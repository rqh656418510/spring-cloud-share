package com.clay.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class DistributeLock {

    // ZooKeeper 连接地址（多个集群节点使用逗号分割）
    private static final String ADDRESS = "192.168.2.235:2181,192.168.2.235:2182,192.168.2.235:2183";

    // ZooKeeper 会话超时时间
    private static final int SESSION_TIMEOUT = 2000;

    // 根节点的路径
    private static final String ROOT_PATH = "/locks";

    // 子节点的路径前缀
    private static final String PREFIX_CHILD_PATH = "seq-";

    // 等待客户端建立连接的锁
    private CountDownLatch connectLatch = new CountDownLatch(1);

    // 等待监听前一个节点的锁
    private CountDownLatch watchPreNodeLatch = new CountDownLatch(1);

    // ZooKeeper 客户端
    private ZooKeeper client;

    // 当前客户端创建的子节点的路径
    private String currentChildNodePath;

    // 当前客户端监听的前一个节点的路径
    private String preChildNodePath;

    public DistributeLock() throws Exception {
        // 初始化客户端
        client = new ZooKeeper(ADDRESS, SESSION_TIMEOUT, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                // 监听到客户端已建立连接
                if (event.getState() == Event.KeeperState.SyncConnected) {
                    connectLatch.countDown();
                }

                // 监听到前一个节点的删除（释放锁）
                if (event.getType() == Event.EventType.NodeDeleted && event.getPath().equals(preChildNodePath)) {
                    watchPreNodeLatch.countDown();
                }
            }
        });

        // 阻塞等待客户端建立连接
        connectLatch.await();

        // 如果根节点不存在，则创建根节点，根节点类型为永久节点
        if (client.exists(ROOT_PATH, false) == null) {
            client.create(ROOT_PATH, "locks".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
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
        if (currentChildNodeIndex == -1) {
            // 抛出异常
            throw new RuntimeException("Current child node not in children list.");
        } else if (currentChildNodeIndex == 0) {
            // 如果当前子节点是第一个子节点，则当前客户端直接获得锁
            return;
        } else {
            // 如果当前子节点是第一个子节点，则监听前一个子节点
            preChildNodePath = ROOT_PATH + "/" + children.get(currentChildNodeIndex - 1);
            client.getData(preChildNodePath, true, null);
            // 阻塞等待监听事件（进入等待锁状态）
            watchPreNodeLatch.await();
            return;
        }
    }

    /**
     * 释放锁
     */
    public void unlock() {
        // 删除子节点（如果指定的版本为 -1，则它与任何节点的版本匹配）
        try {
            client.delete(currentChildNodePath, -1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
