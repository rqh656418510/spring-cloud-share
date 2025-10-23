namespace java com.clay.dubbo.thrift

service DemoThrift {
    string sayHello(1:string name)
}

// Thrift 编译命令： thrift -r -gen java DemoThrift.thrift
