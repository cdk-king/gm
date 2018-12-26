package com.cdk.controller;

import com.cdk.result.Result;
import com.cdk.service.impl.PlayerLogServiceImpl;

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
public class PlayerLogTableController {
    private static Logger logger = LoggerFactory.getLogger(PlayerLogTableController.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PlayerLogServiceImpl playerLogServiceImpl;

    @CrossOrigin
    @RequestMapping("/getPlayerProhibitSpeakLog")
    public Result getPlayerProhibitSpeakLog(@RequestBody Map map) {
        Result re = playerLogServiceImpl.getPlayerProhibitSpeakLog(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/getPlayerBan")
    public Result getPlayerBan(@RequestBody Map map) {
        Result re = playerLogServiceImpl.getPlayerBan(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/log/getGoodFlowLog")
    public Result getGoodFlowLog(@RequestBody Map map) {
        Result re = playerLogServiceImpl.getGoodFlowLog(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/log/getMoneyFlowLog")
    public Result getMoneyFlowLog(@RequestBody Map map) {
        Result re = playerLogServiceImpl.getMoneyFlowLog(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/log/getCreateRoleLog")
    public Result getCreateRoleLog(@RequestBody Map map) {
        Result re = playerLogServiceImpl.getCreateRoleLog(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/log/getRoleLoginLog")
    public Result getRoleLoginLog(@RequestBody Map map) {
        Result re = playerLogServiceImpl.getRoleLoginLog(map);
        return re;
    }
}
