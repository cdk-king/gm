package com.cdk.controller;

import com.cdk.result.Result;
import com.cdk.service.impl.ChannelServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.logging.Logger;

@RestController
public class ChannelController {
    private static Logger logger = Logger.getLogger(String.valueOf(ChannelController.class));
    public static final String Divider = "############################";
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ChannelServiceImpl channelServiceImpl;

    @CrossOrigin
    @RequestMapping("/api/channel/getAllChannel")
    public Result getAllChannel(@RequestBody Map map) {
        Result re = channelServiceImpl.getAllChannel(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/channel/getAllChannelFormPlatform")
    public Result getAllChannelFormPlatform(@RequestBody Map map) {
        Result re = channelServiceImpl.getAllChannelFormPlatform(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/channel/getChannel")
    public Result getChannel(@RequestBody Map map) {
        Result re = channelServiceImpl.getChannel(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/channel/getChannelTable")
    public Result getChannelTable(@RequestBody Map map) {
        Result re = channelServiceImpl.getChannelTable(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/channel/addChannel")
    public Result addChannel(@RequestBody Map map) {
        Result re = channelServiceImpl.addChannel(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/channel/saveCheckChannel")
    public Result saveCheckChannel(@RequestBody Map map) {
        Result re = channelServiceImpl.saveCheckChannel(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/channel/saveAllCheckChannel")
    public Result saveAllCheckChannel(@RequestBody Map map) {
        Result re = channelServiceImpl.saveAllCheckChannel(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/channel/editChannel")
    public Result editChannel(@RequestBody Map map) {
        Result re = channelServiceImpl.editChannel(map);
        logger.info(Divider);
        return re;
    }


    @CrossOrigin
    @RequestMapping("/api/channel/deleteChannel")
    public Result deleteChannel(@RequestBody Map map) {
        Result re = channelServiceImpl.deleteChannel(map);
        logger.info(Divider);
        return re;
    }
}
