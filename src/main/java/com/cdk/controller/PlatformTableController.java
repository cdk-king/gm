package com.cdk.controller;

import com.cdk.result.Result;
import com.cdk.service.impl.PlatformServiceImpl;

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

import javax.transaction.Transactional;

@RestController
public class PlatformTableController {
    private static Logger logger = LoggerFactory.getLogger(PlatformTableController.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PlatformServiceImpl platformServiceImpl;

    @CrossOrigin
    @RequestMapping("/getAllPlatform")
    public Result getAllPlatform(@RequestBody Map map) {
        Result re = platformServiceImpl.getAllPlatform(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/getAllGameList")
    public Result getAllGameList(@RequestBody Map map) {
        Result re = platformServiceImpl.getAllGameList(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/getAllRoleList")
    public Result getAllRoleList(@RequestBody Map map) {
        Result re = platformServiceImpl.getAllRoleList(map);
        return re;
    }

    @CrossOrigin
    @Transactional
    @RequestMapping("/addPlatform")
    public Result addPlatform(@RequestBody Map map) {
        Result re = platformServiceImpl.addPlatform(map);
        return re;
    }

    @CrossOrigin
    @Transactional
    @RequestMapping(value = "/editPlatform", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result editPlatform(@RequestBody Map map) {
        Result re = platformServiceImpl.editPlatform(map);
        return re;
    }

    @CrossOrigin
    @Transactional
    @RequestMapping(value = "/deletePlatform", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result deletePlatform(@RequestBody Map map) {
        Result re = platformServiceImpl.deletePlatform(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping(value = "/changeStateToNormal_Platform", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result changeStateToNormal_Platform(@RequestBody Map map) {
        Result re = platformServiceImpl.changeStateToNormal_Platform(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping(value = "/changeStateToFrozen_Platform", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result changeStateToFrozen_Platform(@RequestBody Map map) {
        Result re = platformServiceImpl.changeStateToFrozen_Platform(map);
        return re;
    }

    @CrossOrigin
    @Transactional
    @RequestMapping(value = "/deleteAllPlatform", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result deleteAllPlatform(@RequestBody Map map) {
        Result re = platformServiceImpl.deleteAllPlatform(map);
        return re;
    }

}
