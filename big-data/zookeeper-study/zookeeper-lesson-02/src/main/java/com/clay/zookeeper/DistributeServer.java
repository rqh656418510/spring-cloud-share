package com.clay.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

public class DistributeServer {

    // ZooKeeper 连接地址（多个集群节点使用逗号分割）
    private static final String ADDRESS = "192.168.2.235:2181,192.168.2.235:2182,192.168.2.235:2183";

    // ZooKeeper 会话超时时间
    private static final int SESSION_TIMEOUT = 2000;

    // 根节点的路径
    private static final String ROOT_PATH = "/servers";

    // ZooKeeper 客户端
    private ZooKeeper client;

    /**
     * 初始化服务器
     */
    private void init() throws Exception {
        if (client == null) {
            client = new ZooKeeper(ADDRESS, SESSION_TIMEOUT, new Watcher() {
                @Override
                public void process(WatchedEvent event) {

                }
            });
        }
    }

    /**
     * 注册服务器
     */
    private void register(String hostname) throws Exception {
        // 创建临时顺序节点
        client.create(ROOT_PATH + "/server", hostname.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("===> " + hostname + " is online.");
    }

    /**
     * 业务逻辑处理
     */
    private void process() throws Exception {
        // 模拟业务处理
        Thread.sleep(Integer.MAX_VALUE);
    }

    /**
     * 关闭服务器
     */
    private void close() throws Exception {
        if (client != null) {
            client.close();
        }
    }

    public static void main(String[] args) throws Exception {
        DistributeServer distributeServer = new DistributeServer();
        distributeServer.init();
        distributeServer.register("192.168.1.103");
        distributeServer.process();
        distributeServer.close();
    }

}
