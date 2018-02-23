package com.ftrd.flashlight.nettys.codes

import com.ftrd.flashlight.FileKt.FinalValue
import com.ftrd.flashlight.FileKt.LogUtils
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder

/**
 * @author: Jeff <15899859876@qq.com>
 * @date:  2018-02-23 14:52
 * @description: 功能描述:接收服务器返回的心跳包解析
 */
class HeartbeatDecode : ByteToMessageDecoder() {
    /**参数:接受心跳包的次数
     *  ？：表示当前是否对象可以为空
     *  ！！： 表示当前对象不为空的情况下执行
     */
    private var cmd = 0

    override fun decode(ctx: ChannelHandlerContext?, `in`: ByteBuf?, out: MutableList<Any>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        if (`in`!!.readableBytes() < 4) {
            return
        }
        `in`.markReaderIndex()
        val data = ByteArray(4)
        `in`.readBytes(data)
        val aswer = String(data)
        if (aswer.equals(FinalValue.MSG_HEAD)) {
            cmd++
            LogUtils.d(this.toString(), "服务器返回心跳cmd:" + cmd)
        }
        if (aswer.equals(FinalValue.MSG_HEAD)) {
            val index = `in`.bytesBefore('#'.toByte())
            if (index > 0) {
                val msg = ByteArray(index + 1)
                `in`.readBytes(msg)
                //StartRadioStreamNetModel.getInstance().convertToObj(msg,msg.length);
                val text = String(msg)
                out!!.add(text)
            } else {
                `in`.resetReaderIndex()
            }

        }
    }
}
