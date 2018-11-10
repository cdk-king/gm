package com.cdk.service.impl;

import com.cdk.dao.impl.UtilsDaoImpl;
import com.cdk.entity.Game;
import com.cdk.entity.User;
import com.cdk.result.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UtilsServiceImpl {

    public static final String Divider = "############################";
    public static final String Split = "----------------";
    @Autowired
    public UtilsDaoImpl utilsDaoImpl;

    public Result getUserAllRight(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        System.out.println("id：" + id);

        User user = new User();
        user.setId(Integer.parseInt(id));
        Result re;
        List<Map<String, Object>> list = utilsDaoImpl.getUserAllRight(user);
        System.out.println("list.size()：" + list.size());
        Object[] str = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            str[i] = list.get(i).get("rightTag");
        }

        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        if (list.size() > 0) {
            re = new Result(200, "用户权限组获取成功", str);
        } else {
            re = new Result(400, "用户权限组获取失败", str);
        }

        return re;
    }

    public Result getUserAllRole(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        System.out.println("id：" + id);
        User user = new User();
        user.setId(Integer.parseInt(id));
        List<Map<String, Object>> list = utilsDaoImpl.getUserAllRole(user);
        System.out.println("list.size()：" + list.size());
        System.out.println(list);
        Object[] str = new String[list.size()];

        for (int i = 0; i < list.size(); i++) {
            str[i] = list.get(i).get("id").toString();
        }

        Result re;

        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        if (list.size() > 0) {
            re = new Result(200, "用户角色组获取成功", str);
        } else {
            re = new Result(400, "用户角色组获取失败", str);
        }

        System.out.println(str);

        return re;
    }

    public Result getGameListForUser(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        System.out.println("id：" + id);
        User user = new User();
        user.setId(Integer.parseInt(id));
        List<Map<String, Object>> list = utilsDaoImpl.getGameListForUser(user);
        Result re;
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        if (list.size() > 0) {
            re = new Result(200, "用户游戏列表获取成功", JsonMap);
        } else {
            re = new Result(400, "用户游戏列表获取失败", JsonMap);
        }
        return re;
    }

    public Result getPlatformListForGameId(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        System.out.println("id：" + id);
        Game game = new Game();
        game.setId(Integer.parseInt(id));
        List<Map<String, Object>> list = utilsDaoImpl.getPlatformListForGameId(game);
        Result re;
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        if (list.size() > 0) {
            re = new Result(200, "游戏平台列表获取成功", JsonMap);
        } else {
            re = new Result(400, "游戏平台列表获取失败", JsonMap);
        }
        return re;
    }

    public Result getPlatformListForUserIdAndGameId(Map map) {
        String userId = (map.get("userId") != null ? map.get("userId").toString() : "");
        System.out.println("userId：" + userId);
        String gameId = (map.get("gameId") != null ? map.get("gameId").toString() : "");
        System.out.println("gameId：" + gameId);
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
            re = new Result(400, "用户游戏平台列表获取失败", JsonMap);
        }

        return re;
    }
}
