package com.ranmaxli.api.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by liran on 2017/2/13.
 */
public class DateUtil {
    public static String formatDuring(Integer t) {
        //计算小时,用毫秒总数除以(1000*60*60),后去掉小数点
        int hour = t / (1000 * 60 * 60);
        //计算分钟,用毫秒总数减去小时乘以(1000*60*60)后,除以(1000*60),再去掉小数点
        int min = (t - hour * (1000 * 60 * 60)) / (1000 * 60);
        //同上
        int sec = (t - hour * (1000 * 60 * 60) - min * (1000 * 60)) / 1000;
        return (hour < 10 ? "0" + hour : hour) + ":" + (min < 10 ? "0" + min : min) + ":" + (sec < 10 ? "0" + sec : sec);
    }

    /**
     * 将毫秒时间转化为 00:00:00 格式
     *
     * @param time 需要转化的时间,单位毫秒
     * @return 返回转化后的时间
     * @author xusi
     */
    public static String getDateFormat(long time, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String dateString = formatter.format(time);
        return dateString;

//        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss",
//                Locale.getDefault());
//        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+0"));
//        Date date = new Date();
//        date.setTime(time);
//        return dateFormat.format(date);
    }

    /**
     * 返回时间戳
     *
     * @param dateStr
     * @return
     */
    public static Long getLongTime(String dateStr) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = formatter.parse(dateStr);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return (long) 0;
        }
    }

    public static Date getDate(String timeFormmat, String time) {
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timeFormmat);
        try {
            date = simpleDateFormat.parse(time);
        } catch (Exception ex) {
            date = new Date();
        }
        return date;
    }

    public static String getDateString(Date date) {
        if (date == null)
            return "0000-00-00 00:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static String getDateString(Date date,String formattern) {
        if (date == null)
            date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(formattern);
        return sdf.format(date);
    }

    public static int daysBetween(Date small, Date big) {
        Long intervalMs = SecondsBetween(small,big);
        return secondsToDays(intervalMs);
    }

    public static int HoursBetween(Date small, Date big) {
        Long intervalMs = SecondsBetween(small,big);
        return secondsToHours(intervalMs);
    }

    public static long SecondsBetween(Date small, Date big) {
        long interval = 0l;
        try {
            interval = (big.getTime() - small.getTime())/1000;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return interval;
    }

    public static int millisecondsToDays(long intervalMs) {
        return (int) (intervalMs / (1000 * 86400));
    }

    public static int millisecondsToHours(long intervalMs) {
        return (int) (intervalMs / (1000 * 3600));
    }

    public static int secondsToDays(long intervalMs) {
        return (int) (intervalMs / 86400);
    }

    public static int secondsToHours(long intervalMs) {
        return (int) (intervalMs / 3600);
    }

    public static int millisecondsToSeconds(long intervalMs) {
        return (int) (intervalMs / 1000);
    }

    public static void setTimeToMidnight(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
    }

    /**
     *
     * @param date
     * @return
     */
    public static Date addDays(Date date,int dValue){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, dValue);//当前时间减去一年，即一年前的时间
        return calendar.getTime();//获取一年前的时间，或者一个月前的时间
    }

    /**
     *
     * @param date
     * @return
     */
    public static Date addHours(Date date,int dValue){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, dValue);
        return calendar.getTime();
    }

    /**
     *
     * @param date
     * @return
     */
    public static Date addMonths(Date date,int mValue){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, mValue);//当前时间减去一个月
        return calendar.getTime();//获取一个月前的时间
    }

    /**
     * 判断是否是同一天
     * @param day1
     * @param day2
     * @return
     */
    public static boolean isSameDay(Date day1, Date day2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ds1 = sdf.format(day1);
        String ds2 = sdf.format(day2);
        if (ds1.equals(ds2)) {
            return true;
        } else {
            return false;
        }
    }
}
