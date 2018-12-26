package com.cdk.controller;


import com.cdk.result.Result;
import com.cdk.service.impl.NewGiftServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.logging.Logger;

@RestController
public class NewGiftTableController {
    private static Logger logger = Logger.getLogger(String.valueOf(NewGiftTableController.class));

    public static final String Divider = "############################";

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
