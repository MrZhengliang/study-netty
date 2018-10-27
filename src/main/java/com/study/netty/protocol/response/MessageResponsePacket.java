package com.study.netty.protocol.response;

import com.study.netty.protocol.Packet;
import io.netty.channel.ChannelHandlerContext;
import lombok.Data;

import java.util.Date;

import static com.study.netty.protocol.command.Command.MESSAGE_RESPONSE;


@Data
public class MessageResponsePacket extends Packet {

    private String fromUserId;

    private String fromUserName;

    private String message;

    @Override
    public Byte getCommand() {

        return MESSAGE_RESPONSE;
    }

    @Override
    public void readMsg(ChannelHandlerContext ctx, Packet packet) {
        MessageResponsePacket messageResponsePacket = (MessageResponsePacket) packet;
        System.out.println(new Date() + ": 收到服务端的消息: " + messageResponsePacket.getMessage());
    }
}
