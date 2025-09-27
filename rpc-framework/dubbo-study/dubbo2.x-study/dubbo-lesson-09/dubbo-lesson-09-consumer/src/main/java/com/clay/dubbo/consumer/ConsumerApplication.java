package com.clay.dubbo.consumer;

import com.alibaba.fastjson.JSON;
import com.clay.dubbo.service.DemoService;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.io.Bytes;

import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;

public class ConsumerApplication {

    // ZooKeeper 连接地址（多个集群节点使用逗号分割）
    private static final String ADDRESS = "192.168.2.235:2181";

    // ZooKeeper 会话超时时间
    private static final int SESSION_TIMEOUT = 2000;

    // 根节点的路径
    private static final String ROOT_PATH = "/dubbo";

    // 服务节点的路径
    private static final String SERVICE_PATH = ROOT_PATH + "/" + DemoService.class.getName() + "/providers";

    /**
     * 获取 ZK 客户端
     */
    public CuratorFramework getCuratorFramework() {
        // 重试策略
        ExponentialBackoffRetry backoffRetry = new ExponentialBackoffRetry(3000, 3);

        // 创建 ZK 客户端
        CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString(ADDRESS)
            .connectionTimeoutMs(SESSION_TIMEOUT)
            .retryPolicy(backoffRetry)
            .build();

        // 启动 ZK 客户端
        client.start();

        return client;
    }

    public void run() throws Exception {
        // 获取服务地址列表
        CuratorFramework zkClient = getCuratorFramework();
        List<String> providerUrls = zkClient.getChildren().forPath(SERVICE_PATH);

        if (providerUrls.size() == 0) {
            System.out.println("服务列表为空，客户端退出");
            System.exit(0);
        }

        // 随机选取一个服务地址
        int index = new Random().nextInt(providerUrls.size());
        String providerUrl = providerUrls.get(index);
        System.out.println("未解码的 URL: " + providerUrl);

        // 对 Provider URL 进行解码
        String decodeUrl = URLDecoder.decode(providerUrl, StandardCharsets.UTF_8.name());
        System.out.println("解码后的 URL: " + decodeUrl);

        // 获取 Provider 的信息
        URL url = URL.valueOf(decodeUrl);
        String protocol = url.getProtocol();
        String host = url.getHost();
        int port = url.getPort();
        String path = url.getPath();

        // 创建 TCP 客户端
        SocketChannel dubboClient = SocketChannel.open();
        dubboClient.connect(new InetSocketAddress(host, port));

        // 发送的数据（消息体），基于 FastJSON 序列化协议
        StringBuffer bodyString = new StringBuffer();
        // Dubbo 协议的版本
        bodyString.append(JSON.toJSONString("2.0.2")).append("\r\n");
        // 接口地址
        bodyString.append(JSON.toJSONString(path)).append("\r\n");
        // 接口版本
        bodyString.append(JSON.toJSONString("0.0.0")).append("\r\n");
        // 方法名称
        bodyString.append(JSON.toJSONString("sayHello")).append("\r\n");
        // 参数描述
        bodyString.append(JSON.toJSONString("Ljava/lang/String;")).append("\r\n");
        // 参数值（经过序列化）
        bodyString.append(JSON.toJSONString("Jim")).append("\r\n");
        // 附加参数（用于扩展 Dubbo 功能）
        bodyString.append("{}").append("\r\n");
        byte[] body = bodyString.toString().getBytes();

        // 发送的数据（消息头），固定 16 个字节
        byte[] header = new byte[16];
        // 魔数，协议标识（2 个字节）
        byte[] magicArray = Bytes.short2bytes((short) 0xdabb);
        System.arraycopy(magicArray, 0, header, 0, 2);
        // 请求 / 响应标识（1 个字节）
        header[2] = (byte) 0xc6;
        // 响应状态（1 个字节）
        header[3] = 0x00;
        // 请求 ID（8 个字节）
        byte[] requestId = Bytes.long2bytes(1);
        System.arraycopy(requestId, 0, header, 4, 8);
        // 消息体的长度（4 个字节）
        byte[] bodyLength = Bytes.int2bytes(body.length);
        System.arraycopy(bodyLength, 0, header, 12, 4);

        // 封装请求报文
        byte[] request = new byte[header.length + body.length];
        System.arraycopy(header, 0, request, 0, header.length);
        System.arraycopy(body, 0, request, header.length, body.length);

        // 发送 RPC 请求
        dubboClient.write(ByteBuffer.wrap(request));

        // 获取响应结果
        ByteBuffer response = ByteBuffer.allocate(1024);
        dubboClient.read(response);

        // 准备读取响应结果
        response.flip();

        // 打印响应结果
        byte[] data = new byte[response.remaining()];
        response.get(data);
        System.out.println("RPC 调用结果：" + new String(data));
    }

    public static void main(String[] args) throws Exception {
        ConsumerApplication application = new ConsumerApplication();
        application.run();
    }

}
