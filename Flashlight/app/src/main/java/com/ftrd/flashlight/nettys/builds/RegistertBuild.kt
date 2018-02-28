package com.ftrd.flashlight.nettys.builds

/**
 * @author: Jeff <15899859876@qq.com>
 * @date:  2018-02-23 15:11
 * @description:设备注册登陆信息对象
 * 注册
 * 99dc0156,000015,,V1,180228 141652,A,11322.0751,2307.8062,0.00,0.00,
 * 0000000000000000,0000000000000000,90.69,26.6,94,01,0,1,0,0,0.0.1.00,0101,120.27.47.226:9005,1,0,,#
 * 返回
 * 0048,000015,,C0,180228 142144,V1,180228 141652,0,1,#
 */
class RegistertBuild {
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
