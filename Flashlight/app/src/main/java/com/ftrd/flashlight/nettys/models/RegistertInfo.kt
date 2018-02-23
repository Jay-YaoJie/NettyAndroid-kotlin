package com.ftrd.flashlight.nettys.models

/**
 * @author: Jeff <15899859876@qq.com>
 * @date:  2018-02-23 15:11
 * @description:设备注册登陆信息对象
 */
class RegistertInfo {
    var mUnkown1 = "0.0.1.00";
    var mUnkown2 = "0101";
    var mServerIp = "120.27.47.226";
    var mServerPort: Short = 9005;
    var mRebootNum = 1;
    var mConnectNum = 0;
    var mBusNumber = "";
    var sb = StringBuilder();

    fun buildInfo(sb: StringBuilder) {
        sb.append(mUnkown1);
        sb.append(",");
        sb.append(mUnkown2);
        sb.append(",");
        sb.append(mServerIp);
        sb.append(":");
        sb.append(mServerPort.toInt());
        sb.append(",");
        sb.append(mRebootNum);
        sb.append(",");
        sb.append(mConnectNum);
        sb.append(",");
        sb.append(mBusNumber);
        sb.append(",");
        this.sb = sb;
    }
}
