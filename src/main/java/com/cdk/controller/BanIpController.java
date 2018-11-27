package com.cdk.controller;

import com.cdk.result.Result;
import com.cdk.service.impl.BanIpServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class BanIpController {

    public static final String Divider = "############################";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private BanIpServiceImpl banIpServiceImpl;

    @RequestMapping("/api/ip/getBanIp")
    public Result getBanIp(@RequestBody Map map) {
        Result re = banIpServiceImpl.getBanIp(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/api/ip/addBanIp")
    public Result addBanIp(@RequestBody Map map) {
        Result re = banIpServiceImpl.addBanIp(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/api/ip/banIp")
    public Result banIp(@RequestBody Map map) {
        Result re = banIpServiceImpl.banIp(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/api/ip/deleteBanIp")
    public Result deleteBanIp(@RequestBody Map map) {
        Result re = banIpServiceImpl.deleteBanIp(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/api/ip/deleteAllBanIp")
    public Result deleteAllBanIp(@RequestBody Map map) {
        Result re = banIpServiceImpl.deleteAllBanIp(map);
        System.out.println(Divider);
        return re;
    }
}
