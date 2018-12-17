package com.cdk.controller;

import com.alibaba.fastjson.JSONException;
import com.cdk.cache.CacheManagerImpl;
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
import java.util.logging.Logger;

@RestController
public class PlatformNoticeController {
    private static Logger logger = Logger.getLogger(String.valueOf(PlatformNoticeController.class));
    public static final String Divider = "############################";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PlatformNoticeServiceImpl platformNoticeServiceImpl;


    @CrossOrigin
    @RequestMapping("/getPlatformNotice")
    public Result getPlatformNotice(@RequestBody Map map) {
        Result re = platformNoticeServiceImpl.getPlatformNotice(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/addPlatformNotice")
    public Result addPlatformNotice(@RequestBody Map map) {
        Result re = platformNoticeServiceImpl.addPlatformNotice(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/editPlatformNotice")
    public Result editPlatformNotice(@RequestBody Map map) {
        Result re = platformNoticeServiceImpl.editPlatformNotice(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/deletePlatformNotice")
    public Result deletePlatformNotice(@RequestBody Map map) {
        Result re = platformNoticeServiceImpl.deletePlatformNotice(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/notice/sendNotice")
    public Result sendPlatformNotice(@RequestBody Map map) {
        Result re = platformNoticeServiceImpl.sendPlatformNotice(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/notice/reSendNotice")
    public Result reSendNotice(@RequestBody Map map) {
        Result re = platformNoticeServiceImpl.sendPlatformNotice(map);
        logger.info(Divider);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/deleteAllPlatformNotice")
    public Result deleteAllPlatformNotice(@RequestBody Map map) {
        Result re = platformNoticeServiceImpl.deleteAllPlatformNotice(map);
        logger.info(Divider);
        return re;
    }


    @Autowired
    private CacheManagerImpl cacheManagerImpl;
    Logger logger2 = Logger.getLogger("getLastNotice");

    @CrossOrigin
    @RequestMapping("/api/notice/getLastNotice")
    public String getLastNotice(@RequestParam("oid") String oid) {
        Map<String, String> map = new HashMap<>();
        map.put("platformId", oid);
        String platform = oid;
        Result re;

        //设置缓存
        if (cacheManagerImpl.isContains("LastNotice-" + platform)) {
            re = (Result) cacheManagerImpl.getCacheByKey("LastNotice-" + platform).getDatas();
            logger2.info("已找到缓存" + "LastNotice-" + platform);
            logger2.info(re.toString());
        } else {
            logger2.info("未找到缓存" + "LastNotice-" + platform);
            re = platformNoticeServiceImpl.getLastNotice(map);
            int timeOut = 60 * 1000;
            cacheManagerImpl.putCache("LastNotice-" + platform, re, timeOut);
            logger2.info("已设置缓存，缓存时间" + Math.floor(timeOut / 1000L) + "秒");
            logger2.info(re.toString());
        }

        JSONObject jsonObject = null;
        try {
            jsonObject = JSONObject.fromObject(re.getData());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        logger.info(Divider);
        return jsonObject.toString();
    }
}
