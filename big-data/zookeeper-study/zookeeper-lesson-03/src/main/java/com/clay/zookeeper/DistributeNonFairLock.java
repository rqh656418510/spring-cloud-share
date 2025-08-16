package com.clay.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 基于 ZooKeeper 的临时节点实现分布式锁（非公平锁），直接抢占锁，容易造成 "饥饿问题" 和 "惊群效应"
 * 特别注意：代码缺乏健壮性和可用性（尤其是对网络抖动的处理），仅供参考，不适用于生产环境
 */
public class DistributeNonFairLock {

    private ZooKeeper zk;
    private final static String ROOT_LOCK_PATH = "/locks";
    private final static String LOCK_PREFIX = "lock-";
    private final static CountDownLatch CONNECTED_LATCH = new CountDownLatch(1);
    private static final String ADDRESS = "192.168.2.235:2181,192.168.2.235:2182,192.168.2.235:2183";

    /**
     * 私有构造方法
     */
    private DistributeNonFairLock() {
        try {
            this.zk = new ZooKeeper(ADDRESS, 50000, new ConnectWatcher());

            // 阻塞等待 ZK 客户端建立连接
            CONNECTED_LATCH.await();

            // 确保根节点存在
            if (zk.exists(ROOT_LOCK_PATH, false) == null) {
                try {
                    // 创建持久节点
                    zk.create(ROOT_LOCK_PATH, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                } catch (Exception ignored) {

                }
            }

            System.out.println("ZooKeeper server connect success.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取一个分布式锁
     */
    public boolean acquireDistributedLock(String lockId, long waitTimeMs) {
        String path = ROOT_LOCK_PATH + "/" + LOCK_PREFIX + lockId;
        try {
            // 尝试创建临时节点（抢占锁），若失败会抛出异常
            zk.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            System.out.println("acquire the lock for [id=" + lockId + "].");
            return true;
        } catch (Exception e) {
            // 整体处理的超时时间
            long deadline = System.currentTimeMillis() + waitTimeMs;
            while (true) {
                try {
                    // 判断是否有剩余的时间可用
                    long remaining = deadline - System.currentTimeMillis();
                    if (remaining <= 0) {
                        return false;
                    }

                    // 提前创建本轮专属的 Latch（避免错过节点被删除的事件，导致线程永远阻塞）
                    CountDownLatch waitLatch = new CountDownLatch(1);

                    // 判断节点是否存在，并给该节点注册一个监听器，负责监听该节点被删除
                    Stat stat = zk.exists(path, new NodeDeletedWatcher(path, waitLatch));

                    // 节点已存在，阻塞等待该节点被删除
                    if (stat != null) {
                        boolean signaled = waitLatch.await(remaining, TimeUnit.MILLISECONDS);
                        // 如果阻塞等待超时了，直接放弃继续抢占锁
                        if (!signaled) {
                            return false;
                        }
                    }

                    // 被唤醒后，再次尝试创建临时节点（抢占锁），若失败会抛出异常
                    zk.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

                    System.out.println("acquire the lock for [id=" + lockId + "].");
                    return true;
                } catch (Exception ee) {
                    // 节点已存在，继续下一轮抢占锁，当出现网络抖动或者节点刚删除立刻又被别人创建时，会导致 CPU 占用飙升，建议这里睡眠一段时间或者限制重试次数
                    sleepQuiet(50);
                }
            }
        }
    }

    /**
     * 释放掉一个分布式锁
     */
    public void releaseDistributedLock(String lockId) {
        String path = ROOT_LOCK_PATH + "/" + LOCK_PREFIX + lockId;
        try {
            zk.delete(path, -1);
            System.out.println("release the lock for [id=" + lockId + "].");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ZK 连接事件的 Watcher
     */
    private class ConnectWatcher implements Watcher {

        @Override
        public void process(WatchedEvent event) {
            System.out.println("Receive watched state: " + event.getState());

            // 处理客户端连接状态事件
            if (Event.KeeperState.SyncConnected == event.getState()) {
                CONNECTED_LATCH.countDown();
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
            // 处理节点被删除的事件
            if (event.getType() == Event.EventType.NodeDeleted && path.equals(event.getPath())) {
                latch.countDown();
            }
        }
    }

    /**
     * 睡眠一段时间
     */
    private void sleepQuiet(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * 关闭 ZK 连接
     */
    public void close() {
        try {
            if (zk != null) {
                zk.close();
            }
        } catch (Exception ignored) {

        }
    }

    /**
     * 封装单例的静态内部类
     */
    private static class Singleton {

        private static DistributeNonFairLock instance;

        static {
            instance = new DistributeNonFairLock();
        }

        public static DistributeNonFairLock getInstance() {
            return instance;
        }

    }

    /**
     * 获取单例
     */
    public static DistributeNonFairLock getInstance() {
        return Singleton.getInstance();
    }

}