package com.maven.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    //日期转字符窜
    public static String dateToString(Date date, String form) {
        SimpleDateFormat sdf = new SimpleDateFormat(form);
        return sdf.format(date);
    }

    //字符串转日期
    public static Date StringToDate(Date date, String form) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(form);
        return sdf.parse(form);
    }

    //获取年月日
    public static Integer getCalendar(String key) {
        //获取当前系统年月日
        Calendar cal = Calendar.getInstance();
        if ("day".equals(key)) {
            return cal.get(Calendar.DATE);
        }
        if ("month".equals(key)) {
            return cal.get(Calendar.MONTH) + 1;
        }
        if ("year".equals(key)) {
            return cal.get(Calendar.YEAR);
        }
        return 0;
    }
}
