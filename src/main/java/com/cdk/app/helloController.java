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
//@RestController注解相当于@ResponseBody ＋ @Controller
public class helloController {
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "HelloWorld";

    }


//    @Resource
//    private DemoService demoService;

    @RequestMapping("/testResponseBody")
    @ResponseBody
    public String testResponseBody(HttpServletRequest request){
        return "<h1 style='color:lightGreen'>Hello Web!</h1>";
    }



}
