package com.cdk.timer;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerClass {
    private static Long delay = 2000L;
    private static Long period = 5000L;

    //    public static void main(String[] args) {
    //        timer1(delay);
    //        timer2(delay,period);
    //        timer3(delay,period);
    //        timer4();
    //    }

    // 第一种方法：设定指定任务task在指定时间time执行 schedule(TimerTask task, Date time)
    public static void timer1(long delay) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                System.out.println("-------设定要指定任务--------");
            }
        }, delay);// 设定指定的时间time,此处为2000毫秒
    }

    // 第二种方法：设定指定任务task在指定延迟delay后进行固定延迟peroid的执行
    // schedule(TimerTask task, long delay, long period)
    public static void timer2(long delay, long period) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                System.out.println("-------设定要指定任务--------");
            }
        }, delay, period);
    }

    // 第三种方法：设定指定任务task在指定延迟delay后进行固定频率peroid的执行。
    // scheduleAtFixedRate(TimerTask task, long delay, long period)
    public static void timer3(long delay, long period) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                System.out.println("-------设定要指定任务--------");
            }
        }, delay, period);
    }

    // 第四种方法：安排指定的任务task在指定的时间firstTime开始进行重复的固定速率period执行．
    // Timer.scheduleAtFixedRate(TimerTask task,Date firstTime,long period)
    public static void timer4() {

        //        Calendar calendar = Calendar.getInstance();
        //        int year = calendar.get(Calendar.YEAR);
        //        int month = calendar.get(Calendar.MONTH);
        //        int day = calendar.get(Calendar.DAY_OF_MONTH);
        //        /**
        //         *  定制每天的8:00:00执行，若程序已超过8点启动,当天不再执行，等到明日八点再执行
        //         *  这样保证了时间一直是8点，而不会变成程序启动时间
        //         */
        //        calendar.set(year, month, day, 8, 00, 00);
        //        Date defaultdate = calendar.getTime();// 今天8点（默认发送时间）


        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 12); // 控制时
        calendar.set(Calendar.MINUTE, 0);    // 控制分
        calendar.set(Calendar.SECOND, 0);    // 控制秒

        Date time = calendar.getTime();     // 得出执行任务的时间,此处为今天的12：00：00

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                System.out.println("-------设定要指定任务--------");
            }
        }, time, 1000 * 60 * 60 * 24);// 这里设定将延时每天固定执行
    }

}
