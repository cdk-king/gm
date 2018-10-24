package com.cdk.app;

import com.cdk.entity.Demo;
import com.cdk.service.DemoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletRequest;

@Controller

public class helloController {
    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "HelloWorld";

    }

    @RequestMapping("/testResponseBody")
    @ResponseBody
    //@RestController注解相当于@ResponseBody ＋ @Controller
    public String testResponseBody(HttpServletRequest request) {
        return "<h1 style='color:lightGreen'>Hello Web!</h1>";
    }


}
