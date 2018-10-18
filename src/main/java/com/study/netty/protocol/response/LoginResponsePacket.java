package com.study.netty.protocol.response;

import com.study.netty.protocol.Packet;
import com.study.netty.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import lombok.Data;

import java.util.Date;

import static com.study.netty.protocol.command.Command.LOGIN_RESPONSE;


@Data
public class LoginResponsePacket extends Packet {
    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }

    /**
     * 读取登录响应，处理登录逻辑
     *
     * @param ctx
     */
    @Override
    public void readMsg(ChannelHandlerContext ctx, Packet packet) {
        LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;

        if (loginResponsePacket.isSuccess()) {
            System.out.println(new Date() + ": 客户端登录成功");
            LoginUtil.markAsLogin(ctx.channel());
        } else {
            System.out.println(new Date() + ": 客户端登录失败，原因：" + loginResponsePacket.getReason());
        }
    }
}
