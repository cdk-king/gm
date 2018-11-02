package com.cdk.controller;

import com.cdk.result.Result;
import com.cdk.service.impl.AppleGiftCDK_ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AppleGiftCDKController {

    public static final String Divider = "############################";
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private AppleGiftCDK_ServiceImpl appleGiftCDK_ServiceImpl;

    @RequestMapping("/generateCDK")
    public Result generateCDK(@RequestBody Map map) {
        Result re = appleGiftCDK_ServiceImpl.generateCDK(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/analyseCDK")
    public Result analyseCDK(@RequestBody Map map) {
        Result re = appleGiftCDK_ServiceImpl.analyseCDK(map);
        System.out.println(Divider);
        return re;
    }


    @RequestMapping("/getGiftListForPlatformId")
    public Result getGiftListForPlatformId(@RequestBody Map map) {
        Result re = appleGiftCDK_ServiceImpl.getGiftListForPlatformId(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/getNewGiftListForPlatformId")
    public Result getNewGiftListForPlatformId(@RequestBody Map map) {
        Result re = appleGiftCDK_ServiceImpl.getNewGiftListForPlatformId(map);
        System.out.println(Divider);
        return re;
    }
}
