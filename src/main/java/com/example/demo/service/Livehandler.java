package com.example.demo.service;

import com.example.demo.entity.LiveMessage;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Livehandler extends SimpleChannelInboundHandler<LiveMessage> {
    private static Map<Integer, LiveChannelCache> channelCache = new HashMap<>();
    private Logger logger = LoggerFactory.getLogger(Livehandler.class);
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LiveMessage liveMessage) throws Exception {
        Channel channel = channelHandlerContext.channel();
        final int hashCode = channel.hashCode();
        System.out.println("channel hashCode:" + hashCode + "msg:" + liveMessage + "cache:" + channelCache.size());

        if (!channelCache.containsKey(hashCode)) {
            System.out.println("channelCache.containsKey(hasCode),put key:"+hashCode);
            channel.closeFuture().addListener(future -> {
                System.out.println("channel close, remove key: "+ hashCode);
                channelCache.remove(hashCode);
            });

            ScheduledFuture scheduledFuture = channelHandlerContext.executor().schedule(
                    () -> {
                        System.out.println("schedule runs, close channel:" + hashCode);
                        channel.close();
                    }, 10, TimeUnit.SECONDS);
            channelCache.put(hashCode, new LiveChannelCache(channel,scheduledFuture));
        }

        switch (liveMessage.getType()) {
            case LiveMessage.TYPE_HEART: {
                LiveChannelCache cache = channelCache.get(hashCode);
                ScheduledFuture scheduledFuture = channelHandlerContext.executor().schedule(()->channel.closeFuture(),5, TimeUnit.SECONDS);
                cache.getFuture().cancel(true);
                cache.setFuture(scheduledFuture);
                channelHandlerContext.channel().writeAndFlush(liveMessage);
                break;
            }
            case LiveMessage.TYPE_MESSAGE: {
                channelCache.entrySet().stream().forEach(entry -> {
                    Channel otherChannel = entry.getValue().getChannel();
                    otherChannel.writeAndFlush(liveMessage);
                });
                break;
            }
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        logger.debug("channelReadComplete");
        super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.debug("exceptionCaught");
        if (null != cause) cause.printStackTrace();
        if (null != ctx) ctx.close();
    }
}
