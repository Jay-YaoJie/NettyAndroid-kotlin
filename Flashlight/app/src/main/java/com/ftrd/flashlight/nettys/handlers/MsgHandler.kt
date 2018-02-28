package com.ftrd.flashlight.nettys.handlers

import com.ftrd.flashlight.FileKt.FinalValue.MSG_HEAD
import com.ftrd.flashlight.FileKt.LogUtils
import com.ftrd.flashlight.nettys.builds.RegistertBuild
import com.ftrd.flashlight.nettys.buss.LoginBus
import io.netty.channel.ChannelDuplexHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelPromise
import org.greenrobot.eventbus.EventBus
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
        super.channelRead(ctx, msg)
        // val value:String = String(msg as ByteArray);
        val value: String = msg.toString();
        LogUtils.d("MsgHandler", "channelRead=${value}");
        val arr = value.split(",".toRegex());
        LogUtils.d("MsgHandler", "channelRead=${arr}");
        when (arr[3]) {
            "C0" -> {
                //0048,000004,,C0,170413 181858,V1,170413 181618,0,1,#设备注册成功返回值
                var loginBus: LoginBus = LoginBus();
                loginBus.registertResult = arr[8];
                EventBus.getDefault().post(loginBus);

            }
            "C120" -> {
                //0054,000003,,C120,170414 110212,V120,170414 110212,1,0,ok# 登录返回值
                var loginBus: LoginBus = LoginBus();
                loginBus.loginResult = arr[8];
                EventBus.getDefault().post(loginBus);
            }
            "C51"->{
                /*0315,000019,,C51,180116 180204,171ecaa4-c3aa-4e8b-a532-b1df298f7378,
                  171ecaa4-c3aa-4e8b-a532-b1df298f7378,16668,9E74D299,罗东,A组,
                  d98955e7-f4af-475f-90a6-56d314964628,1,0,
                   d4196a24-59af-44db-b003-2210e4734692,2018-01-16,
                  3471e4d4-20df-4ee9-b33f-9fd6f8ad221,BA2C63E4A47749B099FF94E77F54C707,大日检,2018-01-16,09:32:00,1,501,1A#*/


            }
            else -> {
                print("o == 3")
            }
        }


    }

    override fun write(ctx: ChannelHandlerContext?, msg: Any?, promise: ChannelPromise?) {
        LogUtils.d("MsgHandler", "write");
        super.write(ctx, msg, promise);
    }

    override fun channelActive(ctx: ChannelHandlerContext?) {
        super.channelActive(ctx)
        //注册设备,服务器连接成功，注册通道，认证
        LogUtils.d("MsgHandler", "channelActive")
        //在这里只做发送注册的信息
        ctx!!.writeAndFlush(RegistertBuild());
        //在这里发送心跳
        //0029,000004,,C3,170413 181858,10#心跳包设置
        //  val time = Integer.parseInt(arr[5].replace("#", ""))
        ctx?.executor()?.scheduleAtFixedRate(
                {
                    ctx.writeAndFlush(MSG_HEAD)
                    LogUtils.d("MsgHandler", "channelActive里发送心跳${MSG_HEAD}")
                },
                0,
                10,
                TimeUnit.SECONDS)

    }

    override fun channelRegistered(ctx: ChannelHandlerContext?) {
        LogUtils.d("MsgHandler", "channelRegistered");
        super.channelRegistered(ctx);
    }

    override fun channelUnregistered(ctx: ChannelHandlerContext?) {
        super.channelUnregistered(ctx)
        LogUtils.d("MsgHandler", "channelUnregistered");
        //  ctx!!.close();

    }

    override fun channelInactive(ctx: ChannelHandlerContext?) {
        LogUtils.d("MsgHandler", "channelInactive");
        super.channelInactive(ctx);
    }

    override fun close(ctx: ChannelHandlerContext?, promise: ChannelPromise?) {
        super.close(ctx, promise);
        LogUtils.d("MsgHandler", "close");
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
        super.exceptionCaught(ctx, cause);
        LogUtils.d("MsgHandler", cause?.message.toString());
        ctx!!.close()
    }
}
