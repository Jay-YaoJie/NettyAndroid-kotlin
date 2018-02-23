package com.ftrd.flashlight.nettys

import com.ftrd.flashlight.FileKt.LogUtils
import com.ftrd.flashlight.nettys.models.RegistertInfo
import io.netty.channel.ChannelDuplexHandler
import io.netty.channel.ChannelHandlerAdapter
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelPromise
import java.util.concurrent.TimeUnit

/**
 * @author: Jeff <15899859876@qq.com>
 * @date:  2018-02-07 16:56
 * @description: ChannelDuplexHandler则同时实现了ChannelInboundHandler和ChannelOutboundHandler接口。
 * 如果一个所需的ChannelHandler既要处理入站事件又要处理出站事件，推荐继承此类。
 *
 * 当然这里使用的是netty4
 * 如果使用netty5使用ChannelHandlerAdapter也同样实现ChannelInboundHandler和ChannelOutboundHandler接口
 */
class MsgHandler : ChannelDuplexHandler() {
    override fun channelRead(ctx: ChannelHandlerContext?, msg: Any?) {
        // super.channelRead(ctx, msg)
        val value = String(msg as ByteArray);
       LogUtils.d("MsgHandler","channelRead=${value}");


    }

    override fun write(ctx: ChannelHandlerContext?, msg: Any?, promise: ChannelPromise?) {
        LogUtils.d("MsgHandler","write");
        super.write(ctx, msg, promise);
    }

    override fun channelActive(ctx: ChannelHandlerContext?) {
        // super.channelActive(ctx)
        //注册设备,服务器连接成功，注册通道，认证
        LogUtils.d("MsgHandler","channelActive")
        ctx?.writeAndFlush("connectByKotlin")
        ctx?.executor()?.scheduleAtFixedRate(
                { ctx.writeAndFlush( RegistertInfo()) },
                0,
                10,
                TimeUnit.SECONDS)

//        val registertInfo = RegistertInfo()
//        ctx!!.writeAndFlush( RegistertInfo())

    }

    override fun channelRegistered(ctx: ChannelHandlerContext?) {
        LogUtils.d("MsgHandler","channelRegistered");
        super.channelRegistered(ctx);
    }

    override fun channelUnregistered(ctx: ChannelHandlerContext?) {
        // super.channelUnregistered(ctx)
        LogUtils.d("MsgHandler","channelUnregistered");
        ctx?.close();

    }

    override fun channelInactive(ctx: ChannelHandlerContext?) {
        LogUtils.d("MsgHandler","channelInactive");
        super.channelInactive(ctx);
    }

    override fun close(ctx: ChannelHandlerContext?, promise: ChannelPromise?) {
        super.close(ctx, promise);
        LogUtils.d("MsgHandler","close");
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
        super.exceptionCaught(ctx, cause);
        LogUtils.d("MsgHandler",cause?.message.toString());
    }
}
