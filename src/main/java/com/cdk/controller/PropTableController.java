package com.cdk.controller;

import com.cdk.result.Result;
import com.cdk.service.impl.PropServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.logging.Logger;

@RestController
public class PropTableController {
    private static Logger logger = Logger.getLogger(String.valueOf(PropTableController.class));

    public static final String Divider = "############################";

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private PropServiceImpl propServiceImpl;

    @CrossOrigin
    @RequestMapping("/getProp")
    public Result getProp(@RequestBody Map map) {
        Result re = propServiceImpl.getProp(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/addProp")
    public Result addProp(@RequestBody Map map) {
        Result re = propServiceImpl.addProp(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/editProp")
    public Result editProp(@RequestBody Map map) {
        Result re = propServiceImpl.editProp(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/deleteProp")
    public Result deleteProp(@RequestBody Map map) {
        Result re = propServiceImpl.deleteProp(map);
        logger.info(Divider);
        return re;
    }

    @RequestMapping(value = "/changeStateToNormal_Prop", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result changeStateToNormal_Game(@RequestBody Map map) {
        Result re = propServiceImpl.changeStateToNormal_Game(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping(value = "/changeStateToFrozen_Prop", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result changeStateToFrozen_Game(@RequestBody Map map) {
        Result re = propServiceImpl.changeStateToFrozen_Game(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/deleteAllProp")
    public Result deleteAllProp(@RequestBody Map map) {
        Result re = propServiceImpl.deleteAllProp(map);
        logger.info(Divider);
        return re;
    }
}
