package com.example.demo.service;


import io.netty.channel.Channel;

import java.util.concurrent.ScheduledFuture;

public class LiveChannelCache {
    private Channel channel;
    private ScheduledFuture future;

    public LiveChannelCache(Channel channel, ScheduledFuture future) {
        this.channel = channel;
        this.future = future;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public ScheduledFuture getFuture() {
        return future;
    }

    public void setFuture(ScheduledFuture future) {
        this.future = future;
    }
}
