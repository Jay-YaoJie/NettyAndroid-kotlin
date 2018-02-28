package com.ftrd.flashlight.services

import android.app.Service
import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.IBinder
import com.ftrd.flashlight.activity.LoginActivity
import org.greenrobot.eventbus.EventBus

/**
 * @author: Jeff <15899859876@qq.com>
 * @date:  2018-02-28 15:54
 * @description:
 */
class NfcSwingCard : Service() {
    override fun onBind(intent: Intent?): IBinder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // TODO Auto-generated method stub
        if (intent == null) {

        } else {
          //  resolve(intent)
        }
        return super.onStartCommand(intent, flags, startId)
    }

//    internal fun resolve(intent: Intent) {
//        //Log.v(TAG, "resolve Intent :" + intent.toString());
//        // Parse the intent
//        val action = intent.action
//        if (NfcAdapter.ACTION_TAG_DISCOVERED == action
//                || NfcAdapter.ACTION_TECH_DISCOVERED == action
//                || NfcAdapter.ACTION_NDEF_DISCOVERED == action) {
//
//            val myNFCID = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID)
//            val mac = LoginActivity.ByteArrayToHexString(myNFCID)
//            val tag = intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)
//            val rawMsgs = intent
//                    .getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)
//
//            /*Log.v(TAG, "myNFCID mac >> " + mac);
//			Log.v(TAG, " tag >> " + tag.toString());
//			Log.v(TAG, " rawMsgs >> " + rawMsgs);*/
//
//            val info = LonginInfo()
//            info.setUserName(mac)
//            info.setUserPwd(mac)
//            EventBus.getDefault().post(info)
//
//            /*acc_tv.setText("用户:"+mac);
//
//			acc_car.setVisibility(View.GONE);*/
//
//            /*buildTagViews(mac);*/
//        } else {
//            //	Log.e(TAG, "Unknown intent " + intent);
//            //finish();
//            return
//        }
//    }
}
