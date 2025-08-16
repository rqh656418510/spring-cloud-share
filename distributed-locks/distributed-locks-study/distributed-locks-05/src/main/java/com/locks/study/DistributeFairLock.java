package com.locks.study;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 基于 ZooKeeper 临时顺序节点实现分布式锁（公平锁），先到先得，可以避免 "饥饿问题" 和 "惊群效应"
 * 特别注意：代码缺乏健壮性和可用性（尤其是对网络抖动、可重入、会话过期的处理），仅供参考，不适用于生产环境
 */
public class DistributeFairLock {

    // ZooKeeper 连接地址（多个集群节点使用逗号分割）
    private static final String ADDRESS = "192.168.2.235:2181,192.168.2.235:2182,192.168.2.235:2183";

    // ZooKeeper 会话超时时间
    private static final int SESSION_TIMEOUT = 2000;

    // 根节点的路径
    private static final String ROOT_PATH = "/locks";

    // 子节点的路径前缀
    private static final String PREFIX_CHILD_PATH = "seq-";

    // ZooKeeper 客户端
    private ZooKeeper client;

    // 当前客户端创建的子节点的路径
    private String currentChildNodePath;

    // 当前客户端监听的前一个子节点的路径
    private String preChildNodePath;

    // 等待客户端建立连接的锁
    private CountDownLatch connectLatch = new CountDownLatch(1);

    public DistributeFairLock() throws Exception {
        // 初始化客户端
        client = new ZooKeeper(ADDRESS, SESSION_TIMEOUT, new ConnectWatcher());

        // 阻塞等待客户端建立连接
        connectLatch.await();

        // 如果根节点不存在，则创建根节点，根节点类型为永久节点
        if (client.exists(ROOT_PATH, false) == null) {
            client.create(ROOT_PATH, "locks".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }

        System.out.println("ZooKeeper server connect success.");
    }

    /**
     * 加锁
     */
    public void lock() throws Exception {
        // 创建当前子节点（子节点的类型为临时顺序节点）
        currentChildNodePath = client.create(ROOT_PATH + "/" + PREFIX_CHILD_PATH, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

        // 获取所有子节点
        List<String> children = client.getChildren(ROOT_PATH, false);

        // 如果只有一个子节点，则当前客户端直接获得锁
        if (children.size() == 1) {
            return;
        }

        // 子节点根据序号进行排序
        Collections.sort(children);

        // 获取当前子节点的名称，如：seq-0000000009
        String currentChildNodeName = currentChildNodePath.substring((ROOT_PATH + "/").length());

        // 通过子节点名称，获取当前子节点在子节点集合中的位置
        int currentChildNodeIndex = children.indexOf(currentChildNodeName);

        // 子节点位置不正常，直接抛出异常
        if (currentChildNodeIndex == -1) {
            throw new RuntimeException("Current child node not in children list.");
        }
        // 如果当前子节点是第一个子节点，则当前客户端直接获得锁
        else if (currentChildNodeIndex == 0) {
            System.out.println("acquire the lock.");
            return;
        }
        // 如果当前子节点不是第一个子节点，则监听前一个子节点被删除
        else {
            // 获取前一个子节点的路径
            preChildNodePath = ROOT_PATH + "/" + children.get(currentChildNodeIndex - 1);

            // 新建一个专用的 Latch
            CountDownLatch watchPreNodeLatch = new CountDownLatch(1);

            // 判断前一个子节点是否存在，并给该节点注册一个监听器，负责监听该节点被删除
            Stat stat = client.exists(preChildNodePath, new NodeDeletedWatcher(preChildNodePath, watchPreNodeLatch));

            // 如果前一个子节点已存在，阻塞等待该节点被删除（进入等待锁状态）
            if (stat != null) {
                // 双重检查，再次判断前一个子节点是否存在，防止线程死等
                if (client.exists(preChildNodePath, false) != null) {
                    // 阻塞等待前一个子节点被删除
                    watchPreNodeLatch.await();
                }
            }

            // 如果前一个子节点不存在或者阻塞等待被唤醒，则当前客户端直接获得锁
            System.out.println("acquire the lock.");
            return;
        }
    }

    /**
     * 释放锁
     */
    public void unlock() {
        try {
            // 删除子节点（如果指定的版本为 -1，则它与该节点的任何版本匹配）
            client.delete(currentChildNodePath, -1);
            System.out.println("release the lock.");
        } catch (KeeperException.NoNodeException ignored) {
            // 子节点不存在是正常情况，可忽略异常信息
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ZK 连接事件的 Watcher
     */
    class ConnectWatcher implements Watcher {

        @Override
        public void process(WatchedEvent event) {
            // 处理客户端连接状态事件
            if (Event.KeeperState.SyncConnected == event.getState()) {
                connectLatch.countDown();
            }
        }

    }

    /**
     * ZK 节点被删除事件的 Watcher
     */
    class NodeDeletedWatcher implements Watcher {

        private final String path;
        private final CountDownLatch latch;

        NodeDeletedWatcher(String path, CountDownLatch latch) {
            this.path = path;
            this.latch = latch;
        }

        @Override
        public void process(WatchedEvent event) {
            // 处理节点被删除的事件（释放锁）
            if (event.getType() == Event.EventType.NodeDeleted && path.equals(event.getPath())) {
                latch.countDown();
            }
        }
    }

    /**
     * 关闭 ZK 连接
     */
    public void close() {
        try {
            if (client != null) {
                client.close();
            }
        } catch (Exception ignored) {

        }
    }

}
