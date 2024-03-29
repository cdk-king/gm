package com.cdk.service.impl;

import com.cdk.dao.impl.UtilsDaoImpl;
import com.cdk.entity.Game;
import com.cdk.entity.User;
import com.cdk.result.Result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UtilsServiceImpl {
    private static Logger logger = LoggerFactory.getLogger(UtilsServiceImpl.class);
    @Autowired
    public UtilsDaoImpl utilsDaoImpl;

    public List<Map<String, String>> getServerUrl(String serverIdList, String platformId, String gameId) {
        String[] serverIdArray = serverIdList.split(",");
        List<Map<String, Object>> list = utilsDaoImpl.getServerUrl(platformId, gameId);
        List<Map<String, String>> urlList = new ArrayList<>();
        Map<String, String> map = new HashMap();
        for (int j = 0; j < serverIdArray.length; j++) {
            for (int i = 0; i < list.size(); i++) {
                if (Objects.equals(serverIdArray[j], list.get(i).get("serverId").toString())) {
                    map.put("serverId", serverIdArray[j]);
                    map.put("url", list.get(i).get("serverIp").toString() + ":" + list.get(i).get("serverPort").toString());
                    urlList.add(map);
                    map = new HashMap();
                }
            }
        }
        return urlList;
    }

    public Result getUserAllRight(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        User user = new User();
        user.setId(Integer.parseInt(id));
        Result re;
        List<Map<String, Object>> list = utilsDaoImpl.getUserAllRight(user);
        Object[] str = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            str[i] = list.get(i).get("rightTag");
        }
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        if (list.size() > 0) {
            re = new Result(200, "用户权限组获取成功", str);
        } else {
            re = new Result(200, "用户权限为空", str);
        }
        return re;
    }

    public Result getUserAllRole(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        User user = new User();
        user.setId(Integer.parseInt(id));
        List<Map<String, Object>> list = utilsDaoImpl.getUserAllRole(user);
        Object[] strList = new String[list.size()];

        for (int i = 0; i < list.size(); i++) {
            strList[i] = list.get(i).get("id").toString();
        }
        Result re;
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        if (list.size() > 0) {
            re = new Result(200, "用户角色组获取成功", strList);
        } else {
            re = new Result(200, "用户角色为空", strList);
        }
        return re;
    }


    public Result getGameListForUser(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        User user = new User();
        user.setId(Integer.parseInt(id));
        List<Map<String, Object>> list = utilsDaoImpl.getGameListForUser(user);
        Result re;
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        if (list.size() > 0) {
            re = new Result(200, "用户游戏列表获取成功", JsonMap);
        } else {
            re = new Result(200, "用户游戏列表为空", JsonMap);
        }
        return re;
    }

    public Result getPlatformListForGameId(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        Game game = new Game();
        game.setId(Integer.parseInt(id));
        List<Map<String, Object>> list = utilsDaoImpl.getPlatformListForGameId(game);
        Result re;
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        if (list.size() > 0) {
            re = new Result(200, "游戏平台列表获取成功", JsonMap);
        } else {
            re = new Result(200, "游戏平台列表为空", JsonMap);
        }
        return re;
    }

    public Result getPlatformListForUserIdAndGameId(Map map) {
        String userId = (map.get("userId") != null ? map.get("userId").toString() : "");
        String gameId = (map.get("gameId") != null ? map.get("gameId").toString() : "");
        User user = new User();
        user.setId(Integer.parseInt(userId));
        Game game = new Game();
        game.setId(Integer.parseInt(gameId));
        List<Map<String, Object>> list = utilsDaoImpl.getPlatformListForUserIdAndGameId(user, game);
        Result re;
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        if (list.size() > 0) {
            re = new Result(200, "用户游戏平台列表获取成功", JsonMap);
        } else {
            re = new Result(200, "用户游戏平台列表为空", JsonMap);
        }

        return re;
    }

    public Result getValueTypeList(Map map) {
        String strGameId = ((map.get("gameId") != null && map.get("gameId") != "") ? map.get("gameId").toString() : "0");
        String allow = ((map.get("allow") != null && map.get("allow") != "") ? map.get("allow").toString() : "");
        int gameId = Integer.parseInt(strGameId);
        Result re;
        Map<String, Object> JsonMap = utilsDaoImpl.getValueTypeList(gameId, allow);
        if (Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(400, "货币类别列表获取失败", "");
        } else {
            re = new Result(200, "货币类别列表获取成功", JsonMap);
        }
        return re;
    }
}
