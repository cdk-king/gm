package com.cdk.controller;

import com.cdk.entity.User;
import com.cdk.result.Result;
import com.cdk.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {

    //Spring模拟HTTP请求——RestTemplate类
    private RestTemplate restTemplate;



    @GetMapping(value="/userConsumer/{id}")
    public User findById(@PathVariable Long id){
        return restTemplate.getForObject("/user/findById/"+id, User.class);
    }

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/getUserDao")
    public String getUserDao() {
        Result re = userServiceImpl.insert("cdk","cdk");
        //System.out.println(re);
        return "/getUserDao";
    }
    @GetMapping("/getTest")
    public String getTest() {
        return "/getTest";
    }
}
