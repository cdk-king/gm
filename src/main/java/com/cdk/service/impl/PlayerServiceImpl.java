package com.cdk.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cdk.dao.impl.PlayerDaoImpl;
import com.cdk.entity.Player;
import com.cdk.result.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class PlayerServiceImpl {
    public static final String Divider = "############################";
    public static final String Split = "----------------";

    @Autowired
    public PlayerDaoImpl playerDaoImpl;

    public Result getPlayer(Map map) {
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String strServerId = ((map.get("serverId") != null && map.get("serverId") != "") ? map.get("serverId").toString() : "0");
        String isPage = (map.get("isPage") != null ? map.get("isPage").toString() : "");
        String StrPageNo = (map.get("pageNo") != null ? map.get("pageNo").toString() : "1");
        String StrPageSize = (map.get("pageSize") != null ? map.get("pageSize").toString() : "5");
        String strSearchForm = (map.get("searchForm") != null ? map.get("searchForm").toString() : "");
        //strSearchForm = JSON.toJSONString(strSearchForm);
        System.out.println(strSearchForm);
        JSONObject jsonObject = JSON.parseObject(strSearchForm);
        //将jsonObj转换成Map
        Map<String, Object> searchForm = JSONObject.toJavaObject(jsonObject, Map.class);
        //JSONObject myJson = JSONObject.


        //Map<String, Object> searchForm = jsonObject;
        //Map searchForm = JSONObject.parseObject(strSearchForm);
        //Map searchForm = JSON.parseObject(strSearchForm);
        int pageNo = 1;
        int pageSize = 5;
        try {
            pageNo = Integer.parseInt(StrPageNo);
            pageSize = Integer.parseInt(StrPageSize);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        int platformId = Integer.parseInt(strPlatformId);
        int serverId = Integer.parseInt(strServerId);
        Player player = new Player();
        player.setPlatformId(platformId);
        player.setServerId(serverId);

        Result re;
        Map<String, Object> JsonMap = playerDaoImpl.getPlayer(player, isPage, pageNo, pageSize, searchForm);
        if (Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(400, "玩家列表获取失败", "");
        } else {
            re = new Result(200, "玩家列表获取成功", JsonMap);
        }
        return re;
    }


    public Result ChangeToProhibitSpeak(Map map) {
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String strServerId = ((map.get("serverId") != null && map.get("serverId") != "") ? map.get("serverId").toString() : "0");
        String playerName = (map.get("playerName") != null ? map.get("playerName").toString() : "");
        String playerAccount = (map.get("playerAccount") != null ? map.get("playerAccount").toString() : "");
        String playerId = (map.get("playerId") != null ? map.get("playerId").toString() : "");
        Player player = new Player();
        player.setServerId(Integer.parseInt(strServerId));
        player.setPlatformId(Integer.parseInt(strPlatformId));
        player.setPlayerName(playerName);
        player.setPlayerAccount(playerAccount);
        player.setPlayerId(Integer.parseInt(playerId));
        Result re;
        int temp = playerDaoImpl.ChangeToProhibitSpeak(player);
        if (temp > 0) {
            System.out.println("玩家禁言成功");
            re = new Result(200, "玩家禁言成功", null);
        } else {
            System.out.println("玩家禁言失败");
            re = new Result(400, "玩家禁言失败", null);
        }
        return re;
    }
}
