package com.cdk.controller;

import com.cdk.result.Result;
import com.cdk.service.impl.PlayerServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.logging.Logger;

@RestController
public class PlayerTableController {
    private static Logger logger = Logger.getLogger(String.valueOf(PlayerTableController.class));
    public static final String Divider = "############################";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PlayerServiceImpl playerServiceImpl;

    @CrossOrigin
    @RequestMapping("/api/player/getPlayer")
    public Result getPlayer(@RequestBody Map map) {
        Result re = playerServiceImpl.getPlayer(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/player/getPlayerFromServer")
    public Result getPlayerFromServer(@RequestBody Map map) {
        Result re = playerServiceImpl.getPlayerFromServer(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/player/getPlayerDetailInfo")
    public Result getPlayerDetailInfo(@RequestBody Map map) {
        Result re = playerServiceImpl.getPlayerDetailInfo(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/player/talkBan")
    public Result talkBan(@RequestBody Map map) {
        Result re = playerServiceImpl.talkBan(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/player/Ban")
    public Result Ban(@RequestBody Map map) {
        Result re = playerServiceImpl.Ban(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/ChangeToProhibitSpeak")
    public Result ChangeToProhibitSpeak(@RequestBody Map map) {
        Result re = playerServiceImpl.ChangeToProhibitSpeak(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/ImportPlayer")
    public Result ImportPlayer(@RequestBody Map map) {
        Result re = playerServiceImpl.ImportPlayer(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/ChangeProhibitSpeakToNormal")
    public Result ChangeProhibitSpeakToNormal(@RequestBody Map map) {
        Result re = playerServiceImpl.ChangeProhibitSpeakToNormal(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/ChangeToBan")
    public Result ChangeToBan(@RequestBody Map map) {
        Result re = playerServiceImpl.ChangeToBan(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/ChangeBanToNormal")
    public Result ChangeBanToNormal(@RequestBody Map map) {
        Result re = playerServiceImpl.ChangeBanToNormal(map);
        logger.info(Divider);
        return re;
    }

}
