package com.ftrd.flashlight.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.ftrd.flashlight.FileKt.LogUtils
import com.ftrd.flashlight.R

/**
 * @author: Jeff <15899859876@qq.com>
 * @date:  2018-02-23 17:23
 * @description:用户登陆页面
 */
class LoginActivity : BaseActivity() {
    override fun into(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_login);
    }





    override fun keycodeEnter() {
        super.keycodeEnter()
        LogUtils.d("com.ftrd.flashlight.activity,LoginActivity","点击了确认按钮");
    }

    override fun keycodeMenu() {
        super.keycodeMenu()
        LogUtils.d("com.ftrd.flashlight.activity,LoginActivity","点击了返回按钮");
    }
}
