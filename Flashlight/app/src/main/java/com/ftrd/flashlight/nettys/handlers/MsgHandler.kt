package com.ftrd.flashlight.nettys.handlers

import com.ftrd.flashlight.FileKt.FinalValue.MSG_HEAD
import com.ftrd.flashlight.FileKt.LogUtils
import com.ftrd.flashlight.FlashLight.Companion.eBus
import com.ftrd.flashlight.nettys.NettyConnect.nettyConnect
import com.ftrd.flashlight.nettys.NettyConnect.nettyDestroy
import com.ftrd.flashlight.nettys.builds.RegistertBuild
import com.ftrd.flashlight.nettys.buss.LoginBus
import io.netty.channel.ChannelHandlerAdapter
import io.netty.channel.ChannelHandlerContext
import org.greenrobot.eventbus.EventBus
import java.util.concurrent.TimeUnit


/**
 * @author: Jeff <15899859876@qq.com>
 * @date:  2018-02-07 16:56
 * @description: ChannelDuplexHandler 则同时实现了 ChannelInboundHandler 和 ChannelOutboundHandler 接口。
 * 如果一个所需的ChannelHandler既要处理入站事件又要处理出站事件，推荐继承此类。
 *
 * 当然这里使用的是netty4
 * 如果使用netty5使用 ChannelHandlerAdapter 也同样实现 ChannelInboundHandler 和 ChannelOutboundHandler 接口
 */
class MsgHandler : ChannelHandlerAdapter() {

    override fun channelActive(ctx: ChannelHandlerContext?) {
        super.channelActive(ctx)
//        if (ctx!!.channel()!!.isActive) {
//            ctx!!.close()
//
//        } else {
        //注册设备,服务器连接成功，注册通道，认证
        LogUtils.d("MsgHandler", "channelActive")
        //在这里只做发送注册的信息
        ctx!!.writeAndFlush(RegistertBuild());
        //在这里发送心跳
//        //0029,000004,,C3,170413 181858,10#心跳包设置
//        //  val time = Integer.parseInt(arr[5].replace("#", ""))
        ctx?.executor()?.scheduleAtFixedRate(
                {
                    ctx.writeAndFlush(MSG_HEAD);
                    LogUtils.d("MsgHandler", "channelActive里发送心跳${MSG_HEAD}---${TimeUnit.SECONDS}")
                },
                0,
                30,
                TimeUnit.SECONDS)

        //   }
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
        super.exceptionCaught(ctx, cause);
        LogUtils.d("MsgHandler", cause?.message.toString());
        if (ctx!!.channel()!!.isActive) {
            nettyDestroy();//关闭服务器连接
            nettyConnect();//连接服务器
        }

    }
    override fun channelRead(ctx: ChannelHandlerContext?, msg: Any?) {
//        if (msg != null) {
//            val value = String(msg as ByteArray)
//            LogUtils.d("MsgHandler", msg.toString()+"==channelRead=${value}");
//
//        }


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
                eBus.post(loginBus);

            }
            "C120" -> {
                //0054,000003,,C120,170414 110212,V120,170414 110212,1,0,ok# 登录返回值
                var loginBus: LoginBus = LoginBus();
                loginBus.loginResult = arr[8];
                eBus.post(loginBus);
            }
            "C51" -> {
                /*0315,000019,,C51,180116 180204,171ecaa4-c3aa-4e8b-a532-b1df298f7378,
                  171ecaa4-c3aa-4e8b-a532-b1df298f7378,16668,9E74D299,罗东,A组,
                  d98955e7-f4af-475f-90a6-56d314964628,1,0,
                   d4196a24-59af-44db-b003-2210e4734692,2018-01-16,
                  3471e4d4-20df-4ee9-b33f-9fd6f8ad221,BA2C63E4A47749B099FF94E77F54C707,大日检,2018-01-16,09:32:00,1,501,1A#*/


            }
//            "C3"->{
//                //0029,000004,,C3,170413 181858,10#心跳包设置
//                //  val time = Integer.parseInt(arr[5].replace("#", ""))
//                ctx?.executor()?.scheduleAtFixedRate(
//                        {
//                            ctx.writeAndFlush(MSG_HEAD)
//                            LogUtils.d("MsgHandler", "channelActive里发送心跳${MSG_HEAD}---${TimeUnit.SECONDS}")
//                        },
//                        0,
//                        10,
//                        TimeUnit.SECONDS)
//            }
            else -> {
                print("o == 3")
            }
        }


    }


}

