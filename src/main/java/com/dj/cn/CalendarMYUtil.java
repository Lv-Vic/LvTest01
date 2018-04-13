package com.dj.cn;

import com.dj.candystate.MsgQueue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by dajie on 17-6-15.
 */
public class CalendarMYUtil {
    public static void main(String[] args) throws ParseException {
    Date date = new Date();
    SimpleDateFormat si = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = si.parse("2017-12-31 12:00:00");
        String format = si.format(date);
        System.out.println(format);
        System.out.println(parse);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parse);
        System.out.println(new SimpleDateFormat("yyyy-M-d H:m:s").format(calendar.getTime()));
        Calendar instance = Calendar.getInstance();
        instance.set(2017,5,15,20,32,00);
        System.out.println(new SimpleDateFormat("yyyy-M-d H:m:s").format(instance.getTime()));
        int year = Calendar.YEAR;
        int i = instance.get(Calendar.YEAR);
        System.out.println("年份"+i);
        int i1 = instance.get(Calendar.MONTH);
        System.out.println("月份"+(i1+1));
        int i2 = instance.get(Calendar.DAY_OF_WEEK);
        System.out.println("本周第几天"+(i2-1));
        int i3 = instance.get(Calendar.DAY_OF_MONTH);
        System.out.println("本月第几天"+i3);
        int i4 = instance.get(Calendar.DAY_OF_YEAR);
        System.out.println("今年第几天"+i4);
        int i5 = instance.get(Calendar.HOUR_OF_DAY);
        System.out.println("本日的小时"+i5);
        instance.add(Calendar.HOUR_OF_DAY,1);
        int i6 = instance.get(Calendar.HOUR_OF_DAY);
        System.out.println("1个小时以后"+i6);
        instance.set(Calendar.HOUR_OF_DAY,0);
        int i7 = instance.get(Calendar.HOUR_OF_DAY);
        System.out.println("Can个小时以后"+i7);



    }
    public static void getTest1(){
        MsgQueue.rellyOne = true;
    }
}
