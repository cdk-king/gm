package com.cdk.controller;

import com.cdk.result.Result;
import com.cdk.service.impl.ApplyPropServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.logging.Logger;

@RestController
public class ApplyPropController {
    private static Logger logger = Logger.getLogger(String.valueOf(ApplyPropController.class));
    public static final String Divider = "############################";
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ApplyPropServiceImpl applyPropServiceImpl;

    @CrossOrigin
    @RequestMapping("/getPlayerTypeList")
    public Result getPlayerTypeList(@RequestBody Map map) {
        Result re = applyPropServiceImpl.getPlayerTypeList();
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/getApplyProp")
    public Result getApplyProp(@RequestBody Map map) {
        Result re = applyPropServiceImpl.getApplyProp(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/addApplyProp")
    public Result addApplyProp(@RequestBody Map map) {
        Result re = applyPropServiceImpl.addApplyProp(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/apply/editApplyProp")
    public Result editApplyProp(@RequestBody Map map) {
        Result re = applyPropServiceImpl.editApplyProp(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/applyProp/confirmApply")
    public Result confirmApply(@RequestBody Map map) {
        Result re = applyPropServiceImpl.confirmApply(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/applyProp/getMoneyTypeList")
    public Result getMoneyTypeList(@RequestBody Map map) {
        Result re = applyPropServiceImpl.getMoneyTypeList(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/applyProp/getPropQualityList")
    public Result getPropQualityList(@RequestBody Map map) {
        Result re = applyPropServiceImpl.getPropQualityList(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/confirmApplyProp")
    public Result confirmApplyProp(@RequestBody Map map) {
        Result re = applyPropServiceImpl.confirmApplyProp(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/notConfirmApplyProp")
    public Result notConfirmApplyProp(@RequestBody Map map) {
        Result re = applyPropServiceImpl.notConfirmApplyProp(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/deleteApplyProp")
    public Result deleteApplyProp(@RequestBody Map map) {
        Result re = applyPropServiceImpl.deleteApplyProp(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/deleteAllApplyProp")
    public Result deleteAllApplyProp(@RequestBody Map map) {
        Result re = applyPropServiceImpl.deleteAllApplyProp(map);
        logger.info(Divider);
        return re;
    }
}
