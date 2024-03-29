package com.cdk.controller;

import com.cdk.result.Result;
import com.cdk.service.impl.PlatformEmailServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PlatformEmailController {
    private static Logger logger = LoggerFactory.getLogger(PlatformEmailController.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PlatformEmailServiceImpl platformEmailServiceImpl;

    @CrossOrigin
    @RequestMapping("/getPlatformEmail")
    public Result getPlatformEmail(@RequestBody Map map) {
        Result re = platformEmailServiceImpl.getPlatformEmail(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/editPlatformEmail")
    public Result editPlatformEmail(@RequestBody Map map) {
        Result re = platformEmailServiceImpl.editPlatformEmail(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/addPlatformEmail")
    public Result addPlatformEmail(@RequestBody Map map) {
        Result re = platformEmailServiceImpl.addPlatformEmail(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/deletePlatformEmail")
    public Result deletePlatformNotice(@RequestBody Map map) {
        Result re = platformEmailServiceImpl.deletePlatformEmail(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/sendPlatformEmail")
    public Result sendPlatformEmail(@RequestBody Map map) {
        Result re = platformEmailServiceImpl.sendPlatformEmail(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/deleteAllPlatformEmail")
    public Result deleteAllPlatformEmail(@RequestBody Map map) {
        Result re = platformEmailServiceImpl.deleteAllPlatformEmail(map);
        return re;
    }
}
