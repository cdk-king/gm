package com.cdk.controller;

import com.cdk.result.Result;
import com.cdk.service.impl.CDK_ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CDK_TableController {

    public static final String Divider = "############################";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CDK_ServiceImpl cdkServiceImpl;

    @RequestMapping("/getCDK")
    public Result getCDK(@RequestBody Map map) {
        Result re = cdkServiceImpl.getCDK(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/exchangeCDK")
    public Result exchangeCDK(@RequestBody Map map) {
        Result re = cdkServiceImpl.exchangeCDK(map);
        System.out.println(Divider);
        return re;
    }
}
