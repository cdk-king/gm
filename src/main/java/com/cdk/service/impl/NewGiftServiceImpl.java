package com.cdk.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.cdk.dao.impl.NewGiftDaoImpl;
import com.cdk.entity.NewGift;
import com.cdk.result.Result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class NewGiftServiceImpl {
    private static Logger logger = LoggerFactory.getLogger(NewGiftServiceImpl.class);

    @Autowired
    public NewGiftDaoImpl newGiftDaoImpl;

    public Result ImportGift(Map map) {
        int platformId = 0;
        int gameId = 0;
        Result re;

        String strlist = map.get("list").toString();
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String strGameId = ((map.get("gameId") != null && map.get("gameId") != "") ? map.get("gameId").toString() : "0");
        if (Objects.equals(strPlatformId, "")) {
            logger.debug("礼包导入失败");
            re = new Result(400, "礼包导入失败", null);
            return re;
        }
        JSONArray jsonArray = null;
        platformId = Integer.parseInt(strPlatformId);
        gameId = Integer.parseInt(strGameId);
        jsonArray = JSONArray.parseArray(strlist);

        int temp = newGiftDaoImpl.ImportGift(jsonArray, platformId, gameId);
        if (temp > 0) {
            logger.debug("礼包导入成功");
            re = new Result(200, "礼包导入成功", null);
        } else {
            logger.debug("礼包导入失败");
            re = new Result(400, "礼包导入失败", null);
        }
        return re;
    }

    public Result getGiftUpload(Map map) {
        String gameId = ((map.get("gameId") != null && map.get("gameId") != "") ? map.get("gameId").toString() : "0");
        String giftName = map.get("giftName").toString();
        String giftTag = map.get("giftTag").toString();
        String platformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String isPage = (map.get("isPage") != null ? map.get("isPage").toString() : "");
        String StrPageNo = (map.get("pageNo") != null ? map.get("pageNo").toString() : "1");
        String StrPageSize = (map.get("pageSize") != null ? map.get("pageSize").toString() : "5");
        String strPlatform = (map.get("strPlatform") != null ? map.get("strPlatform").toString() : "");
        int pageNo = 1;
        int pageSize = 5;
        Result re;
        NewGift newGift = new NewGift();
        newGift.setGiftName(giftName);
        newGift.setGiftTag(giftTag);
        try {
            pageNo = Integer.parseInt(StrPageNo);
            pageSize = Integer.parseInt(StrPageSize);
            newGift.setPlatformId(Integer.parseInt(platformId));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        Map<String, Object> JsonMap = newGiftDaoImpl.getGiftUpload(newGift, isPage, pageNo, pageSize, strPlatform, gameId);
        if (!Objects.equals(JsonMap.get("list"), 0)) {
            logger.debug("礼包列表获取成功");
            re = new Result(200, "礼包列表获取成功", JsonMap);
        } else {
            logger.debug("礼包列表获取失败");
            re = new Result(400, "礼包列表获取失败", null);
        }
        return re;
    }

    public Result deleteAllGiftForPlatform(Map map) {
        String gameId = ((map.get("gameId") != null && map.get("gameId") != "") ? map.get("gameId").toString() : "0");
        String platformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");

        Result re;
        int temp = newGiftDaoImpl.deleteAllGiftForPlatform(gameId, platformId);

        if (temp > 0) {
            logger.debug("礼包批量删除成功");
            re = new Result(200, "礼包批量删除成功", temp);
        } else {
            logger.debug("礼包批量删除失败");
            re = new Result(400, "礼包批量删除失败", temp);
        }
        return re;
    }
}
