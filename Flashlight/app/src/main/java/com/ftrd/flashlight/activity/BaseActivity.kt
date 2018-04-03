package com.ftrd.flashlight.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import com.ftrd.flashlight.FileKt.LogUtils
import com.ftrd.flashlight.FlashLight.Companion.eBus
import com.ftrd.flashlight.util.ActivitiesManager.popActivity
import com.ftrd.flashlight.util.ActivitiesManager.pushActivity
import org.greenrobot.eventbus.EventBus
import kotlin.properties.Delegates

/**
 * @author: Jeff <15899859876@qq.com>
 * @date:  2018-02-23 17:25
 * @description: 抽象类对象，并重写了按钮事件
 */
open class BaseActivity : AppCompatActivity() {
    var tag: String? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        into(savedInstanceState);
        tag=this.localClassName;
        pushActivity(this);//添加当前activity
    }

    //   如果设置了abstract就会TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    open fun into(savedInstanceState: Bundle?) {};
    override fun onDestroy() {
        close();
        if (eBus.isRegistered(this)) {
            //如果有注册eventbus则在结束当前页面时关闭
            eBus.unregister(this);
        }
        popActivity(this);//关闭当前activity
        super.onDestroy()
    }

    //页面被关闭生命周期到了onDestroy
    open fun close() {}

    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        LogUtils.d("com.ftrd.flashlight.activity.BaseActivity", "点击按钮=" + event!!.keyCode);
        //重写dispatchKeyEvent方法 按返回键back 执行两次的解决方法
        if (event!!.action != KeyEvent.ACTION_UP) { //不响应按键抬起时的动作
            ////在 when 中，else 同 switch 的 default。如果其他分支都不满足条件将会求值 else 分支。
            when (event!!.keyCode) {
            //  //KEYCODE_DPAD_LEFT 左键按钮 21
                21 -> {
                    keycodeLeft();
                    return true;
                };
            //KEYCODE_DPAD_RIGHT 右键按钮 22
                22 -> {
                    keycodeRight();
                    return true;
                };
            //KEYCODE_ENTER  拍照的左上角按钮/确认按钮 66
                66 -> {
                    keycodeEnter();
                    return true;
                };
            //KEYCODE_MENU 右上角按钮/菜单键（Menu）82
                82 -> {
                    keycodeMenu();
                    return true;
                };
                else -> { // else 同 switch 的 default
                    LogUtils.e("com.ftrd.flashlight.activity.BaseActivity", "点击了其他按钮=" + event!!.keyCode);
                    //dispatchKeyEvent是做分发的工作，如果你想要onKeyDown还可以接收到应该实现返回
                    return super.dispatchKeyEvent(event);
                }
            }
        }
        //dispatchKeyEvent是做分发的工作，如果你想要onKeyDown还可以接收到应该实现返回
        return super.dispatchKeyEvent(event);
    }

    //不使用abstract关键字是因为有的地方不会使用到此方法，
    //KEYCODE_DPAD_LEFT 左键按钮 21
    open fun keycodeLeft() {};
    //KEYCODE_DPAD_RIGHT 右键按钮 22
    open fun keycodeRight() {};
    //KEYCODE_ENTER  拍照的左上角按钮/确认按钮 66
    open fun keycodeEnter() {};
    //KEYCODE_MENU 右上角按钮/菜单键（Menu）82
    open fun keycodeMenu() {};

}

