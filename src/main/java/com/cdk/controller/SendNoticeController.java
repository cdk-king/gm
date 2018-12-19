package com.cdk.controller;

import com.cdk.result.Result;
import com.cdk.service.impl.SendNoticeServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.logging.Logger;

@RestController
public class SendNoticeController {
    private static Logger logger = Logger.getLogger(String.valueOf(SendNoticeController.class));

    public static final String Divider = "############################";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SendNoticeServiceImpl sendNoticeServiceImpl;

    @CrossOrigin
    @RequestMapping("/getSendNoticeSendType")
    public Result getSendNoticeSendType(@RequestBody Map map) {
        Result re = sendNoticeServiceImpl.getSendNoticeSendType();
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/getSendNoticeNoticeType")
    public Result getSendNoticeNoticeType(@RequestBody Map map) {
        Result re = sendNoticeServiceImpl.getSendNoticeNoticeType();
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/addNotice")
    public Result addNotice(@RequestBody Map map) {
        Result re = sendNoticeServiceImpl.addNotice(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/getNotice")
    public Result getNotice(@RequestBody Map map) {
        Result re = sendNoticeServiceImpl.getNotice(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/deleteAllNotice")
    public Result deleteAllNotice(@RequestBody Map map) {
        Result re = sendNoticeServiceImpl.deleteAllNotice(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/sendNotice")
    public Result sendNotice(@RequestBody Map map) {
        String strTimeInterval = (map.get("timeInterval") != null ? map.get("timeInterval").toString() : "");
        String strCycleTime = (map.get("cycleTime") != null ? map.get("cycleTime").toString() : "");
        int timeInterval = Integer.parseInt(strTimeInterval);
        int cycleTime = Integer.parseInt(strCycleTime);
        sendNotice(timeInterval, cycleTime, map);
        logger.info(Divider);
        return null;
    }

    Logger logger2 = Logger.getLogger("sendNotice");

    public void sendNotice(int timeInterval, int cycleTime, Map map) {
        new Thread() {
            public void run() {
                long a = System.currentTimeMillis();
                int c = 0;
                Boolean isContinue = true;
                logger2.info("startSendNotice");
                while (isContinue) {
                    long b = System.currentTimeMillis();
                    if ((b - a) > (1000 * timeInterval)) {
                        c++;
                        a = System.currentTimeMillis();
                        Result re = sendNoticeServiceImpl.sendNotice(map);
                        logger2.info(c + "-SendNoticeResult:" + re);
                    }
                    if (c >= cycleTime) {
                        isContinue = false;
                    }
                }
            }
        }.start();
    }
}