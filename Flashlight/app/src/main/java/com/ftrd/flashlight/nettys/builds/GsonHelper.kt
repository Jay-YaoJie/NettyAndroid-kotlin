package com.ftrd.flashlight.nettys.builds

import com.ftrd.flashlight.FlashLight.Companion.gson
import com.google.gson.GsonBuilder
import java.io.Serializable
import com.google.gson.annotations.Expose
import java.lang.reflect.Modifier


/**
 * @author: Jeff <15899859876@qq.com>
 * @date:  2018-03-23 10:53
 * @description: gson 对象 初始化和实例化在app初始之前执行，
 * 这里只做转换，json转换为对象，对象转换为json字符串，，中间使用的是byteArray
 */
open class GsonHelper<T> : Serializable {

    // gson 对象 自定义委托实现单例,只能修改这个值一次.
//   private     var gson: Gson by DelegatesExt.notNullSingleValue<Gson>();
//
//    init {
//        gson = GsonBuilder()
//                .excludeFieldsWithModifiers(Modifier.PROTECTED) //@protected 修饰的过滤，比如自动增长的id
//                .create()
//    }
//前面出现的都已get和set
    @Expose
    var result: T? = null;//保存数据对象
    @Expose
    var status: Int = 0;//保存数据状态，也可以是其他状态
    @Expose
    var message: String? = null;//保存其他的数据

    override fun toString(): String = gson!!.toJson(this);
    //转换为json字符串
    private fun read(): ByteArray = gson!!.toJson(this).toByteArray()

    //转换为当前对象
    internal fun write(message: ByteArray): GsonHelper<T> = gson!!.fromJson(String(message), this.javaClass);

    internal val bytes: ByteArray = read();//内容
    internal val size: Int = bytes.size + 4;//body长度
}
