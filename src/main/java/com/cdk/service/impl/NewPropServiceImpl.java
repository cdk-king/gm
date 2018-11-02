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

@Service
public class NewPropServiceImpl {
    public static final String Divider = "############################";
    public static final String Split = "----------------";

    @Autowired
    public NewPropDaoImpl newPropDaoImpl;

    public Result ImportProp(Map map) {
        int len = 10;
        int platformId = 0;
        Result re;

        String strlist = map.get("list").toString();
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        if (Objects.equals(strPlatformId, "")) {
            System.out.println("道具导入失败");
            re = new Result(400, "道具导入失败", null);
            return re;
        }
        JSONArray jsonArray = null;
        try {
            platformId = Integer.parseInt(strPlatformId);
            jsonArray = new JSONArray(strlist);
            System.out.println(jsonArray);
            System.out.println(jsonArray.length());
            len = jsonArray.length();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        int[] temp = new int[len];
        temp = newPropDaoImpl.ImportProp(jsonArray, platformId);
        if (temp.length > 0) {
            System.out.println("道具导入成功");
            re = new Result(200, "道具导入成功", null);
        } else {
            System.out.println("道具导入失败");
            re = new Result(400, "道具导入失败", null);
        }
        return re;
    }


    public Result getPropUplaod(Map map) {
        String propName = (map.get("propName") != null ? map.get("propName").toString() : "");
        String propTag = (map.get("propTag") != null ? map.get("propTag").toString() : "");
        String platformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String isPage = (map.get("isPage") != null ? map.get("isPage").toString() : "");
        String StrPageNo = (map.get("pageNo") != null ? map.get("pageNo").toString() : "1");
        String StrPageSize = (map.get("pageSize") != null ? map.get("pageSize").toString() : "5");
        String strPlatform = (map.get("strPlatform") != null ? map.get("strPlatform").toString() : "");
        System.out.println("strPlatform：" + strPlatform);

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
        newProp.setPropTag(propTag);
        newProp.setPlatformId(Integer.parseInt(platformId));

        Map<String, Object> JsonMap = newPropDaoImpl.getPropUplaod(newProp, isPage, pageNo, pageSize, strPlatform);
        System.out.println(JsonMap.get("list"));
        if (Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(400, "道具列表获取失败", "");
        } else {
            re = new Result(200, "道具列表获取成功", JsonMap);
        }
        return re;

    }
}
