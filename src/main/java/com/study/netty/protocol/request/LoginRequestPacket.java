package com.study.netty.protocol.request;

import com.study.netty.protocol.Packet;
import com.study.netty.protocol.PacketCodeC;
import com.study.netty.protocol.response.LoginResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import lombok.Data;

import java.util.Date;

import static com.study.netty.protocol.command.Command.LOGIN_REQUEST;


@Data
public class LoginRequestPacket extends Packet {
    private String userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {

        return LOGIN_REQUEST;
    }

    @Override
    public void readAndWriteMsg(ChannelHandlerContext ctx, Packet packet) {
        System.out.println(new Date() + ": 收到客户端登录请求……");
        // 登录流程
        LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(packet.getVersion());
        if (valid(loginRequestPacket)) {
            loginResponsePacket.setSuccess(true);
            System.out.println(new Date() + ": 登录成功!");
        } else {
            loginResponsePacket.setReason("账号密码校验失败");
            loginResponsePacket.setSuccess(false);
            System.out.println(new Date() + ": 登录失败!");
        }
        // 登录响应
        ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
        ctx.channel().writeAndFlush(responseByteBuf);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

}
