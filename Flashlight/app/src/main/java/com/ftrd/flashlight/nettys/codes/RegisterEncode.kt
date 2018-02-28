package com.ftrd.flashlight.nettys.codes

import com.ftrd.flashlight.nettys.builds.RegistertBuild
import com.ftrd.flashlight.nettys.builds.CommonString
import com.ftrd.flashlight.nettys.builds.LocationBuild
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageEncoder

/**
 * @author: Jeff <15899859876@qq.com>
 * @date:  2018-02-23 15:07
 * @description: 发送设备注册登陆信息
 */
class RegisterEncode : MessageToMessageEncoder<RegistertBuild>() {
    /*private String text =
     "99dc0153,700009,,V1,161223 181342,A,11322.0751,2307.8062,0.0,0.0,0000000000000000,0000000000000000,
     90.69,26.6,94,1,0,1,0,0,0.0.1.00,0101,120.27.47.226:9005,1,0,,#";*/
    override fun encode(ctx: ChannelHandlerContext?, msg: RegistertBuild, out: MutableList<Any>?) {
        ////注册通道 V1
        val sb:StringBuilder = CommonString.build("V1");
        LocationBuild.build(sb);
        msg.buildInfo(sb);
        out!!.add(CommonString.getStrSb(sb));
    }


}