package com.ftrd.flashlight.nettys.codes

import com.ftrd.flashlight.FileKt.DefaultValue.USER_PWD
import com.ftrd.flashlight.FileKt.FinalValue.COMMAND_IP
import com.ftrd.flashlight.FileKt.FinalValue.COMMAND_PORT
import com.ftrd.flashlight.nettys.builds.CommonString
import com.ftrd.flashlight.nettys.buss.LoginBus
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageEncoder

/**
 * @author: Jeff <15899859876@qq.com>
 * @date:  2018-02-28 09:40
 * @description:发送 用户登录
 *  * 登陆信息:99dc0038,000003,,V120, , ,192.168.1.181:8025,用户名,密码#
 * 返回信息:0054,000003,,C120,170414 110212,V120,170414 110212,1,0,ok#
 */
class LoginEncode : MessageToMessageEncoder<LoginBus>() {

    override fun encode(ctx: ChannelHandlerContext?, msg: LoginBus?, out: MutableList<Any>?) {
        val sb:StringBuilder = CommonString.build("V120");
        sb.append(" ");
        sb.append(",");
        sb.append(" ");
        sb.append(",");
        sb.append("${COMMAND_IP}:${COMMAND_PORT}");
        sb.append(",");
        sb.append(USER_PWD);
        sb.append(",");
        sb.append(USER_PWD);
        out!!.add(CommonString.getStrSb(sb))

    }
}