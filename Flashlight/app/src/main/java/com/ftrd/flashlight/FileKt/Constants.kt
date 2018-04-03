/*
 * Copyright (C) 2017 codeestX
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ftrd.flashlight.FileKt

import android.os.Build

/**
 * @author: Jeff <15899859876@qq.com>
 * @date:  2018-02-01 16:47
 * @description: 全局属性   var是一个可变变量，val是一个只读变量相当于java中的final变量。
 */

//不可改变的值声名并且通过const修饰的常量,const相当于final

object FinalValue {

    //app名字
    const val APP_NAME: String = "ditiejianxiu";
    //使用的netty注册时使用的通信协议
    const val MSG_HEAD: String = "99dc";


    //服务器的IP地址，和IP端口号
    const val COMMAND_IP: String = "192.168.3.181";//中心服务地址
    const val COMMAND_PORT: Int = 8025;//中心服务端口
    const val MEDIA_PORT: Int = 5008;//视频端口

    //FTP服务器连接地址和用户名
    const val FTP_IP: String = "192.168.3.112";//FTP服务器地址
    const val FTPPORT: Int = 21;//FTP服务器端口
    const val NAME_PWS: String = "thinkland";//FTP服务用户名和密码

    //CRASH保存的异常文件目录和文件名称
    val CRASH_FILE_PATH: String? = "${DefaultValue.ROOT_DIR}/${APP_NAME}/crash/";//保存文件地址，有可能为空
    const val CRASH_FILE_NAME: String = ".crash";//保存的异常文件名为.crash
    const val CRASH_SAVESD: Boolean = true;//true打打印到机器则为调试，false则保存到本地

    //LOG保存平常打印的信息文件地址和文件名称
    val LOG_FILE_PATH: String? = "${DefaultValue.ROOT_DIR}/${APP_NAME}/log/";//保存文件地址，有可能为空
    const val LOG_FILE_NAME: String = ".log";//保存的异常文件名为.log
    const val LOG_SAVESD: Boolean = false;//是否保存到文件//是否存log到sd卡
    const val LOG_DEBUG: Boolean = true;//保存是否开启打印模式//是否打印log

    //数据库名,数据库版本
    const val DB_NAME: String = "MyDatabase";//数据库名
    const val DB_VERSION: Int = 1;//数据库版本


}

//可改变的值声名
object DefaultValue {
    //使用手电筒时，本身要刷卡的，在这里设置为一个默认值登录
    var USER_NAME: String = "000019"; //000037  ,000019   ,000015
    //刷卡之后得到的登录用户名和密码都是用的这一个
    var USER_PWD: String = "9E74D299"; //37 9BDFF075;//  19 9E74D299  //15  9BDFF076
    var NICK_NAME:String?=null;//登录成功返回的名称
    //保存根目录文件地址
    var ROOT_DIR: String = "";//在Application初始化中赋值，其他地方直接调用即可
    //连接的wifi
    // WIFI:P:88888888;T:WPA2;S:SZMC5_LJK;;
    // result:WIFI:P:ftrd123456;T:WPA2;S:ftrd;;
    var WIFI_P: String = "ftrd123456";
    var WIFI_S: String = "ftrd";
    //phone wifi
    var WIFI_SP: String = "1234567890";
    var WIFI_PP: String = "Jeff";
}
