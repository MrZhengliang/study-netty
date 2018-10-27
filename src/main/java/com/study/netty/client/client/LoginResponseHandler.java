package com.study.netty.client.client;

import com.study.netty.protocol.response.LoginResponsePacket;
import com.study.netty.session.Session;
import com.study.netty.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginResponsePacket loginResponsePacket) throws Exception {
        String userId = loginResponsePacket.getUserId();
        String userName = loginResponsePacket.getUserName();

        if(loginResponsePacket.isSuccess()){
            System.out.println( "[" + userName + "]登录成功,userId为:" + loginResponsePacket.getUserId());

            SessionUtil.bindSession(new Session(userId,userName) ,channelHandlerContext.channel());
        }else {
            System.out.println("[" + userName + "]登录失败，原因：" + loginResponsePacket.getReason());
}
    }


    @Override
    public void channelInactive(ChannelHandlerContext channelHandlerContext){
        System.out.println("客户端连接被关闭");
    }
}
