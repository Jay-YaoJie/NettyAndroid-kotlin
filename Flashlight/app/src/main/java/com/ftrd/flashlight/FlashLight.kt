package com.ftrd.flashlight


import com.ftrd.flashlight.FileKt.DefaultValue
import com.ftrd.flashlight.FileKt.DelegatesExt
import com.ftrd.flashlight.FileKt.FileUtils
import com.ftrd.flashlight.nettys.NettyConnect
import android.support.multidex.MultiDexApplication
import com.ftrd.flashlight.util.ActivitiesManager.getActivitiesManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.greenrobot.eventbus.EventBus
import java.lang.reflect.Modifier
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*


/**
 * @author: Jeff <15899859876@qq.com>
 * @date:  2018-02-01 16:47
 * @description:
 */
class FlashLight : MultiDexApplication() {
    //companion静态声类声名对象，相当于static关键
    companion object {
        // 按照我们在Java中一样创建一个单例最简单的方式：
//        private var instance:Application?=null;
//        fun instance()= instance!!;
        // 单例不会是null   所以使用notNull委托
        //var instance: FlashLight by Delegates.notNull()
        // 自定义委托实现单例,只能修改这个值一次.
        var instance: FlashLight by DelegatesExt.notNullSingleValue<FlashLight>();
        //创建一个Gson实例对象
        var gson: Gson by DelegatesExt.notNullSingleValue<Gson>();
        //创建一个 EventBus 实例对象
        var eBus: EventBus by DelegatesExt.notNullSingleValue<EventBus>();
    }

    //override实现接口关键字，override修饰的方法,默认是可以被继承的
    override fun onCreate() {
        super.onCreate();
        instance = this;
        initThirdService();

    }

    /**
     * 初始化第三方的服务和对象
     */
    private fun initThirdService() {
        Thread(Runnable {
            kotlin.run {
//                EventBus.getDefault().register(instance);
//                eBus = EventBus.getDefault();
                eBus= EventBus.builder().installDefaultEventBus()
                //获取根目录,并保存
                DefaultValue.ROOT_DIR = FileUtils.getRootDir();
                //初始化activity堆栈管理
                getActivitiesManager()
                //初始化gson对象
                gson = GsonBuilder()
                        .excludeFieldsWithModifiers(Modifier.PROTECTED) //@protected 修饰的过滤，比如自动增长的id
                        .create()
                //打开日志工具，主要是保存异常信息
                //CrashHandler.getInit().init();//暂时出现异常交给系统处理，，，，没有自己处理
                NettyConnect.nettyConnect();//连接网络
            }
        }).start();

    }
    override fun onTerminate() {
        // 程序终止的时候执行
        NettyConnect.nettyDestroy();//关闭服务器连接
        super.onTerminate()
    }


}