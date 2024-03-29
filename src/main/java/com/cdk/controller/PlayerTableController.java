package com.cdk.controller;

import com.cdk.result.Result;
import com.cdk.service.impl.PlayerServiceImpl;

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
public class PlayerTableController {
    private static Logger logger = LoggerFactory.getLogger(PlayerTableController.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PlayerServiceImpl playerServiceImpl;

    @CrossOrigin
    @RequestMapping("/api/player/getPlayer")
    public Result getPlayer(@RequestBody Map map) {
        Result re = playerServiceImpl.getPlayer(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/player/getPlayerFromServer")
    public Result getPlayerFromServer(@RequestBody Map map) {
        Result re = playerServiceImpl.getPlayerFromServer(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/player/getPlayerDetailInfo")
    public Result getPlayerDetailInfo(@RequestBody Map map) {
        Result re = playerServiceImpl.getPlayerDetailInfo(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/player/talkBan")
    public Result talkBan(@RequestBody Map map) {
        Result re = playerServiceImpl.talkBan(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/player/Ban")
    public Result Ban(@RequestBody Map map) {
        Result re = playerServiceImpl.Ban(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/ChangeToProhibitSpeak")
    public Result ChangeToProhibitSpeak(@RequestBody Map map) {
        Result re = playerServiceImpl.ChangeToProhibitSpeak(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/ImportPlayer")
    public Result ImportPlayer(@RequestBody Map map) {
        Result re = playerServiceImpl.ImportPlayer(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/ChangeProhibitSpeakToNormal")
    public Result ChangeProhibitSpeakToNormal(@RequestBody Map map) {
        Result re = playerServiceImpl.ChangeProhibitSpeakToNormal(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/ChangeToBan")
    public Result ChangeToBan(@RequestBody Map map) {
        Result re = playerServiceImpl.ChangeToBan(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/ChangeBanToNormal")
    public Result ChangeBanToNormal(@RequestBody Map map) {
        Result re = playerServiceImpl.ChangeBanToNormal(map);
        return re;
    }

}
