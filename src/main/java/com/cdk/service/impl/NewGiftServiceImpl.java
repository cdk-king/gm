package com.cdk.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.cdk.dao.impl.NewGiftDaoImpl;
import com.cdk.entity.NewGift;
import com.cdk.result.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class NewGiftServiceImpl {
    public static final String Divider = "############################";
    public static final String Split = "----------------";

    @Autowired
    public NewGiftDaoImpl newGiftDaoImpl;

    public Result ImportGift(Map map) {
        int len = 10;
        int platformId = 0;
        Result re;

        String strlist = map.get("list").toString();
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        if (Objects.equals(strPlatformId, "")) {
            System.out.println("礼包导入失败");
            re = new Result(400, "礼包导入失败", null);
            return re;
        }
        JSONArray jsonArray = null;

        platformId = Integer.parseInt(strPlatformId);
        jsonArray = JSONArray.parseArray(strlist);
        System.out.println(jsonArray);
        System.out.println(jsonArray.size());
        len = jsonArray.size();

        int[] temp = new int[len];
        temp = newGiftDaoImpl.ImportGift(jsonArray, platformId);
        if (temp.length > 0) {
            System.out.println("礼包导入成功");
            re = new Result(200, "礼包导入成功", null);
        } else {
            System.out.println("礼包导入失败");
            re = new Result(400, "礼包导入失败", null);
        }
        return re;
    }

    public Result getGiftUpload(Map map) {
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

        Map<String, Object> JsonMap = newGiftDaoImpl.getGiftUpload(newGift, isPage, pageNo, pageSize, strPlatform);
        if (!Objects.equals(JsonMap.get("list"), 0)) {
            System.out.println("礼包列表获取成功");
            re = new Result(200, "礼包列表获取成功", JsonMap);
        } else {
            System.out.println("礼包列表获取失败");
            re = new Result(400, "礼包列表获取失败", null);
        }
        //        (•_•)
        //        <)  )╯
        //        /   \
        //
        //        \(•_•)
        //        (  (>
        //        /   \
        return re;
    }

}
