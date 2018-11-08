package com.cdk.controller;

import com.cdk.result.Result;
import com.cdk.service.impl.EmailServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class EmailTableController {
    public static final String Divider = "############################";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmailServiceImpl emailServiceImpl;

    @RequestMapping("/getEmail")
    public Result getEmail(@RequestBody Map map) {
        Result re = emailServiceImpl.getEmail(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/addEmail")
    public Result addEmail(@RequestBody Map map) {
        Result re = emailServiceImpl.addEmail(map);
        System.out.println(Divider);
        return re;
    }
}
