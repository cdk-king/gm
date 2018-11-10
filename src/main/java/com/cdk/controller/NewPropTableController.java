package com.cdk.controller;

import com.cdk.result.Result;
import com.cdk.service.impl.NewPropServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class NewPropTableController {

    public static final String Divider = "############################";

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NewPropServiceImpl newPropServiceImpl;

    @RequestMapping("/ImportProp")
    public Result ImportProp(@RequestBody Map map) {
        Result re = newPropServiceImpl.ImportProp(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/getPropUplaod")
    public Result getPropUplaod(@RequestBody Map map) {
        Result re = newPropServiceImpl.getPropUplaod(map);
        System.out.println(Divider);
        return re;
    }

}
