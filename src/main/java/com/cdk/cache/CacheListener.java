package com.cdk.cache;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

public class CacheListener {
    Logger logger = Logger.getLogger("cacheLog");
    public CacheManagerImpl cacheManagerImpl;

    @Autowired
    public CacheListener(CacheManagerImpl cacheManagerImpl) {
        this.cacheManagerImpl = cacheManagerImpl;

    }

    public void startListenUseThread(String key) {
        logger.info("beforeStartListen");
        new Thread() {
            public void run() {
                long a = System.currentTimeMillis();
                int c = 0;
                logger.info("startListen");
                while (true) {
                    long b = System.currentTimeMillis();
                    if ((b - a) > 1000) {
                        c++;
                        a = System.currentTimeMillis();
                    }
                    if (cacheManagerImpl.isTimeOut(key)) {
                        cacheManagerImpl.clearByKey(key);
                        logger.info("time:" + c + "s");
                        logger.info(key + "缓存被清除");
                    }
                }
            }
        }.start();
    }

    public void startAllListenUseThread() {
        logger.info("beforeStartListen");
        new Thread() {
            public void run() {
                long a = System.currentTimeMillis();
                int c = 0;
                logger.info("startListen");
                while (true) {
                    long b = System.currentTimeMillis();
                    if ((b - a) > 10000) {
                        c = c + 10;
                        a = System.currentTimeMillis();
                        logger.info("time:" + dealTime(c));
                        for (String key : cacheManagerImpl.getAllKeys()) {
                            if (cacheManagerImpl.isTimeOut(key)) {
                                cacheManagerImpl.clearByKey(key);

                                logger.info(key + "缓存被清除");
                            }
                        }
                    }

                }
            }
        }.start();
    }

    /**
     * 时间转换
     * 可将秒数转变为可读的时间格式
     * @param value
     * @return
     */
    public String dealTime(int value) {
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

    public void startListenUseTimer(String key, long delay) {
        logger.info("beforeStartListen");
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                cacheManagerImpl.clearByKey(key);
                logger.info(key + "缓存被清除");
            }
        }, delay);
    }

    public void startListenUseTimer(String key) {
        logger.info("beforeStartListen");
        EntityCache cache = cacheManagerImpl.getCacheByKey(key);
        long delay = cache.getTimeOut();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                cacheManagerImpl.clearByKey(key);
                logger.info(key + "缓存被清除");
            }
        }, delay);
    }

    public void startAllListenUseTimer() {
        logger.info("beforeStartListen");
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                for (String key : cacheManagerImpl.getAllKeys()) {
                    if (cacheManagerImpl.isTimeOut(key)) {
                        cacheManagerImpl.clearByKey(key);
                        logger.info(key + "缓存被清除");
                    }
                }
            }
        }, 0, 1000);
    }


}
