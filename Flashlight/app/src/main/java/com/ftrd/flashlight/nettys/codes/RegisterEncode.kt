package com.ftrd.flashlight.nettys.codes

import com.ftrd.flashlight.FileKt.DefaultValue
import com.ftrd.flashlight.FileKt.FinalValue
import com.ftrd.flashlight.FileKt.LogUtils
import com.ftrd.flashlight.nettys.models.RegistertInfo
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageEncoder

/**
 * @author: Jeff <15899859876@qq.com>
 * @date:  2018-02-23 15:07
 * @description: 设备注册登陆信息
 */
class RegisterEncode: MessageToMessageEncoder<RegistertInfo>(){
    val CMD = "V1";//注册通道
    /*private String text =
     "99dc0153,700009,,V1,161223 181342,A,11322.0751,2307.8062,0.0,0.0,0000000000000000,0000000000000000,
     90.69,26.6,94,1,0,1,0,0,0.0.1.00,0101,120.27.47.226:9005,1,0,,#";*/
    private fun build(): StringBuilder {
        val sb = StringBuilder();
        sb.append(FinalValue.MSG_HEAD);
        sb.append(",");
        sb.append(DefaultValue.USER_NAME);
        sb.append(",");
        sb.append(",");
        sb.append(CMD);
        sb.append(",");
        return sb;
    }

    override fun encode(ctx: ChannelHandlerContext?, msg: RegistertInfo, out: MutableList<Any>?) {
        val sb = build();
         LocationInfo(sb);
        msg.buildInfo(sb);
        sb.append("#");
        val len = sb.length - 5;
        val strLen = "0000".substring(0, 4 - len.toString().length) + len;
        sb.insert(4, strLen);
        out!!.add(sb.toString());
        LogUtils.i(this.javaClass.name, "注册编码 sb=" + sb.toString());
    }

    //本身为定位信息，现在没有使用定位所以在这里直接使用固定的参数
    fun LocationInfo(sb: StringBuilder) {
        sb.append(System.currentTimeMillis().toString())
        sb.append(",")
        sb.append("A")
        sb.append(",")
        sb.append("11322.0751")
        sb.append(",")
        sb.append("2307.8062")
        sb.append(",")
        //sb.append(mSpeed);
        sb.append("0.00")
        sb.append(",")
        //sb.append(",");
        sb.append("0.00")
        //sb.append(",");
        //sb.append(mDirection);
        sb.append(",")
        sb.append("0000000000000000")
        sb.append(",")
        sb.append("0000000000000000")
        sb.append(",")
        sb.append(90.69f)
        sb.append(",")
        sb.append(26.60f)
        sb.append(",")
        sb.append("94")
        sb.append(",")
        sb.append("01")
        sb.append(",")
        sb.append("0")
        sb.append(",")
        sb.append(1)
        sb.append(",")
        sb.append(0)
        sb.append(",")
        sb.append(0)
        sb.append(",")
    }
}