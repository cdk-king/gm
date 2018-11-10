package com.cdk.controller;

import com.cdk.result.Result;
import com.cdk.service.impl.SendNoticeServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SendNoticeController {


    public static final String Divider = "############################";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SendNoticeServiceImpl sendNoticeServiceImpl;


    @RequestMapping("/getSendNoticeSendType")
    public Result getSendNoticeSendType(@RequestBody Map map) {
        Result re = sendNoticeServiceImpl.getSendNoticeSendType();
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/getSendNoticeNoticeType")
    public Result getSendNoticeNoticeType(@RequestBody Map map) {
        Result re = sendNoticeServiceImpl.getSendNoticeNoticeType();
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/addNotice")
    public Result addNotice(@RequestBody Map map) {
        Result re = sendNoticeServiceImpl.addNotice(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/getNotice")
    public Result getNotice(@RequestBody Map map) {
        Result re = sendNoticeServiceImpl.getNotice(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/deleteAllNotice")
    public Result deleteAllNotice(@RequestBody Map map) {
        Result re = sendNoticeServiceImpl.deleteAllNotice(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/sendNotice")
    public Result sendNotice(@RequestBody Map map) {
        Result re = sendNoticeServiceImpl.sendNotice(map);
        System.out.println(Divider);
        return re;
    }
}
