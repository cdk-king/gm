package com.cdk.controller;

import com.cdk.result.Result;
import com.cdk.service.impl.RightServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RightTableController {
    private static Logger logger = LoggerFactory.getLogger(RightTableController.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RightServiceImpl rightServiceImpl;

    @CrossOrigin
    @RequestMapping("/getRight")
    public Result getRight(@RequestBody Map map) {
        Result re = rightServiceImpl.getRight(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping(value = "/editRight", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result editRight(@RequestBody Map map) {
        Result re = rightServiceImpl.editRight(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/addRight")
    public Result addRight(@RequestBody Map map) {
        Result re = rightServiceImpl.addRight(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping(value = "/changeStateToFrozen_Right", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result changeStateToFrozen_Right(@RequestBody Map map) {
        Result re = rightServiceImpl.changeStateToFrozen_Right(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping(value = "/changeStateToNormal_Right", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result changeStateToNormal_Right(@RequestBody Map map) {
        Result re = rightServiceImpl.changeStateToNormal_Right(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping(value = "/deleteRight", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result deleteRight(@RequestBody Map map) {
        Result re = rightServiceImpl.deleteRight(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping(value = "/deleteAllRight", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result deleteAllRight(@RequestBody Map<String, String> map) {
        Result re = rightServiceImpl.deleteAllRight(map);
        return re;
    }


}
