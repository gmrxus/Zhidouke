package com.gmrxus.zhidouke.util;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by mac on 2017/4/25.
 */

public class DateUtil {
    private static final String TAG = "DateUtil";

    private DateUtil() {
    }


    /**
     * 获取当前时间的yyyyMMdd
     *
     * @return
     */
    public static String getNowDateString() {
        Calendar instance = Calendar.getInstance();
        long time = instance.getTime().getTime();
        SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
        String format = yyyyMMdd.format(time);
        Log.e(TAG, "getNowDateString: " + format);
        return format;
    }

    /**
     * 获取当前知乎api所需的日期yyyyMMdd
     *
     * @return
     */
    public static String getZhihuUrlDate() {
        SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
        String format = "";
        try {
            long parse = yyyyMMdd.parse(getNowDateString()).getTime() + 1000 * 60 * 60 * 24;
            Date date = new Date();
            date.setTime(parse);
            format = yyyyMMdd.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return format;
    }

    /**
     * long时间转换成yyyyMMdd
     *
     * @param dateL
     * @return
     */
    public static String getDateString(long dateL) {
        Date date = new Date(dateL);
        SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
        String format = yyyyMMdd.format(date);
        return format;
    }

    /**
     * 获取前一天的yyyyMMdd
     *
     * @param nonceDate
     * @return
     */
    public static String getLastDate(String nonceDate) {
        SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
        String dateStr = null;
        try {
            Date date = yyyyMMdd.parse(nonceDate);
            date.setTime(date.getTime() - 1000 * 60 * 60 * 24);
            dateStr = yyyyMMdd.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateStr;

    }
}
