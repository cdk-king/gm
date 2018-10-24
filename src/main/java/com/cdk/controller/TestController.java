package com.cdk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TestController {

    public static final String  Divider= "############################";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //org.springframework.ui.Model
    //事实上model数据，最终spring也是写到HttpServletRequest属性中，只是用model更符合mvc设计,减少各层间耦合。
    @RequestMapping("/testModel")
    public ModelAndView index(Model model){
        model.addAttribute("name", "CDK");
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @RequestMapping("/testRequest")
    public ModelAndView request(HttpServletRequest request){
        request.setAttribute("name", "Main world");
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

}
