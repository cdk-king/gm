package com.cdk.controller;

import com.cdk.result.Result;
import com.cdk.service.impl.PlatformNoticeServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PlatformNoticeController {
    public static final String Divider = "############################";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PlatformNoticeServiceImpl platformNoticeServiceImpl;


    @RequestMapping("/getPlatformNotice")
    public Result getPlatformNotice(@RequestBody Map map) {
        Result re = platformNoticeServiceImpl.getPlatformNotice(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/addPlatformNotice")
    public Result addPlatformNotice(@RequestBody Map map) {
        Result re = platformNoticeServiceImpl.addPlatformNotice(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/editPlatformNotice")
    public Result editPlatformNotice(@RequestBody Map map) {
        Result re = platformNoticeServiceImpl.editPlatformNotice(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/deletePlatformNotice")
    public Result deletePlatformNotice(@RequestBody Map map) {
        Result re = platformNoticeServiceImpl.deletePlatformNotice(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/sendPlatformNotice")
    public Result sendPlatformNotice(@RequestBody Map map) {
        Result re = platformNoticeServiceImpl.sendPlatformNotice(map);
        System.out.println(Divider);
        return re;
    }


    @RequestMapping("/deleteAllPlatformNotice")
    public Result deleteAllPlatformNotice(@RequestBody Map map) {
        Result re = platformNoticeServiceImpl.deleteAllPlatformNotice(map);
        System.out.println(Divider);
        return re;
    }
}
