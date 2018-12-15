package com.cdk.controller;

import com.cdk.result.Result;
import com.cdk.service.impl.ApplyGiftCDK_ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ApplyGiftCDKController {

    public static final String Divider = "############################";
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ApplyGiftCDK_ServiceImpl applyGiftCDK_ServiceImpl;

    @CrossOrigin
    @RequestMapping("/generateCDK")
    public Result generateCDK(@RequestBody Map map) {
        Result re = applyGiftCDK_ServiceImpl.generateCDK(map);
        System.out.println(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/analyseCDK")
    public Result analyseCDK(@RequestBody Map map) {
        Result re = applyGiftCDK_ServiceImpl.analyseCDK(map);
        System.out.println(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/getGiftListForPlatformId")
    public Result getGiftListForPlatformId(@RequestBody Map map) {
        Result re = applyGiftCDK_ServiceImpl.getGiftListForPlatformId(map);
        System.out.println(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/getNewGiftListForPlatformId")
    public Result getNewGiftListForPlatformId(@RequestBody Map map) {
        Result re = applyGiftCDK_ServiceImpl.getNewGiftListForPlatformId(map);
        System.out.println(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/cdk/getCoupon")
    public Result getCoupon(@RequestBody Map map) {
        Result re = applyGiftCDK_ServiceImpl.getCoupon(map);
        System.out.println(Divider);
        return re;
    }

}
