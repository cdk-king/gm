package com.cdk.service.impl;

import com.cdk.dao.impl.PlatformDaoImpl;
import com.cdk.entity.Platform;
import com.cdk.result.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

@Service
public class PlatformServiceImpl {
    private static Logger logger = Logger.getLogger(String.valueOf(PlatformServiceImpl.class));
    public static final String Divider = "############################";
    public static final String Split = "----------------";
    @Autowired
    public PlatformDaoImpl platformDaoImpl;

    public Result getAllPlatform(Map map) {
        String platformName = (map.get("platform") != null ? map.get("platform").toString() : "");
        String platformTag = (map.get("platformTag") != null ? map.get("platformTag").toString() : "");
        String platform_describe = (map.get("platform_describe") != null ? map.get("platform_describe").toString() : "");
        String gameName = (map.get("gameName") != null ? map.get("gameName").toString() : "");
        String addUser = (map.get("addUser") != null ? map.get("addUser").toString() : "");
        String addDatetime = (map.get("addDatetime") != null ? map.get("addDatetime").toString() : "");
        String state = (map.get("state") != null ? map.get("state").toString() : "");
        String isPage = (map.get("isPage") != null ? map.get("isPage").toString() : "");
        if (state == "") {
            state = "0";
        }

        String StrPageNo = (map.get("pageNo") != null ? map.get("pageNo").toString() : "1");
        String StrPageSize = (map.get("pageSize") != null ? map.get("pageSize").toString() : "5");

        logger.info("pageNo：" + StrPageNo);
        logger.info("pageSize：" + StrPageSize);
        int pageNo = 1;
        int pageSize = 5;

        try {
            pageNo = Integer.parseInt(StrPageNo);
            pageSize = Integer.parseInt(StrPageSize);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        Platform platform = new Platform();
        platform.setPlatform(platformName);
        platform.setPlatform_describe(platform_describe);
        platform.setPlatformTag(platformTag);
        platform.setState(Integer.parseInt(state));

        Result re;

        Map<String, Object> JsonMap = platformDaoImpl.getAllPlatform(platform, gameName, isPage, pageNo, pageSize);
        if (Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(400, "平台列表获取失败", "");
        } else {
            re = new Result(200, "平台列表获取成功", JsonMap);
        }
        return re;
    }

    public Result getAllGameList(Map map) {
        Result re;
        List<Map<String, Object>> list = platformDaoImpl.getAllGameList();
        if (list.size() > 0) {
            logger.info("游戏列表获取成功");
            re = new Result(200, "游戏列表获取成功", list);
        } else {
            logger.info("游戏列表获取失败");
            re = new Result(400, "游戏列表获取失败", list);
        }
        return re;
    }

    public Result getAllRoleList(Map map) {
        Result re;
        List<Map<String, Object>> list = platformDaoImpl.getAllRoleList();
        if (list.size() > 0) {
            logger.info("角色列表获取成功");
            re = new Result(200, "角色列表获取成功", list);
        } else {
            logger.info("角色列表获取失败");
            re = new Result(400, "角色列表获取失败", list);
        }
        return re;
    }

    public Result addPlatform(Map map) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        String platformId = (map.get("platformId") != null ? map.get("platformId").toString() : "");
        String gameId = (map.get("gameId") != null ? map.get("gameId").toString() : "");
        String roleId = (map.get("roleId") != null ? map.get("roleId").toString() : "");
        String platformName = (map.get("platform") != null ? map.get("platform").toString() : "");
        String platformTag = (map.get("platformTag") != null ? map.get("platformTag").toString() : "");
        String ParentId = (map.get("ParentId") != null ? map.get("ParentId").toString() : "");
        String platform_describe = (map.get("platform_describe") != null ? map.get("platform_describe").toString() : "");
        String sort = (map.get("sort") != null ? map.get("sort").toString() : "");
        String addUser = (map.get("addUser") != null ? map.get("addUser").toString() : "");
        String state = (map.get("state") != null ? map.get("state").toString() : "");

        Platform platform = new Platform();
        platform.setPlatform(platformName);
        platform.setPlatformTag(platformTag);
        platform.setPlatform_describe(platform_describe);
        platform.setGameId(Integer.parseInt(gameId));
        platform.setRoleId(Integer.parseInt(roleId));
        platform.setAddUser(addUser);
        platform.setPlatformId(Integer.parseInt(platformId));

        Result re;
        int temp = platformDaoImpl.addPlatform(platform);
        if (temp > 0) {
            logger.info("权限添加成功");
            re = new Result(200, "权限添加成功", null);
        } else {
            logger.info("权限添加失败");
            re = new Result(400, "权限添加失败", null);
        }
        return re;
    }

    public Result editPlatform(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        String platformId = (map.get("platformId") != null ? map.get("platformId").toString() : "");
        String platformName = (map.get("platform") != null ? map.get("platform").toString() : "");
        String platform_describe = (map.get("platform_describe") != null ? map.get("platform_describe").toString() : "");
        String platformTag = (map.get("platformTag") != null ? map.get("platformTag").toString() : "");
        String ParentId = (map.get("ParentId") != null ? map.get("ParentId").toString() : "");
        String sort = (map.get("sort") != null ? map.get("sort").toString() : "");
        String gameId = (map.get("gameId") != null ? map.get("gameId").toString() : "");
        String roleId = (map.get("roleId") != null ? map.get("roleId").toString() : "");
        String addUser = (map.get("addUser") != null ? map.get("addUser").toString() : "");
        String addDatetime = (map.get("addDatetime") != null ? map.get("addDatetime").toString() : "");
        String state = (map.get("state") != null ? map.get("state").toString() : "");

        Platform platform = new Platform();
        platform.setId(Integer.parseInt(id));
        platform.setPlatform(platformName);
        platform.setPlatform_describe(platform_describe);
        platform.setPlatformTag(platformTag);
        platform.setAddUser(addUser);
        platform.setGameId(Integer.parseInt(gameId));
        platform.setRoleId(Integer.parseInt(roleId));
        platform.setPlatformId(Integer.parseInt(platformId));

        Result re;
        int temp = platformDaoImpl.editPlatform(platform);
        if (temp > 0) {
            logger.info("平台信息修改成功");
            re = new Result(200, "平台信息更新成功", null);
        } else {
            logger.info("平台信息修改失败");
            re = new Result(400, "平台信息更新失败", null);
        }
        return re;
    }

    public Result deletePlatform(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        logger.info("id：" + id);

        Platform platform = new Platform();
        platform.setId(Integer.parseInt(id));

        Result re;
        int temp = platformDaoImpl.editPlatform(platform);
        if (temp > 0) {
            logger.info("平台删除成功");
            re = new Result(200, "平台删除成功", null);
        } else {
            logger.info("平台删除失败");
            re = new Result(400, "平台删除失败", null);

        }
        return re;
    }

    public Result changeStateToNormal_Platform(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        logger.info("id：" + id);

        Platform platform = new Platform();
        platform.setId(Integer.parseInt(id));

        Result re;
        int temp = platformDaoImpl.changeStateToNormal_Platform(platform);
        if (temp > 0) {
            logger.info("平台解冻成功");
            re = new Result(200, "平台解冻成功", null);
        } else {
            logger.info("平台解冻失败");
            re = new Result(400, "平台解冻失败", null);
        }
        return re;
    }

    public Result changeStateToFrozen_Platform(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        logger.info("id：" + id);

        Platform platform = new Platform();
        platform.setId(Integer.parseInt(id));
        Result re;
        int temp = platformDaoImpl.changeStateToFrozen_Platform(platform);
        if (temp > 0) {
            logger.info("平台冻结成功");
            re = new Result(200, "平台冻结成功", null);
        } else {
            logger.info("平台冻结失败");
            re = new Result(400, "平台冻结失败", null);
        }
        return re;
    }

    public Result deleteAllPlatform(Map map) {

        String id = (map.get("id") != null ? map.get("id").toString() : "");
        logger.info("id：" + id);
        if (Objects.equals(id, "")) {
            logger.info("无任何批量删除操作");
            return new Result(400, "无任何批量删除操作", null);
        }

        String[] ObjectArry = id.split(",");
        logger.info("ObjectArry：" + ObjectArry);

        Result re;
        int[] temp = new int[ObjectArry.length];
        temp = platformDaoImpl.deleteAllPlatform(ObjectArry);
        if (temp.length != 0) {
            logger.info("平台批量删除成功");
            re = new Result(200, "平台批量删除成功", null);
        } else if (ObjectArry.length == 0) {
            logger.info("无任何删除操作");
            re = new Result(400, "无任何删除操作", null);
        } else {
            logger.info("平台批量删除失败");
            re = new Result(400, "平台批量删除失败", null);
        }
        return re;
    }
}
