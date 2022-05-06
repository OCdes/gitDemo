package com.example.demo.thrfit.client;

import com.example.demo.thrfit.generated.User;
import com.example.demo.thrfit.generated.UserService;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.io.IOException;

public class Client {

    public void initConnection() throws Exception {
        TTransport tTransport = new TSocket("localhost",8181);
        TProtocol protocol = new TCompactProtocol(tTransport);

        UserService.Client client = new UserService.Client(protocol);
        tTransport.open();
        User user = client.getUser(123);
    }
}
