package com.cdk.controller;


import com.cdk.result.Result;
import com.cdk.service.impl.NewGiftServiceImpl;

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
public class NewGiftTableController {
    private static Logger logger = LoggerFactory.getLogger(NewGiftTableController.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NewGiftServiceImpl newGiftServiceImpl;

    @CrossOrigin
    @RequestMapping("/api/gift/ImportGift")
    public Result ImportGift(@RequestBody Map map) {
        Result re = newGiftServiceImpl.ImportGift(map);
        return re;
    }


    @CrossOrigin
    @RequestMapping("/getGiftUpload")
    public Result getGiftUpload(@RequestBody Map map) {
        Result re = newGiftServiceImpl.getGiftUpload(map);
        return re;
    }
}
