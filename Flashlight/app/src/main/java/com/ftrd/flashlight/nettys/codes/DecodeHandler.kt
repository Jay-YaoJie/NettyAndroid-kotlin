package com.ftrd.flashlight.nettys.codes

import com.ftrd.flashlight.FileKt.FinalValue
import com.ftrd.flashlight.FileKt.LogUtils
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder
import org.jetbrains.anko.getStackTraceString

/**
 * @author: Jeff <15899859876@qq.com>
 * @date:  2018-03-22 15:58
 * @description: 接收器，并解析编码
 */
class DecodeHandler : ByteToMessageDecoder() {
    /**参数:接受心跳包的次数
     *  ？：表示当前是否对象可以为空
     *  ！！： 表示当前对象不为空的情况下执行
     * @param ctx ChannelHandlerContext
     * @param in ByteBuf
     * @param out MutableList<Any>
     */
    override fun decode(ctx: ChannelHandlerContext?, `in`: ByteBuf?, out: MutableList<Any>?) {
        try {
            LogUtils.d("DecodeHandler", "接收到服务器返回的值。。。。。。")
            if (`in`!!.isReadable && `in`!!.readableBytes() > 4) {
                `in`.markReaderIndex();

                //这里是之前使用字符拼接起来的 [0040, 000015, , C30, 180330 174323, 1, , 20, 0, 0010#]
                val data = ByteArray(4)
                `in`.readBytes(data)
                val aswer = String(data)
                if (aswer.equals(FinalValue.MSG_HEAD)) {
                    LogUtils.d("DecodeHandler", "服务器返回心跳cmd:" + FinalValue.MSG_HEAD)
                }
                if (aswer.equals(FinalValue.MSG_HEAD)) {
                    val index = `in`.bytesBefore('#'.toByte())
                    if (index > 0) {
                        val msg = ByteArray(index + 1)
                        `in`.readBytes(msg)
                        val text = String(msg)
                        out!!.add(text)
                    } else {
                        `in`.resetReaderIndex()
                    }

                }

//                val dataLength = `in`.readInt() - 4;
//                LogUtils.d("DecodeHandler", "接收到服务器返回的数据" +
//                        "----`in`.readInt()=" + `in`!!.readInt() + "----`in`.readableBytes()=" + `in`.readableBytes());
//                //如果返回json数据则使用这个方法
//                if (dataLength <= 0 || `in`.readableBytes() < dataLength) {
//                    `in`.resetReaderIndex();
//                } else {
//                    val bytes = ByteArray(dataLength);
//                    `in`.readBytes(bytes);
//                    LogUtils.d("DecodeHandler", "接收到的数据转为String" + String(bytes))
//                    out!!.add(bytes);
//                }
            }
        } catch (e: Exception) {
            LogUtils.e("DecodeHandler", "接收数据出现异常" + e.getStackTraceString());
        }
    }


}