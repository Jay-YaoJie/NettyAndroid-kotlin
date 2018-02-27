package com.ftrd.flashlight.util

import android.support.annotation.StringRes
import android.widget.Toast
import com.ftrd.flashlight.FlashLight.Companion.instance

/**
 * @author: Jeff <15899859876@qq.com>
 * @date:  2018-02-27 12:13
 * @description:
 */
object ToastUtil {
    fun show(text: CharSequence?) {
        if (text != null) {
            if (text.length < 10) {
                Toast.makeText(instance, text, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(instance, text, Toast.LENGTH_LONG).show()
            }
        }
    }

    fun show(@StringRes resId: Int) {
        show(instance.getString(resId))
    }

    fun show() {
        //先检查网络是否可以使用
        //        if (App.app!=null&&!CommonUtils.isNetworkAvailable(TussaudSmart.instance)){
        //            show( "当前网络不可用，请稍后重试！");
        //        }else {
        //            show("服务器繁忙，请稍后重试！");
        //
        //        }
    }

}
