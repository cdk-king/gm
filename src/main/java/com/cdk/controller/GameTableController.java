package com.cdk.controller;

import com.cdk.result.Result;
import com.cdk.service.impl.GameServiceImpl;

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
public class GameTableController {
    public static final String Divider = "############################";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private GameServiceImpl gameServiceImpl;

    @CrossOrigin
    @RequestMapping("/getAllGame")
    public Result getAllGame(@RequestBody Map map) {
        Result re = gameServiceImpl.getAllGame(map);
        System.out.println(Divider);
        return re;
    }

    @Transactional
    @RequestMapping(value = "/editGame", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result editGame(@RequestBody Map map) {
        Result re = gameServiceImpl.editGame(map);
        System.out.println(Divider);
        return re;
    }

    @Transactional
    @RequestMapping("/addGame")
    public Result addGame(@RequestBody Map map) {
        Result re = gameServiceImpl.addGame(map);
        System.out.println(Divider);
        return re;
    }

    @Transactional
    @RequestMapping(value = "/deleteGame", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result deleteGame(@RequestBody Map map) {
        Result re = gameServiceImpl.deleteGame(map);
        System.out.println(Divider);
        return re;
    }


    @RequestMapping(value = "/changeStateToNormal_Game", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result changeStateToNormal_Game(@RequestBody Map map) {
        Result re = gameServiceImpl.changeStateToNormal_Game(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping(value = "/changeStateToFrozen_Game", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result changeStateToFrozen_Game(@RequestBody Map map) {
        Result re = gameServiceImpl.changeStateToFrozen_Game(map);
        System.out.println(Divider);
        return re;
    }

    @Transactional
    @RequestMapping(value = "/deleteAllGame", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result deleteAllGame(@RequestBody Map<String, String> map) {
        Result re = gameServiceImpl.deleteAllGame(map);
        System.out.println(Divider);
        return re;
    }
}
