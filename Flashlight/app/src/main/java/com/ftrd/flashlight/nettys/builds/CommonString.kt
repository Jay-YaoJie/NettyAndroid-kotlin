package com.ftrd.flashlight.nettys.builds

import com.ftrd.flashlight.FileKt.DefaultValue
import com.ftrd.flashlight.FileKt.FinalValue
import com.ftrd.flashlight.FileKt.LogUtils

/**
 * @author: Jeff <15899859876@qq.com>
 * @date:  2018-02-28 09:46
 * @description: 发送指令的全局配置
 */
object CommonString {
    //发送指令的前半部 99dc0038,0000037,,V120,  返回的是StringBuilder对象
     fun build(cmd:String): StringBuilder {
        val sb = StringBuilder();
        sb.append(FinalValue.MSG_HEAD);
        sb.append(",");
        sb.append(DefaultValue.USER_NAME);
        sb.append(",");
        sb.append(",");
        sb.append(cmd);
        sb.append(",");
        return sb;
    }
    //添加字符串结尾字符# 并计算长度，返回String字符串直接可以发送的字符串
    fun getStrSb(strBuilder:StringBuilder): String{
        strBuilder.append("#");
        var len:Int = strBuilder.length - 5;
        var strLen:String = "0000".substring(0, 4 - len.toString().length) + len;
        strBuilder.insert(4, strLen);
        var strSb:String=strBuilder.toString();//保存 StringBuilder为String字符串
        LogUtils.d("CommonString","最后要发送的字符串-----编码 sb===${strSb}");
        return strSb;
    }

}
