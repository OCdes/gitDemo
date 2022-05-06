package com.example.demo.thrfit.service;

import com.example.demo.thrfit.generated.UserService;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;

public class ThriftServer {
//    public static void startServer() throws Exception {
//        //定义了从输入数据流中读数据和向数据流中写数据的操作
//        TProcessor processor = new UserService.Processor<UserService.Iface>(new UserServiceImpl());
//        //继承于TTransport，提供一个简单的网络读写抽象，为到来的链接创建一个transport
//        TServerSocket serverSocket = new TServerSocket(8181);
//        //定义了将内存中的数据映射成可传输格式的机制。
//        TCompactProtocol.Factory protocolFactory = new TCompactProtocol.Factory();
//        //将以上所有特性放在一个参数中
//        TServer.Args simpleArgs = new TServer.Args(serverSocket).processor(processor).protocolFactory(protocolFactory);
//        //集成所有的参数的Server
//        TServer server = new TSimpleServer(simpleArgs);
//        //开启服务等待，链接
//        server.serve();
//    }

    public void startServer() throws Exception {
        TServerSocket socket = new TServerSocket(8181);

        TCompactProtocol.Factory factory = new TCompactProtocol.Factory();

        TProcessor processor = new UserService.Processor<UserService.Iface>(new UserServiceImpl());

        TServer server = new TSimpleServer(new TServer.Args(socket).processor(processor).protocolFactory(factory));

        server.serve();
    }
}
