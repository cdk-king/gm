package com.cdk.controller;

import com.cdk.result.Result;
import com.cdk.service.impl.PlayerServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PlayerTableController {
    public static final String Divider = "############################";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PlayerServiceImpl playerServiceImpl;

    @RequestMapping("/getPlayer")
    public Result getPlayer(@RequestBody Map map) {
        Result re = playerServiceImpl.getPlayer(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/ChangeToProhibitSpeak")
    public Result ChangeToProhibitSpeak(@RequestBody Map map) {
        Result re = playerServiceImpl.ChangeToProhibitSpeak(map);
        System.out.println(Divider);
        return re;
    }


    @RequestMapping("/ImportPlayer")
    public Result ImportPlayer(@RequestBody Map map) {
        Result re = playerServiceImpl.ImportPlayer(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/ChangeProhibitSpeakToNormal")
    public Result ChangeProhibitSpeakToNormal(@RequestBody Map map) {
        Result re = playerServiceImpl.ChangeProhibitSpeakToNormal(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/ChangeToBan")
    public Result ChangeToBan(@RequestBody Map map) {
        Result re = playerServiceImpl.ChangeToBan(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/ChangeBanToNormal")
    public Result ChangeBanToNormal(@RequestBody Map map) {
        Result re = playerServiceImpl.ChangeBanToNormal(map);
        System.out.println(Divider);
        return re;
    }

}
