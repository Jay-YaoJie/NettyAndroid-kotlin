package com.ftrd.flashlight.nettys.builds

import java.util.*

/**
 * @author: Jeff <15899859876@qq.com>
 * @date:  2018-02-28 10:19
 * @description: 当前位置信息
 */
object LocationBuild {
    fun getCurrentDateTime(): String {
        val calendar = Calendar.getInstance()
        val retval: String
        var year = calendar.get(Calendar.YEAR)
        var month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        year = if (year > 2000) year - 2000 else year
        val strYear = year.toString()
        month += 1
        val strMonth = (if (month > 9) "" else "0") + month
        val strDay = (if (day > 9) "" else "0") + day
        val date = strYear + strMonth + strDay
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        val second = calendar.get(Calendar.SECOND)
        val strHour = (if (hour > 9) "" else "0") + hour
        val strMinute = (if (minute > 9) "" else "0") + minute
        val strSecond = (if (second > 9) "" else "0") + second
        val time = strHour + strMinute + strSecond
        retval = date + " " + time

        return retval
    }
    //本身为定位信息，现在没有使用定位所以在这里直接使用固定的参数
    //private String text = "99dc0153,700009,,V1,161223 181342,A,
    // 11322.0751,2307.8062,0.0,0.0,0000000000000000,0000000000000000,
    // 90.69,26.6,94,1,0,1,0,0,0.0.1.00,0101,120.27.47.226:9005,1,0,,#";
    fun build(sb: StringBuilder) {
        sb.append(getCurrentDateTime())
        sb.append(",")
        sb.append("A")
        sb.append(",")
        sb.append("11322.0751")
        sb.append(",")
        sb.append("2307.8062")
        sb.append(",")
        //sb.append(mSpeed);
        sb.append("0.00")
        sb.append(",")
        //sb.append(",");
        sb.append("0.00")
        //sb.append(",");
        //sb.append(mDirection);
        sb.append(",")
        sb.append("0000000000000000")
        sb.append(",")
        sb.append("0000000000000000")
        sb.append(",")
        sb.append(90.69f)
        sb.append(",")
        sb.append(26.60f)
        sb.append(",")
        sb.append("94")
        sb.append(",")
        sb.append("01")
        sb.append(",")
        sb.append("0")
        sb.append(",")
        sb.append(1)
        sb.append(",")
        sb.append(0)
        sb.append(",")
        sb.append(0)
        sb.append(",")
    }
}
