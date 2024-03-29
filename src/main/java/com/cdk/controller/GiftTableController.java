package com.cdk.controller;


import com.cdk.result.Result;
import com.cdk.service.impl.GiftServiceImpl;

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
public class GiftTableController {
    private static Logger logger = LoggerFactory.getLogger(GiftTableController.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private GiftServiceImpl giftServiceImpl;

    @CrossOrigin
    @RequestMapping("/getGift")
    public Result getGift(@RequestBody Map map) {
        Result re = giftServiceImpl.getGift(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/addGift")
    public Result addGift(@RequestBody Map map) {
        Result re = giftServiceImpl.addGift(map);
        return re;
    }
}
