package com.cdk.controller;

import com.alibaba.fastjson.JSONException;
import com.cdk.result.Result;
import com.cdk.service.impl.PlatformNoticeServiceImpl;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PlatformNoticeController {
    public static final String Divider = "############################";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PlatformNoticeServiceImpl platformNoticeServiceImpl;


    @CrossOrigin
    @RequestMapping("/getPlatformNotice")
    public Result getPlatformNotice(@RequestBody Map map) {
        Result re = platformNoticeServiceImpl.getPlatformNotice(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/addPlatformNotice")
    public Result addPlatformNotice(@RequestBody Map map) {
        Result re = platformNoticeServiceImpl.addPlatformNotice(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/editPlatformNotice")
    public Result editPlatformNotice(@RequestBody Map map) {
        Result re = platformNoticeServiceImpl.editPlatformNotice(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/deletePlatformNotice")
    public Result deletePlatformNotice(@RequestBody Map map) {
        Result re = platformNoticeServiceImpl.deletePlatformNotice(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/api/notice/sendNotice")
    public Result sendPlatformNotice(@RequestBody Map map) {
        Result re = platformNoticeServiceImpl.sendPlatformNotice(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/api/notice/reSendNotice")
    public Result reSendNotice(@RequestBody Map map) {
        Result re = platformNoticeServiceImpl.sendPlatformNotice(map);
        System.out.println(Divider);
        return re;
    }


    @RequestMapping("/deleteAllPlatformNotice")
    public Result deleteAllPlatformNotice(@RequestBody Map map) {
        Result re = platformNoticeServiceImpl.deleteAllPlatformNotice(map);
        System.out.println(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/notice/getLastNotice")
    public String getLastNotice(@RequestParam("oid") String oid) {
        Map<String, String> map = new HashMap<>();
        map.put("platformId", oid);
        System.out.println(map);
        String code = "公告获取测试";
        Result re = platformNoticeServiceImpl.getLastNotice(map);
        JSONObject jsonObject = null;
        try {
            System.out.println(jsonObject);
            jsonObject = JSONObject.fromObject(re.getData());
            System.out.println(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //code = re.getData().toString();
        System.out.println(Divider);
        return jsonObject.toString();
    }
}
