package com.cdk.controller;

import com.cdk.result.Result;
import com.cdk.service.impl.NewPropServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.logging.Logger;

@RestController
public class NewPropTableController {
    private static Logger logger = Logger.getLogger(String.valueOf(NewPropTableController.class));
    public static final String Divider = "############################";

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NewPropServiceImpl newPropServiceImpl;

    @CrossOrigin
    @RequestMapping("/ImportProp")
    public Result ImportProp(@RequestBody Map map) {
        Result re = newPropServiceImpl.ImportProp(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/getPropUplaod")
    public Result getPropUplaod(@RequestBody Map map) {
        Result re = newPropServiceImpl.getPropUplaod(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/newProp/getPropTypeList")
    public Result getPropTypeList(@RequestBody Map map) {
        Result re = newPropServiceImpl.getPropTypeList(map);
        logger.info(Divider);
        return re;
    }

}
