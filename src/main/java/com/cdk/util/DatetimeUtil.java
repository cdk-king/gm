package com.cdk.util;

//Java时间格式转换大全

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatetimeUtil {
    private static Logger logger = LoggerFactory.getLogger(DatetimeUtil.class);

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     *
     * @param strDate
     * @return
     */
    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
     *
     * @param dateDate
     * @return
     */
    public static String dateToStrLong(java.util.Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }


    /**
     * 时间转换
     * 可将秒数转变为可读的时间格式
     * @param value
     * @return
     */
    public static String dealTime(int value) {
        String re = "";
        int size = 0;
        if (value < 60) {
            re = value + "秒";
            return re;
        } else if (value < (60 * 60)) {
            size = value / 60;
            return size + "分钟-" + dealTime(value - size * 60);
        } else if (value < (60 * 60 * 24)) {
            size = value / (60 * 60);
            return size + "小时-" + dealTime(value - size * 60 * 60);
        } else {
            size = value / (60 * 60 * 24);
            return size + "天-" + dealTime(value - size * 60 * 60 * 24);
        }
    }
}