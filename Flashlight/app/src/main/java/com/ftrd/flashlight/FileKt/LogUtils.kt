package com.ftrd.flashlight.FileKt

import android.util.Log
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * @author: Jeff <15899859876@qq.com> 189
 * @date:  2018-02-02 09:33
 * @description:日志工具类
 */
object LogUtils {
    private val execu: ExecutorService = Executors.newFixedThreadPool(1)

    fun v(tag: String, msg: String) = FinalValue.LOG_DEBUG.debugLog("com.ftrd.flashlight.LogUtils", "${tag}==---==${msg}", Log.VERBOSE)
    fun d(tag: String, msg: String) = FinalValue.LOG_DEBUG.debugLog("com.ftrd.flashlight.LogUtils", "${tag}==---==${msg}", Log.DEBUG)
    fun i(tag: String, msg: String) = FinalValue.LOG_DEBUG.debugLog("com.ftrd.flashlight.LogUtils", "${tag}==---==${msg}", Log.INFO)
    fun w(tag: String, msg: String) = FinalValue.LOG_DEBUG.debugLog("com.ftrd.flashlight.LogUtils", "${tag}==---==${msg}", Log.WARN)
    fun e(tag: String, msg: String) = FinalValue.LOG_DEBUG.debugLog("com.ftrd.flashlight.LogUtils", "${tag}==---==${msg}", Log.ERROR)
    //打印或保存到日志
    private fun Boolean.debugLog(tag: String, msg: String, type: Int) {
        FinalValue.LOG_SAVESD.saveToSd("${SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(Date())}\n${tag}", msg)
        if (!this) {
            return
        }
        when (type) {
            Log.VERBOSE -> Log.v(tag, tag + msg)
            Log.DEBUG -> Log.d(tag, msg)
            Log.INFO -> Log.i(tag, msg)
            Log.WARN -> Log.w(tag, msg)
            Log.ERROR -> Log.e(tag, msg)
        }

    }

    //保存日志到本地文件
    private fun Boolean.saveToSd(tag: String, msg: String) {
        if (!this) {
            return
        }
        execu.submit({
            //获得当前时间
            val current = System.currentTimeMillis();
            val time = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(Date(current));

            //保存日志信息
            FileUtils.appendText(File(FinalValue.LOG_FILE_PATH + time + FinalValue.LOG_FILE_NAME), "\r\n$tag\n$msg")
        })

    }

    //上传到服务器上
    private fun uploadLogToServer() {

    }


}