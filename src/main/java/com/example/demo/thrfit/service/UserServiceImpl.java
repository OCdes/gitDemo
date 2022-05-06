package com.example.demo.thrfit.service;

import com.example.demo.thrfit.generated.User;
import com.example.demo.thrfit.generated.UserService;
import org.apache.thrift.TException;

public class UserServiceImpl implements UserService.Iface {
    @Override
    public User getUser(int uid) throws TException {
        User user = new User(uid,"agg");
        return user;
    }
}
