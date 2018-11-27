package com.cdk.controller;

import com.cdk.result.Result;
import com.cdk.service.impl.ApplyPropServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ApplyPropController {

    public static final String Divider = "############################";
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ApplyPropServiceImpl applyPropServiceImpl;

    @RequestMapping("/getPlayerTypeList")
    public Result getPlayerTypeList(@RequestBody Map map) {
        Result re = applyPropServiceImpl.getPlayerTypeList();
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/getApplyProp")
    public Result getApplyProp(@RequestBody Map map) {
        Result re = applyPropServiceImpl.getApplyProp(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/addApplyProp")
    public Result addApplyProp(@RequestBody Map map) {
        Result re = applyPropServiceImpl.addApplyProp(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/api/apply/editApplyProp")
    public Result editApplyProp(@RequestBody Map map) {
        Result re = applyPropServiceImpl.editApplyProp(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/api/applyProp/confirmApply")
    public Result confirmApply(@RequestBody Map map) {
        Result re = applyPropServiceImpl.confirmApply(map);
        System.out.println(Divider);
        return re;
    }


    @RequestMapping("/api/applyProp/getMoneyTypeList")
    public Result getMoneyTypeList(@RequestBody Map map) {
        Result re = applyPropServiceImpl.getMoneyTypeList(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/api/applyProp/getPropQualityList")
    public Result getPropQualityList(@RequestBody Map map) {
        Result re = applyPropServiceImpl.getPropQualityList(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/confirmApplyProp")
    public Result confirmApplyProp(@RequestBody Map map) {
        Result re = applyPropServiceImpl.confirmApplyProp(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/notConfirmApplyProp")
    public Result notConfirmApplyProp(@RequestBody Map map) {
        Result re = applyPropServiceImpl.notConfirmApplyProp(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/deleteApplyProp")
    public Result deleteApplyProp(@RequestBody Map map) {
        Result re = applyPropServiceImpl.deleteApplyProp(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/deleteAllApplyProp")
    public Result deleteAllApplyProp(@RequestBody Map map) {
        Result re = applyPropServiceImpl.deleteAllApplyProp(map);
        System.out.println(Divider);
        return re;
    }
}
