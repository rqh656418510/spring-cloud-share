package com.clay.dubbo;

import com.clay.dubbo.thrift.DemoThrift;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class ThriftServerTest {

    /**
     * 测试 Thrift 服务端
     */
    public static void main(String[] args) throws Exception {
        TTransport transport = new TSocket("192.168.2.140", 3030);
        transport.open();
        TProtocol protocol = new TBinaryProtocol(transport);
        DemoThrift.Client client = new DemoThrift.Client(protocol);
        System.out.println("===> start");
        System.out.println(client.sayHello("test"));
        System.out.println("===> end");
        transport.close();
    }

}
