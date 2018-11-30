package com.cdk.controller;

import com.cdk.result.Result;
import com.cdk.service.impl.RegisterServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RegisterController {
    public static final String Divider = "############################";
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RegisterServiceImpl RegisterServiceImpl;

    @CrossOrigin
    @RequestMapping("/api/register/addRegisterUser")
    public Result addRegisterUser(@RequestBody Map map) {
        Result re = RegisterServiceImpl.addRegisterUser(map);
        System.out.println(Divider);
        return re;
    }
}
