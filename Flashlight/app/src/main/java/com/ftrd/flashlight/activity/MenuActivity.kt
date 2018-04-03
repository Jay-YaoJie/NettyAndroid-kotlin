package com.ftrd.flashlight.activity

import android.os.Bundle
import com.ftrd.flashlight.R

/**
 * @author: Jeff <15899859876@qq.com>
 * @date:  2018-03-21 10:37
 * @description: 菜单列表
 */
class MenuActivity : BaseActivity() {
    override fun into(savedInstanceState: Bundle?) {
        super.into(savedInstanceState)
        setContentView(R.layout.activity_menu);
    }
}
