package com.cdk.controller;

import com.cdk.result.Result;
import com.cdk.service.impl.UserServiceImpl;

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
public class UserTableController {
    private static Logger logger = Logger.getLogger(String.valueOf(UserTableController.class));
    public static final String Divider = "############################";

    //1、@Autowired是spring自带的，@Inject是JSR330规范实现的，@Resource是JSR250规范实现的，需要导入不同的包
    //
    //2、@Autowired、@Inject用法基本一样，不同的是@Autowired有一个request属性
    //
    //3、@Autowired、@Inject是默认按照类型匹配的，@Resource是按照名称匹配的
    //
    //4、@Autowired如果需要按照名称匹配需要和@Qualifier一起使用，@Inject和@Name一起使用
    //替代setter注解成javaBean，实现单例模式
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @CrossOrigin
    @RequestMapping("/getUser")
    public Result getUser(@RequestBody Map map) {
        Result re = userServiceImpl.getUser(map);
        logger.info("UserData：" + re);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/user/getAllUser")
    public Result getAllUser() {
        Result re = userServiceImpl.getAllUser();
        logger.info(Divider);

        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/user/getUserById")
    public Result getUserById(@RequestBody Map map) {
        Result re = userServiceImpl.getUserById(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping(value = "/editUser", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result editUser(@RequestBody Map map) {
        Result re = userServiceImpl.editUser(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/addUser")
    public Result addUser(@RequestBody Map map) {
        Result re = userServiceImpl.addUser(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping(value = "/editPassword", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result editpassword(@RequestBody Map map) {
        Result re = userServiceImpl.editPassword(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping(value = "/changeStateToFrozen_User", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result changeStateToFrozen_User(@RequestBody Map map) {
        Result re = userServiceImpl.changeStateToFrozen_User(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping(value = "/changeStateToNormal_User", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result changeStateToNormal_User(@RequestBody Map map) {
        Result re = userServiceImpl.changeStateToNormal_User(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result deleteUser(@RequestBody Map map) {
        Result re = userServiceImpl.deleteUser(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping(value = "/deleteAllUser", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result deleteAllUser(@RequestBody Map map) {
        Result re = userServiceImpl.deleteAllUser(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/insertUserRoles")
    public Result insertUserRoles(@RequestBody Map map) {
        Result re = userServiceImpl.insertUserRoles(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping(value = "/deleteUserRoles", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result deleteUserRoles(@RequestBody Map map) {
        Result re = userServiceImpl.deleteUserRoles(map);
        logger.info(Divider);
        return re;
    }
}
