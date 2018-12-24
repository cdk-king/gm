package com.cdk.service.impl;


import com.cdk.dao.impl.NewPropDaoImpl;
import com.cdk.entity.NewProp;
import com.cdk.result.Result;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

@Service
public class NewPropServiceImpl {
    private static Logger logger = Logger.getLogger(String.valueOf(NewPropServiceImpl.class));
    public static final String Divider = "############################";
    public static final String Split = "----------------";

    @Autowired
    public NewPropDaoImpl newPropDaoImpl;

    public Result ImportProp(Map map) {
        int platformId = 0;
        int gameId = 0;
        Result re;

        String strlist = map.get("list").toString();
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String strGameId = ((map.get("gameId") != null && map.get("gameId") != "") ? map.get("gameId").toString() : "0");
        if (Objects.equals(strPlatformId, "")) {
            logger.info("道具导入失败");
            re = new Result(400, "道具导入失败", null);
            return re;
        }
        JSONArray jsonArray = null;
        try {
            platformId = Integer.parseInt(strPlatformId);
            gameId = Integer.parseInt(strGameId);
            jsonArray = new JSONArray(strlist);
            logger.info(jsonArray.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        int temp = newPropDaoImpl.ImportProp(jsonArray, platformId, gameId);
        if (temp > 0) {
            logger.info("道具导入成功");
            re = new Result(200, "道具导入成功", null);
        } else {
            logger.info("道具导入失败");
            re = new Result(400, "道具导入失败", null);
        }
        return re;
    }

    public Result getPropTypeList(Map map) {
        String strGameId = ((map.get("gameId") != null && map.get("gameId") != "") ? map.get("gameId").toString() : "0");
        Result re;
        int gameId = Integer.parseInt(strGameId);

        Map<String, Object> JsonMap = newPropDaoImpl.getPropTypeList(gameId);
        if (Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(400, "道具类别列表获取失败", "");
        } else {
            re = new Result(200, "道具类别列表获取成功", JsonMap);
        }
        return re;

    }

    public Result getPropUplaod(Map map) {
        String propName = (map.get("propName") != null ? map.get("propName").toString() : "");
        String propType = (map.get("propType") != null ? map.get("propType").toString() : "");
        String platformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
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
        Result re;

        NewProp newProp = new NewProp();
        newProp.setPropName(propName);
        newProp.setPropType(propType);
        newProp.setPlatformId(Integer.parseInt(platformId));

        Map<String, Object> JsonMap = newPropDaoImpl.getPropUplaod(newProp, isPage, pageNo, pageSize, strPlatform);
        if (Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(400, "道具列表获取失败", "");
        } else {
            re = new Result(200, "道具列表获取成功", JsonMap);
        }
        return re;
    }
}
