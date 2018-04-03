package com.ftrd.flashlight.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Bundle
import com.ftrd.flashlight.FileKt.DefaultValue.USER_PWD
import com.ftrd.flashlight.FileKt.LogUtils
import com.ftrd.flashlight.FlashLight.Companion.eBus
import com.ftrd.flashlight.R
import com.ftrd.flashlight.nettys.buss.LoginBus
import com.ftrd.flashlight.nettys.NettyConnect.channel
import com.ftrd.flashlight.util.ActivitiesManager.popActivity
import com.ftrd.flashlight.util.ActivitiesManager.popAllActivities
import com.ftrd.flashlight.util.ToastUtil
import kotlinx.android.synthetic.main.activity_login.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.startActivity
import kotlin.experimental.and

/**
 * @author: Jeff <15899859876@qq.com>
 * @date:  2018-02-23 17:23
 * @description:用户登陆页面，刷卡登录
 */
class LoginActivity : BaseActivity() {
    override fun into(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_login);
        eBus.register(this);
        //刷卡登录
        nfcResolve(intent)
    }

    var mLoginBus: LoginBus? = null;
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(loginBus: LoginBus?) {
        mLoginBus = loginBus;
        if (loginBus!!.loginResult.equals("0")) {
            // 用户登陆成功,
            // startActivity(Intent(LoginActivity@this,MainActivity::class.java))
            startActivity<MainActivity>();
            popActivity();//退出当前Activity
        } else if (loginBus?.loginResult.equals("0")) {
            ToastUtil.show("用户登录失败！");
        } else if (loginBus?.registertResult.equals("1")) {
            loginTvCar.text = "设备注册成功,请刷卡登陆.";
        } else if (loginBus?.registertResult.equals("0")) {
            loginTvCar.text = "设备注册失败,请联系管理员.";
        }
    }

    override fun keycodeEnter() {
        super.keycodeEnter();
        if (mLoginBus?.registertResult.equals("1")) {
            //设备注册成功,请刷卡登陆
            //登录
            channel!!.write(mLoginBus)
        }

    }


    //使用NFC刷卡获得登录名和密码
    fun ByteArrayToHexString(inarray: ByteArray): String {
        var i: Int
        var j: Int
        var `in`: Int
        val hex = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J")
        var out = ""
        j = 0
        while (j < inarray.size) {
            `in` = (inarray[j] and 0xff.toByte()).toInt()
            i = `in` shr 4 and 0x0f
            out += hex[i]
            i = `in` and 0x0f
            out += hex[i]
            ++j
        }
        return out
    }

    fun nfcResolve(intent: Intent) {
        //  LogUtils.v("LoginActivity", "resolve Intent :" + intent.toString())
        // Parse the intent
        val action = intent.action
        if (NfcAdapter.ACTION_TAG_DISCOVERED == action
                || NfcAdapter.ACTION_TECH_DISCOVERED == action
                || NfcAdapter.ACTION_NDEF_DISCOVERED == action) {

            val myNFCID = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID)
            //获取刷卡值，NFC返回的值
            val mac = ByteArrayToHexString(myNFCID)
            val tag = intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)
            val rawMsgs = intent
                    .getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)
            LogUtils.v("LoginActivity", "myNFCID mac >> " + mac)
            LogUtils.v("LoginActivity", " tag >> " + tag.toString())
            LogUtils.v("LoginActivity", " rawMsgs >> " + rawMsgs)
            loginTvCar.text = mac;
            USER_PWD = mac;
            if (mLoginBus?.registertResult.equals("1")) {
                //设备注册成功,请刷卡登陆
                //登录
                channel!!.write(mLoginBus)
            }
            /*buildTagViews(mac);*/
        } else {
            //  LogUtils.e("LoginActivity", "Unknown intent " + intent)
            //finish();
            return
        }
    }
}
