package com.cdk.controller;

import com.cdk.result.Result;
import com.cdk.service.impl.NewPropServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class NewPropTableController {
    private static Logger logger = LoggerFactory.getLogger(NewPropTableController.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NewPropServiceImpl newPropServiceImpl;

    @CrossOrigin
    @RequestMapping("/ImportProp")
    public Result ImportProp(@RequestBody Map map) {
        Result re = newPropServiceImpl.ImportProp(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/getPropUplaod")
    public Result getPropUplaod(@RequestBody Map map) {
        Result re = newPropServiceImpl.getPropUplaod(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/newProp/getPropTypeList")
    public Result getPropTypeList(@RequestBody Map map) {
        Result re = newPropServiceImpl.getPropTypeList(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/newProp/deleteAllPropForPlatform")
    public Result deleteAllPropForPlatform(@RequestBody Map map) {
        Result re = newPropServiceImpl.deleteAllPropForPlatform(map);
        return re;
    }
}
