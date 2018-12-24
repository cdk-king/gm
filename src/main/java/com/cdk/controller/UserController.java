package com.cdk.controller;

import com.cdk.entity.User;
import com.cdk.service.impl.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@RestController
public class UserController {
    private static Logger logger = Logger.getLogger(String.valueOf(UserController.class));
    //Spring模拟HTTP请求——RestTemplate类
    private RestTemplate restTemplate;


    @GetMapping(value = "/userConsumer/{id}")
    public User findById(@PathVariable Long id) {
        return restTemplate.getForObject("/user/findById/" + id, User.class);
    }

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/getTest")
    public String getTest() {
        return "/getTest";
    }
}
