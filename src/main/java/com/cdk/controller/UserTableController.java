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

@RestController
public class UserTableController {

    public static final String Divider = "############################";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @CrossOrigin
    @RequestMapping("/getUser")
    public Result getUser(@RequestBody Map map) {
        Result re = userServiceImpl.getUser(map);
        System.out.println("UserData：" + re);
        System.out.println(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/user/getAllUser")
    public Result getAllUser() {
        Result re = userServiceImpl.getAllUser();
        System.out.println("UserData：" + re);
        System.out.println(Divider);

        return re;
    }

    @RequestMapping(value = "/editUser", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result editUser(@RequestBody Map map) {
        Result re = userServiceImpl.editUser(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/addUser")
    public Result addUser(@RequestBody Map map) {
        Result re = userServiceImpl.addUser(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping(value = "/editPassword", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result editpassword(@RequestBody Map map) {
        Result re = userServiceImpl.editPassword(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping(value = "/changeStateToFrozen_User", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result changeStateToFrozen_User(@RequestBody Map map) {
        Result re = userServiceImpl.changeStateToFrozen_User(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping(value = "/changeStateToNormal_User", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result changeStateToNormal_User(@RequestBody Map map) {
        Result re = userServiceImpl.changeStateToNormal_User(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result deleteUser(@RequestBody Map map) {
        Result re = userServiceImpl.deleteUser(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping(value = "/deleteAllUser", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result deleteAllUser(@RequestBody Map map) {
        Result re = userServiceImpl.deleteAllUser(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/insertUserRoles")
    public Result insertUserRoles(@RequestBody Map map) {
        Result re = userServiceImpl.insertUserRoles(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping(value = "/deleteUserRoles", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result deleteUserRoles(@RequestBody Map map) {
        Result re = userServiceImpl.deleteUserRoles(map);
        System.out.println(Divider);
        return re;
    }
}
