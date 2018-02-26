package com.ftrd.flashlight.util


import com.ftrd.flashlight.FileKt.LogUtils
import android.app.Activity
import java.util.*


/**
 * @author: Jeff <15899859876@qq.com>
 * @date:  2018-02-24 17:24
 * @description:activity堆栈管理
 */
open class ActivitiesManager {

    private var mActivityStack: Stack<Activity>? = null
    private var mActivitiesManager: ActivitiesManager? = null


    //初始货Activity堆管理
    open fun getInstance(): ActivitiesManager {
        if (null == mActivitiesManager) {
            mActivitiesManager = ActivitiesManager()
            if (null == mActivityStack) {
                mActivityStack = Stack<Activity>()
            }
        }
        return mActivitiesManager as ActivitiesManager
    }

    //获得保存的Activity数量
    open fun stackSize(): Int {
        return mActivityStack!!.size
    }

    //获得当前的Activity
    open fun getCurrentActivity(): Activity? {
        var activity: Activity? = null

        try {
            activity = mActivityStack!!.lastElement()
        } catch (e: Exception) {
            return null
        }

        return activity
    }

    //退出当前Activity
    open fun popActivity() {
        var activity = mActivityStack!!.lastElement()
        if (null != activity) {
            LogUtils.i("com.ftrd.flashlight.util.ActivitiesManager", "popActivity-->" + activity!!.javaClass.getSimpleName())
            activity!!.finish()
            mActivityStack!!.remove(activity)
            activity = null
        }
    }

    //退出当前指定的Activity
    open fun popActivity(activity: Activity?) {
        var activity = activity
        if (null != activity) {
            LogUtils.i("com.ftrd.flashlight.util.ActivitiesManager", "popActivity-->" + activity.javaClass.simpleName)
            // activity.finish();
            mActivityStack!!.remove(activity)
            activity = null
        }
    }

    //添加Activity
    open fun pushActivity(activity: Activity) {
        mActivityStack!!.add(activity)
        LogUtils.i("com.ftrd.flashlight.util.ActivitiesManager", "pushActivity-->" + activity.javaClass.simpleName)
    }

    open fun popAllActivities() {
        while (!mActivityStack!!.isEmpty()) {
            val activity = getCurrentActivity() ?: break
            activity.finish()
            popActivity(activity)
        }
    }

    //关闭所有的Activity
    open fun popSpecialActivity(cls: Class<*>) {
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

        }

    }
}

