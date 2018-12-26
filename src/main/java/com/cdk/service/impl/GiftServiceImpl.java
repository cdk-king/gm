package com.cdk.service.impl;

import com.cdk.dao.impl.GiftDaoImpl;
import com.cdk.entity.Gift;
import com.cdk.result.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

@Service
public class GiftServiceImpl {
    private static Logger logger = Logger.getLogger(String.valueOf(GiftServiceImpl.class));

    @Autowired
    public GiftDaoImpl giftDaoImpl;

    public Result getGift(Map map) {
        String giftName = (map.get("giftName") != null ? map.get("giftName").toString() : "");
        String giftTag = (map.get("giftTag") != null ? map.get("giftTag").toString() : "");
        String platformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String state = (map.get("state") != null ? map.get("state").toString() : "");
        String isPage = (map.get("isPage") != null ? map.get("isPage").toString() : "");
        String StrPageNo = (map.get("pageNo") != null ? map.get("pageNo").toString() : "1");
        String StrPageSize = (map.get("pageSize") != null ? map.get("pageSize").toString() : "5");
        String strPlatform = (map.get("strPlatform") != null ? map.get("strPlatform").toString() : "");
        int pageNo = 1;
        int pageSize = 5;
        try {
            pageNo = Integer.parseInt(StrPageNo);
            pageSize = Integer.parseInt(StrPageSize);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (state == "") {
            state = "0";
        }
        Result re;
        Gift gift = new Gift();
        gift.setGiftName(giftName);
        gift.setGiftTag(giftTag);
        gift.setPlatformId(Integer.parseInt(platformId));
        gift.setState(Integer.parseInt(state));

        Map<String, Object> JsonMap = giftDaoImpl.getGift(gift, isPage, pageNo, pageSize, strPlatform);
        if (Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(400, "礼包列表获取失败", "");
        } else {
            re = new Result(200, "礼包列表获取成功", JsonMap);
        }
        return re;
    }

    public Result addGift(Map map) {
        String giftName = (map.get("giftName") != null ? map.get("giftName").toString() : "");
        String giftTag = (map.get("giftTag") != null ? map.get("giftTag").toString() : "");
        String gift_describe = (map.get("gift_describe") != null ? map.get("gift_describe").toString() : "");
        String platformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String addUser = (map.get("addUser") != null ? map.get("addUser").toString() : "");
        Gift gift = new Gift();
        gift.setGiftName(giftName);
        gift.setGiftTag(giftTag);
        gift.setPlatformId(Integer.parseInt(platformId));
        gift.setGift_describe(gift_describe);
        gift.setAddUser(addUser);
        gift.setGiftType(0);
        Result re;
        int temp = giftDaoImpl.addGift(gift);
        if (temp > 0) {
            logger.info("礼包添加成功");
            re = new Result(200, "礼包添加成功", null);
        } else {
            logger.info("礼包添加失败");
            re = new Result(400, "礼包添加失败", null);
        }
        return re;
    }
}
