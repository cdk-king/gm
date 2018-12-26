package com.cdk.controller;

import com.cdk.result.Result;
import com.cdk.service.impl.BanIpServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.logging.Logger;

@RestController
public class BanIpController {
    private static Logger logger = Logger.getLogger(String.valueOf(BanIpController.class));
    public static final String Divider = "############################";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private BanIpServiceImpl banIpServiceImpl;

    @CrossOrigin
    @RequestMapping("/api/ip/getBanIp")
    public Result getBanIp(@RequestBody Map map) {
        Result re = banIpServiceImpl.getBanIp(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/ip/addBanIp")
    public Result addBanIp(@RequestBody Map map) {
        Result re = banIpServiceImpl.addBanIp(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/ip/banIp")
    public Result banIp(@RequestBody Map map) {
        Result re = banIpServiceImpl.banIp(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/ip/deleteBanIp")
    public Result deleteBanIp(@RequestBody Map map) {
        Result re = banIpServiceImpl.deleteBanIp(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/ip/deleteAllBanIp")
    public Result deleteAllBanIp(@RequestBody Map map) {
        Result re = banIpServiceImpl.deleteAllBanIp(map);
        return re;
    }
}
