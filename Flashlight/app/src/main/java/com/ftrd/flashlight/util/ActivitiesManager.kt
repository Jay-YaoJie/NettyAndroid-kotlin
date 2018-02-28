package com.ftrd.flashlight.util


import com.ftrd.flashlight.FileKt.LogUtils
import android.app.Activity
import com.ftrd.flashlight.FileKt.DelegatesExt
import java.util.*


/**
 * @author: Jeff <15899859876@qq.com>
 * @date:  2018-02-24 17:24
 * @description:activity堆栈管理
 */
object ActivitiesManager {

    private var mActivityStack: Stack<Activity> by DelegatesExt.notNullSingleValue<Stack<Activity>>();
    //初始货Activity堆管理
    fun getActivitiesManager() {
        mActivityStack = Stack<Activity>()

    }

    //获得保存的Activity数量
    fun stackSize(): Int {
        return mActivityStack!!.size
    }

    //获得当前的Activity
    fun getCurrentActivity(): Activity? {
        var activity: Activity? = null

        try {
            activity = mActivityStack!!.lastElement()
        } catch (e: Exception) {
            return null
        }

        return activity
    }

    //退出当前Activity
    fun popActivity() {
        var activity = mActivityStack!!.lastElement()
        if (null != activity) {
            LogUtils.i("com.ftrd.flashlight.util.ActivitiesManager", "popActivity-->" + activity!!.javaClass.getSimpleName())
            activity!!.finish()
            mActivityStack!!.remove(activity)
            activity = null
        }
    }

    //退出当前指定的Activity
    fun popActivity(activity: Activity?) {
        var activity = activity
        if (null != activity) {
            LogUtils.i("com.ftrd.flashlight.util.ActivitiesManager", "popActivity-->" + activity.javaClass.simpleName)
            // activity.finish();
            mActivityStack!!.remove(activity)
            activity = null
        }
    }

    //添加Activity
    fun pushActivity(activity: Activity) {
        mActivityStack!!.add(activity)
        LogUtils.i("com.ftrd.flashlight.util.ActivitiesManager", "pushActivity-->" + activity.javaClass.simpleName)
    }
    //关闭所有的Activity，并退出系统
    fun popAllActivities() {
        while (!mActivityStack!!.isEmpty()) {
            val activity = getCurrentActivity() ?: break
            activity.finish()
            popActivity(activity)
        }
        //退出系统
        System.exit(0)
    }


    fun popSpecialActivity(cls: Class<*>) {
        try {
            val iterator = mActivityStack!!.iterator()
            var activity: Activity? = null
            while (iterator.hasNext()) {
                activity = iterator.next()
                if (activity!!.javaClass == cls) {
                    activity!!.finish()
                    iterator.remove()
                    activity = null
                }
            }
        } catch (e: Exception) {

        }finally {

        }

    }
}

