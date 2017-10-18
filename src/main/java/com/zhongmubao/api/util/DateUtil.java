package com.zhongmubao.api.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {


    /**
     * 一个月的第一天
     *
     * @return 时间
     */
    public static Date monthFirstDay() {
        Calendar cale = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        return weeHours(strToDate(format.format(cale.getTime())), 0);

    }

    /**
     * 一个月的最后一天
     *
     * @return 时间
     */
    public static Date monthLastDay() {
        Calendar cale = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        return weeHours(strToDate(format.format(cale.getTime())), 1);
    }

    /**
     * 取当天时间凌晨
     *
     * @param date 日期
     * @param day  要添加的天数
     */
    public static Date addDay(Date date, int day) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);//把日期往后增加一天.整数往后推,负数往前移动
        return calendar.getTime();   //这个时间就是日期往后推一天的结果
    }

    /**
     * 给一个时间添加小时
     *
     * @param date 时间
     * @param hour 小时
     * @return 新时间
     */
    public static Date addHours(Date date, int hour) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hour);
        return calendar.getTime();
    }

    /**
     * 给一个时间添加小时
     *
     * @param date   时间
     * @param minute 分钟
     * @return 新时间
     */
    public static Date addMinute(Date date, int minute) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    /**
     * 时间格式化成Mongo时间
     *
     * @param date 时间
     * @return 新时间
     */
    public static Date formatMongo(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, 8);
        return calendar.getTime();
    }

    /**
     * 取当天时间凌晨
     */
    public static Date dayBegin() throws ParseException {
        return weeHours(new Date(), 0);
    }

    /**
     * 取当天时间23：59：59
     */
    public static Date dayEnd() throws ParseException {
        return weeHours(new Date(), 1);
    }

    /**
     * 取一个时间的凌晨和23：59：59
     *
     * @param date 日期
     * @param flag 0 取凌晨 1取23：59：59
     */
    private static Date weeHours(Date date, int flag) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        //时分秒（毫秒数）
        long millisecond = hour * 60 * 60 * 1000 + minute * 60 * 1000 + second * 1000;
        //凌晨00:00:00
        cal.setTimeInMillis(cal.getTimeInMillis() - millisecond);

        if (flag == 0) {
            return cal.getTime();
        } else if (flag == 1) {
            //凌晨23:59:59
            cal.setTimeInMillis(cal.getTimeInMillis() + 23 * 60 * 60 * 1000 + 59 * 60 * 1000 + 59 * 1000);
        }
        return cal.getTime();
    }

    /**
     * 把时间格式化成yyyy-MM-dd HH:mm:ss
     *
     * @param date 日期
     */
    public static String formatDefault(Date date) {
        try {
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return formater.format(date);
        } catch (Exception ex) {
            return "";
        }
    }

    /**
     * 把时间格式化成y
     *
     * @param date    日期
     * @param pattern 要格式成的样子
     */
    public static String format(Date date, String pattern) {
        try {
            return new SimpleDateFormat(pattern).format(date);
        } catch (Exception ex) {
            return "";
        }
    }

    /**
     * 把时间格式化成yyyy-MM-dd
     *
     * @param date 日期
     */
    public static String formatShort(Date date) {
        try {
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            return formater.format(date);
        } catch (Exception ex) {
            return "";
        }
    }

    /**
     * 字符串格式化时间
     *
     * @param strDate 时间字符串
     * @return 日期
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(strDate, pos);
    }

    /**
     * 某个日期对应小时
     *
     * @param date 日期
     */
    public static int hourOfDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 某个日期对应的天
     *
     * @param date 日期
     */
    public static int dateOfDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DATE);
    }

    /**
     * 某个日期对应的月
     *
     * @param date 日期
     */
    public static int monthOfDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 两个时间相减得到天
     *
     * @param late  时间
     * @param early 被减时间
     */
    public static int subDateOfDay(Date late, Date early) {

        java.util.Calendar calst = java.util.Calendar.getInstance();
        java.util.Calendar caled = java.util.Calendar.getInstance();
        calst.setTime(early);
        caled.setTime(late);
        //设置时间为0时
        calst.set(java.util.Calendar.HOUR_OF_DAY, 0);
        calst.set(java.util.Calendar.MINUTE, 0);
        calst.set(java.util.Calendar.SECOND, 0);
        caled.set(java.util.Calendar.HOUR_OF_DAY, 0);
        caled.set(java.util.Calendar.MINUTE, 0);
        caled.set(java.util.Calendar.SECOND, 0);
        //得到两个日期相差的天数
        return ((int) (caled.getTime().getTime() / 1000) - (int) (calst
                .getTime().getTime() / 1000)) / 3600 / 24;
    }

    /**
     * 两个时间相减得到小时
     *
     * @param date1 时间
     * @param date2 被减时间
     */
    public static long subDateOfHour(Date date1, Date date2) {
        return (date1.getTime() - date2.getTime()) / (60 * 60 * 1000);
    }

    /**
     * 两个时间相减得到秒
     *
     * @param date1 时间
     * @param date2 被减时间
     */
    public static long subDateOfSecond(Date date1, Date date2) {
        return (date1.getTime() - date2.getTime()) / (1000);
    }

    /**
     * 获取当前日期是星期几
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"日", "一", "二", "三", "四", "五", "六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }
}
