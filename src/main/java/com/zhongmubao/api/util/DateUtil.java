package com.zhongmubao.api.util;

import com.zhongmubao.api.config.Constants;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 时间工具类
 *
 * @author 孙阿龙
 */
public class DateUtil {


    /**
     * 一个月的第一天
     *
     * @return 时间
     */
    public static Date monthFirstDay() {
        Calendar cale = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
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
        SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        return weeHours(strToDate(format.format(cale.getTime())), 1);
    }

    /**
     * 指定日期那个月的第一天
     *
     * @return 时间
     */
    public static Date monthFirstDay(Date date) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        return weeHours(strToDate(format.format(cale.getTime())), 0);
    }

    /**
     * 指定日期那个月的最后一天
     *
     * @return 时间
     */
    public static Date monthLastDay(Date date) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
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
     * 取一个时间23：59：59
     */
    public static Date dayEnd(Date date) {
        try {
            return weeHours(date, 1);
        } catch (Exception ex) {
            return date;
        }
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
            SimpleDateFormat formater = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
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
            SimpleDateFormat formater = new SimpleDateFormat(Constants.DATE_FORMAT);
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
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(strDate, pos);
    }

    /**
     * 字符串格式化时间
     *
     * @param strDate 时间字符串
     * @return 日期
     */
    public static Date strToShortDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
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
     * @param dt 日期
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

    /**
     * 获取当前日期是星期几（数字）
     *
     * @param dt 日期
     * @return 当前日期是星期几
     */
    public static int getWeekOfDateNumber(Date dt) {
        int[] weekDays = {7, 1, 2, 3, 4, 5, 6};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * 获取当前日期的周一和周日
     *
     * @return 2018年03月12日-2018年03月18日
     */
    public static String getWeekSection() {
        String weekSection;
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_CHINA);
        Calendar cal = Calendar.getInstance();
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayWeek == 1) {
            dayWeek = 8;
        }
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dayWeek);
        Date mondayDate = cal.getTime();
        String weekBegin = sdf.format(mondayDate);
        cal.add(Calendar.DATE, 4 + cal.getFirstDayOfWeek());
        Date sundayDate = cal.getTime();
        String weekEnd = sdf.format(sundayDate);

        weekSection = weekBegin + "-" + weekEnd;
        return weekSection;
    }

    /**
     * 得到某一天是这一年的第几周
     *
     * @param date 某一天
     * @return int 第几周
     */
    public static int getYearWeek(String date) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            cal.setTime(format.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int week = cal.get(Calendar.WEEK_OF_YEAR);
        return week;
    }
}
