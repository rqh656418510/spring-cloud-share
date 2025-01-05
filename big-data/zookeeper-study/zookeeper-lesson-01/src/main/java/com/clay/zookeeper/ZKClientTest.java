package com.clay.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ZKClientTest {

    // ZooKeeper 连接地址（多个集群节点使用逗号分割）
    private static final String ADDRESS = "192.168.2.235:2181,192.168.2.235:2182,192.168.2.235:2183";

    // ZooKeeper 会话超时时间
    private static final int SESSION_TIMEOUT = 2000;

    // ZooKeeper 客户端
    private static ZooKeeper client;

    /**
     * 初始化 ZK 客户端
     */
    @BeforeAll
    public static void init() throws Exception {
        // 创建 ZK 客户端，并设置全局监听器
        client = new ZooKeeper(ADDRESS, SESSION_TIMEOUT, new Watcher() {

            /**
             * 接收到监听事件后的回调函数（用户的业务逻辑）
             */
            @Override
            public void process(WatchedEvent event) {
                // 获取监听事件的信息
                System.out.println("===> Watcher: " + event.getType() + " -- " + event.getPath());

                // 监听器是一次性的，当接收到监听事件后，需要重新设置监听器
                try {
                    List<String> children = client.getChildren("/", true);
                    for (String path : children) {
                        System.out.println("===> " + path);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
    }

    /**
     * 创建节点
     */
    @Test
    public void createNode() throws Exception {
        String path = client.create("/java", "Hello World".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("===> " + path);
    }

    /**
     * 获取子节点，并监听子节点的变化
     * <p> 监听器是一次性的，当接收到监听事件后，需要重新设置监听器
     */
    @Test
    public void getChildren() throws Exception {
        List<String> children = client.getChildren("/", true);
        for (String path : children) {
            System.out.println("===> " + path);
        }
        // 延时阻塞
        System.in.read();
    }

    /**
     * 判断节点是否存在
     */
    @Test
    public void exist() throws Exception {
        Stat stat = client.exists("/java", false);
        System.out.println(stat == null ? "not exist" : "exist");
    }

}
