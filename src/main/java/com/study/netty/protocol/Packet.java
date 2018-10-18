package com.study.netty.protocol;

import com.alibaba.fastjson.annotation.JSONField;
import io.netty.channel.ChannelHandlerContext;
import lombok.Data;

@Data
public abstract class Packet {
    /**
     * 协议版本
     */
    @JSONField(deserialize = false, serialize = false)
    private Byte version = 1;


    @JSONField(serialize = false)
    public abstract Byte getCommand();

    /**
     * 读取消息
     * @param ctx
     */
    public void readMsg(ChannelHandlerContext ctx,Packet packet) {
    }

    /**
     * 写出消息
     * @param ctx
     */
    public void writeMsg(ChannelHandlerContext ctx,Packet packet) {
    }

    /**
     * 读取和响应消息
     * @param ctx
     */
    public void readAndWriteMsg(ChannelHandlerContext ctx,Packet packet) {

    }
}
