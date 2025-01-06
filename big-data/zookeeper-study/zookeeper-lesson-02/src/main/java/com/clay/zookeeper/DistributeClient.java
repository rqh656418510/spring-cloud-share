package com.clay.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.ArrayList;
import java.util.List;

public class DistributeClient {

    // ZooKeeper 连接地址（多个集群节点使用逗号分割）
    private static final String ADDRESS = "192.168.2.235:2181,192.168.2.235:2182,192.168.2.235:2183";

    // ZooKeeper 会话超时时间
    private static final int SESSION_TIMEOUT = 2000;

    // 根节点的路径
    private static final String ROOT_PATH = "/servers";

    // ZooKeeper 客户端
    private ZooKeeper client;

    /**
     * 初始化客户端
     */
    private void init() throws Exception {
        if (client == null) {
            client = new ZooKeeper(ADDRESS, SESSION_TIMEOUT, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    System.out.println("===> " + event.getType() + " -- " + event.getPath());
                    try {
                        // 监听器是一次性的，当接收到监听事件后，需要重新设置监听器
                        getServerList();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /**
     * 获取服务器列表，并监听服务列表的变化
     */
    private void getServerList() throws Exception {
        List<String> resultList = new ArrayList<>();

        // 获取子节点，并监听子节点的变化
        List<String> children = client.getChildren("/servers", true);
        for (String child : children) {
            // 获取子节点的数据
            byte[] data = client.getData(ROOT_PATH + "/" + child, false, null);
            if (data != null) {
                resultList.add(new String(data));
            }
        }

        // 打印服务器列表
        resultList.forEach(System.out::println);
    }

    /**
     * 业务逻辑处理
     */
    private void process() throws Exception {
        // 模拟业务处理
        Thread.sleep(Integer.MAX_VALUE);
    }

    /**
     * 关闭客户端
     */
    private void close() throws Exception {
        if (client != null) {
            client.close();
        }
    }

    public static void main(String[] args) throws Exception {
        DistributeClient distributeClient = new DistributeClient();
        distributeClient.init();
        distributeClient.getServerList();
        distributeClient.process();
        distributeClient.close();
    }

}
