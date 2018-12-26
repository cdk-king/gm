package com.cdk.controller;

import com.cdk.result.Result;
import com.cdk.service.impl.CDK_ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController
public class CDK_TableController {
    private static Logger logger = Logger.getLogger(String.valueOf(CDK_TableController.class));
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CDK_ServiceImpl cdkServiceImpl;


    @CrossOrigin
    @RequestMapping("/getCDK")
    public Result getCDK(@RequestBody Map map) {
        Result re = cdkServiceImpl.getCDK(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/exchangeCDK")
    public Result exchangeCDK(@RequestBody Map map) {
        Result re = cdkServiceImpl.exchangeCDK(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/cdk/checkCDKIsUse")
    public Result checkCDKIsUse(@RequestBody Map map) {
        Result re = cdkServiceImpl.checkCDKIsUse(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/cdk/exchangeCDK_External")
    public String exchangeCDK_External(@RequestParam("id") String id, @RequestParam("oid") String oid, @RequestParam("sequence") String sequence,
            @RequestParam("coupon") String coupon) {
        Map<String, String> map = new HashMap<>();
        map.put("cdk", coupon);
        map.put("couponId", id);
        map.put("sequenceId", sequence);
        map.put("platformId", oid);
        String code = "0";
        Result re = cdkServiceImpl.exchangeCDK_External(map);
        code = re.getData().toString();
        return code;
    }
}
