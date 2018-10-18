package com.study.netty.protocol.request;

import com.study.netty.protocol.Packet;
import com.study.netty.protocol.PacketCodeC;
import com.study.netty.protocol.response.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import lombok.Data;

import java.util.Date;

import static com.study.netty.protocol.command.Command.MESSAGE_REQUEST;

@Data
public class MessageRequestPacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }


    @Override
    public void readAndWriteMsg(ChannelHandlerContext ctx, Packet packet) {
        // 客户端发来消息
        MessageRequestPacket messageRequestPacket = ((MessageRequestPacket) packet);

        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        System.out.println(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());
        messageResponsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");
        ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), messageResponsePacket);
        ctx.channel().writeAndFlush(responseByteBuf);
    }
}
