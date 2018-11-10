package com.cdk.service.impl;


import com.cdk.dao.impl.PropDaoImpl;
import com.cdk.entity.Prop;
import com.cdk.result.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class PropServiceImpl {
    public static final String Divider = "############################";
    public static final String Split = "----------------";
    @Autowired
    public PropDaoImpl propDaoImpl;

    public Result getProp(Map map) {
        String propName = (map.get("propName") != null ? map.get("propName").toString() : "");
        String propTag = (map.get("propTag") != null ? map.get("propTag").toString() : "");
        String platformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String addUser = (map.get("addUser") != null ? map.get("addUser").toString() : "");
        String state = (map.get("state") != null ? map.get("state").toString() : "");
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

        if (state == "") {
            state = "0";
        }

        Result re;

        Prop prop = new Prop();
        prop.setPropName(propName);
        prop.setPropTag(propTag);
        prop.setPlatformId(Integer.parseInt(platformId));
        prop.setAddUser(addUser);
        prop.setState(Integer.parseInt(state));

        Map<String, Object> JsonMap = propDaoImpl.getProp(prop, isPage, pageNo, pageSize, strPlatform);
        System.out.println(JsonMap.get("list"));
        //System.out.println(Objects.equals(JsonMap.get("list"), 0));
        //System.out.println(Objects.equals(JsonMap.get("list"), null));
        if (Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(400, "道具列表获取失败", "");
        } else {
            re = new Result(200, "道具列表获取成功", JsonMap);
        }

        return re;
    }

    public Result addProp(Map map) {
        String propName = (map.get("propName") != null ? map.get("propName").toString() : "");
        String propTag = (map.get("propTag") != null ? map.get("propTag").toString() : "");
        String prop_describe = (map.get("prop_describe") != null ? map.get("prop_describe").toString() : "");
        String platformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String addUser = (map.get("addUser") != null ? map.get("addUser").toString() : "");
        String state = (map.get("state") != null ? map.get("state").toString() : "");

        Prop prop = new Prop();
        prop.setPropName(propName);
        prop.setPropTag(propTag);
        prop.setPlatformId(Integer.parseInt(platformId));
        prop.setAddUser(addUser);
        prop.setProp_describe(prop_describe);
        prop.setPropType(0);

        Result re;
        int temp = propDaoImpl.addProp(prop);
        if (temp > 0) {
            System.out.println("道具添加成功");
            re = new Result(200, "道具添加成功", null);
        } else {
            System.out.println("道具添加失败");
            re = new Result(400, "道具添加失败", null);
        }
        return re;
    }

    public Result editProp(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        String propName = (map.get("propName") != null ? map.get("propName").toString() : "");
        String propTag = (map.get("propTag") != null ? map.get("propTag").toString() : "");
        String prop_describe = (map.get("prop_describe") != null ? map.get("prop_describe").toString() : "");
        //String platformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String addUser = (map.get("addUser") != null ? map.get("addUser").toString() : "");
        //String state = (map.get("state") != null ? map.get("state").toString() : "");
        Result re;
        Prop prop = new Prop();
        prop.setId(Integer.parseInt(id));
        prop.setPropName(propName);
        prop.setPropTag(propTag);
        prop.setAddUser(addUser);
        prop.setProp_describe(prop_describe);

        int temp = propDaoImpl.editProp(prop);
        if (temp > 0) {
            System.out.println("道具编辑成功");
            re = new Result(200, "道具编辑成功", null);
        } else {
            System.out.println("道具编辑失败");
            re = new Result(400, "道具编辑失败", null);
        }
        return re;
    }

    public Result deleteProp(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        Prop prop = new Prop();
        prop.setId(Integer.parseInt(id));
        Result re;
        int temp = propDaoImpl.deleteProp(prop);
        if (temp > 0) {
            System.out.println("道具删除成功");
            re = new Result(200, "道具删除成功", null);
        } else {
            System.out.println("道具删除失败");
            re = new Result(400, "道具删除失败", null);
        }
        return re;
    }

    public Result changeStateToNormal_Game(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        Prop prop = new Prop();
        prop.setId(Integer.parseInt(id));
        Result re;
        int temp = propDaoImpl.changeStateToNormal_Game(prop);
        if (temp > 0) {
            System.out.println("道具解冻成功");
            re = new Result(200, "道具解冻成功", null);
        } else {
            System.out.println("道具解冻失败");
            re = new Result(400, "道具解冻失败", null);
        }
        return re;
    }

    public Result changeStateToFrozen_Game(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        Prop prop = new Prop();
        prop.setId(Integer.parseInt(id));
        Result re;
        int temp = propDaoImpl.changeStateToFrozen_Game(prop);
        if (temp > 0) {
            System.out.println("道具冻结成功");
            re = new Result(200, "道具冻结成功", null);
        } else {
            System.out.println("道具冻结失败");
            re = new Result(400, "道具冻结失败", null);
        }
        return re;
    }

    public Result deleteAllProp(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        System.out.println("id：" + id);
        if (Objects.equals(id, "")) {
            System.out.println("无任何批量删除操作");
            return new Result(400, "无任何批量删除操作", null);
        }

        String[] ObjectArry = id.split(",");
        System.out.println("ObjectArry：" + ObjectArry);

        Result re;
        int[] temp = propDaoImpl.deleteAllProp(ObjectArry);

        if (temp.length != 0) {
            System.out.println("游戏批量删除成功");
            re = new Result(200, "游戏批量删除成功", null);
        } else if (ObjectArry.length == 0) {
            System.out.println("无任何删除操作");
            re = new Result(400, "无任何删除操作", null);
        } else {
            System.out.println("游戏批量删除失败");
            re = new Result(400, "游戏批量删除失败", null);
        }
        return re;
    }
}
