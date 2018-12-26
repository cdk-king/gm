package com.cdk.controller;

import com.cdk.result.Result;
import com.cdk.service.impl.EmailServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.logging.Logger;

@RestController
public class EmailTableController {
    private static Logger logger = Logger.getLogger(String.valueOf(EmailTableController.class));
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmailServiceImpl emailServiceImpl;

    @CrossOrigin
    @RequestMapping("/getEmail")
    public Result getEmail(@RequestBody Map map) {
        Result re = emailServiceImpl.getEmail(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/addEmail")
    public Result addEmail(@RequestBody Map map) {
        Result re = emailServiceImpl.addEmail(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/editEmail")
    public Result editEmail(@RequestBody Map map) {
        Result re = emailServiceImpl.editEmail(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/sendEmail")
    public Result sendEmail(@RequestBody Map map) {
        Result re = emailServiceImpl.sendEmail(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/deleteEmail")
    public Result deleteEmail(@RequestBody Map map) {
        Result re = emailServiceImpl.deleteEmail(map);
        return re;
    }
}
