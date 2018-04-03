/*
 * Copyright (c) 2016.
 * chinaume@163.com
 */

package com.goav.netty.Handler

import com.ftrd.flashlight.FileKt.LogUtils
import com.ftrd.flashlight.nettys.builds.GsonHelper
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToByteEncoder

/**
 * 发送编码器
 *
 * @time: 16/10/8 12:14.<br></br>
 * @author: Created by moo<br></br>
 */

internal class EncodeHandler<T> : MessageToByteEncoder<GsonHelper<T>>() {
    override fun encode(ctx: ChannelHandlerContext, msg: GsonHelper<T>, out: ByteBuf) {
        try {
//            val bytes = msg.read();
//            val size = bytes.size
            out.writeInt(msg.size);//body长度
            out.writeBytes(msg.bytes);//内容
            System.out.println("size=" + msg.size)
        } catch (e: Exception) {
          LogUtils.e("EncodeHandler", e.message!!)
        }
    }

}
