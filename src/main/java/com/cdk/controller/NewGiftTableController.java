package com.cdk.controller;


import com.cdk.result.Result;
import com.cdk.service.impl.NewGiftServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class NewGiftTableController {


    public static final String Divider = "############################";

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NewGiftServiceImpl newGiftServiceImpl;

    @RequestMapping("/api/gift/ImportGift")
    public Result ImportGift(@RequestBody Map map) {
        Result re = newGiftServiceImpl.ImportGift(map);
        System.out.println(Divider);
        return re;
    }


    @RequestMapping("/getGiftUpload")
    public Result getGiftUpload(@RequestBody Map map) {
        Result re = newGiftServiceImpl.getGiftUpload(map);
        System.out.println(Divider);
        return re;
    }
}
