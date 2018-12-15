package com.cdk.controller;

import com.cdk.result.Result;
import com.cdk.service.impl.RoleServiceImpl;

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
public class RoleTableController {

    public static final String Divider = "############################";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RoleServiceImpl roleServiceImpl;

    @CrossOrigin
    @RequestMapping("/getRole")
    public Result getRole(@RequestBody Map map) {
        Result re = roleServiceImpl.getRole(map);
        System.out.println(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/role/getRoleById")
    public Result getRoleById(@RequestBody Map map) {
        Result re = roleServiceImpl.getRoleById(map);
        System.out.println(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping(value = "/editRole", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result editRole(@RequestBody Map map) {
        Result re = roleServiceImpl.editRole(map);
        System.out.println(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/addRole")
    public Result addRole(@RequestBody Map map) {
        Result re = roleServiceImpl.addRole(map);
        System.out.println(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping(value = "/changeStateToFrozen_Role", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result changeStateToFrozen_Role(@RequestBody Map map) {
        Result re = roleServiceImpl.changeStateToFrozen_Role(map);
        System.out.println(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping(value = "/changeStateToNormal_Role", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result changeStateToNormal_Role(@RequestBody Map map) {
        Result re = roleServiceImpl.changeStateToNormal_Role(map);
        System.out.println(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping(value = "/deleteRole", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result deleteRole(@RequestBody Map map) {
        Result re = roleServiceImpl.deleteRole(map);
        System.out.println(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping(value = "/deleteRoleRights", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result deleteRoleRights(@RequestBody Map map) {
        Result re = roleServiceImpl.deleteRoleRights(map);
        System.out.println(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping(value = "/deleteAllRole", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result deleteAllRole(@RequestBody Map<String, String> map) {
        Result re = roleServiceImpl.deleteAllRole(map);
        System.out.println(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/InsertRoleRights")
    public Result InsertRoleRights(@RequestBody Map<String, String> map) {
        Result re = roleServiceImpl.InsertRoleRights(map);
        System.out.println(Divider);
        return re;
    }

}
