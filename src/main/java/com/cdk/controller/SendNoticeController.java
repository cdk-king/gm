package com.cdk.controller;

import com.cdk.result.Result;
import com.cdk.service.impl.SendNoticeServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@RestController
public class SendNoticeController {
    private static Logger logger = LoggerFactory.getLogger(SendNoticeController.class);

    public static final String Divider = "############################";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SendNoticeServiceImpl sendNoticeServiceImpl;

    @CrossOrigin
    @RequestMapping("/getSendNoticeSendType")
    public Result getSendNoticeSendType(@RequestBody Map map) {
        Result re = sendNoticeServiceImpl.getSendNoticeSendType();
        return re;
    }

    @CrossOrigin
    @RequestMapping("/getSendNoticeNoticeType")
    public Result getSendNoticeNoticeType(@RequestBody Map map) {
        Result re = sendNoticeServiceImpl.getSendNoticeNoticeType();
        return re;
    }

    @CrossOrigin
    @RequestMapping("/addNotice")
    public Result addNotice(@RequestBody Map map) {
        Result re = sendNoticeServiceImpl.addNotice(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/getNotice")
    public Result getNotice(@RequestBody Map map) {
        Result re = sendNoticeServiceImpl.getNotice(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/deleteAllNotice")
    public Result deleteAllNotice(@RequestBody Map map) {
        Result re = sendNoticeServiceImpl.deleteAllNotice(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/sendNotice")
    public Result sendNotice(@RequestBody Map map) {
        String strTimeInterval = (map.get("timeInterval") != null ? map.get("timeInterval").toString() : "");
        String strCycleTime = (map.get("cycleTime") != null ? map.get("cycleTime").toString() : "");
        String sendType = (map.get("sendType") != null ? map.get("sendType").toString() : "");
        int timeInterval = Integer.parseInt(strTimeInterval);
        int cycleTime = Integer.parseInt(strCycleTime);
        Result re = new Result(200, "广播已发送", "");
        if (Objects.equals(sendType, "1")) {
            re = sendNoticeServiceImpl.sendNotice(map);
        } else if (Objects.equals(sendType, "2")) {
            sendNotice(timeInterval, cycleTime, map);
        }
        return re;
    }

    public void sendNotice(int timeInterval, int cycleTime, Map map) {
        new Thread() {
            public void run() {
                long a = System.currentTimeMillis();
                int c = 0;
                Boolean isContinue = true;
                while (isContinue) {
                    long b = System.currentTimeMillis();
                    if ((b - a) > (1000 * timeInterval)) {
                        c++;
                        a = System.currentTimeMillis();
                        Result re = sendNoticeServiceImpl.sendNotice(map);
                    }
                    if (c >= cycleTime) {
                        isContinue = false;
                    }
                    try {
                        this.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
