package com.cdk.controller;

import com.cdk.result.Result;
import com.cdk.service.impl.PlatformNoticeServiceImpl;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PlatformNoticeController {
    private static Logger logger = LoggerFactory.getLogger(PlatformNoticeController.class);

    @Autowired
    private PlatformNoticeServiceImpl platformNoticeServiceImpl;


    @CrossOrigin
    @RequestMapping("/getPlatformNotice")
    public Result getPlatformNotice(@RequestBody Map map) {
        Result re = platformNoticeServiceImpl.getPlatformNotice(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/addPlatformNotice")
    public Result addPlatformNotice(@RequestBody Map map) {
        Result re = platformNoticeServiceImpl.addPlatformNotice(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/editPlatformNotice")
    public Result editPlatformNotice(@RequestBody Map map) {
        Result re = platformNoticeServiceImpl.editPlatformNotice(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/deletePlatformNotice")
    public Result deletePlatformNotice(@RequestBody Map map) {
        Result re = platformNoticeServiceImpl.deletePlatformNotice(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/notice/sendNotice")
    public Result sendPlatformNotice(@RequestBody Map map) {
        Result re = platformNoticeServiceImpl.sendPlatformNotice(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/notice/reSendNotice")
    public Result reSendNotice(@RequestBody Map map) {
        Result re = platformNoticeServiceImpl.sendPlatformNotice(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/deleteAllPlatformNotice")
    public Result deleteAllPlatformNotice(@RequestBody Map map) {
        Result re = platformNoticeServiceImpl.deleteAllPlatformNotice(map);
        return re;
    }


    @CrossOrigin
    @RequestMapping("/api/notice/getLastNotice")
    public String getLastNotice(@RequestParam("oid") String oid) {
        Result re = platformNoticeServiceImpl.getLastNotice(oid);
        JSONObject jsonObject = JSONObject.fromObject(re.getData());
        return jsonObject.toString();
    }
}
