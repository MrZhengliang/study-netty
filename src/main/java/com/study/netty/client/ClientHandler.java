package com.study.netty.client;

import com.study.netty.protocol.Packet;
import com.study.netty.protocol.PacketCodeC;
import com.study.netty.protocol.request.LoginRequestPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;
import java.util.UUID;

/**
 * @author fuzl
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + ": 客户端开始登录");

        // 创建登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("flash");
        loginRequestPacket.setPassword("pwd");

        // 编码
        ByteBuf buffer = PacketCodeC.INSTANCE.encode(ctx.alloc(),loginRequestPacket);

        // 登录写数据
        ctx.channel().writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 读取数据
        ByteBuf byteBuf = (ByteBuf) msg;
        // 解码
        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);

        packet.readMsg(ctx,packet);

    }
}
