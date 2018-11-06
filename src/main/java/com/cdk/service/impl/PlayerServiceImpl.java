package com.cdk.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cdk.dao.impl.PlayerDaoImpl;
import com.cdk.entity.Player;
import com.cdk.result.Result;

import org.json.JSONArray;
import org.json.JSONException;
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
        String userId = (map.get("userId") != null ? map.get("userId").toString() : "");
        Player player = new Player();
        player.setServerId(Integer.parseInt(strServerId));
        player.setPlatformId(Integer.parseInt(strPlatformId));
        player.setPlayerName(playerName);
        player.setPlayerAccount(playerAccount);
        player.setPlayerId(Integer.parseInt(playerId));
        Result re;
        int temp = playerDaoImpl.ChangeToProhibitSpeak(player, userId);
        if (temp > 0) {
            System.out.println("玩家禁言成功");
            re = new Result(200, "玩家禁言成功", null);
        } else {
            System.out.println("玩家禁言失败");
            re = new Result(400, "玩家禁言失败", null);
        }
        return re;
    }

    public Result ChangeProhibitSpeakToNormal(Map map) {
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String strServerId = ((map.get("serverId") != null && map.get("serverId") != "") ? map.get("serverId").toString() : "0");
        String playerName = (map.get("playerName") != null ? map.get("playerName").toString() : "");
        String playerAccount = (map.get("playerAccount") != null ? map.get("playerAccount").toString() : "");
        String playerId = (map.get("playerId") != null ? map.get("playerId").toString() : "");
        String userId = (map.get("userId") != null ? map.get("userId").toString() : "");
        Player player = new Player();
        player.setServerId(Integer.parseInt(strServerId));
        player.setPlatformId(Integer.parseInt(strPlatformId));
        player.setPlayerName(playerName);
        player.setPlayerAccount(playerAccount);
        player.setPlayerId(Integer.parseInt(playerId));
        Result re;
        int temp = playerDaoImpl.ChangeProhibitSpeakToNormal(player, userId);
        if (temp > 0) {
            System.out.println("玩家解除禁言成功");
            re = new Result(200, "玩家解除禁言成功", null);
        } else {
            System.out.println("玩家解除禁言失败");
            re = new Result(400, "玩家解除禁言失败", null);
        }
        return re;
    }

    public Result ChangeToBan(Map map) {
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String strServerId = ((map.get("serverId") != null && map.get("serverId") != "") ? map.get("serverId").toString() : "0");
        String playerName = (map.get("playerName") != null ? map.get("playerName").toString() : "");
        String playerAccount = (map.get("playerAccount") != null ? map.get("playerAccount").toString() : "");
        String playerId = (map.get("playerId") != null ? map.get("playerId").toString() : "");
        String userId = (map.get("userId") != null ? map.get("userId").toString() : "");
        Player player = new Player();
        player.setServerId(Integer.parseInt(strServerId));
        player.setPlatformId(Integer.parseInt(strPlatformId));
        player.setPlayerName(playerName);
        player.setPlayerAccount(playerAccount);
        player.setPlayerId(Integer.parseInt(playerId));
        Result re;
        int temp = playerDaoImpl.ChangeToBan(player, userId);
        if (temp > 0) {
            System.out.println("玩家禁封成功");
            re = new Result(200, "玩家禁封成功", null);
        } else {
            System.out.println("玩家禁封失败");
            re = new Result(400, "玩家禁封失败", null);
        }
        return re;
    }

    public Result ChangeBanToNormal(Map map) {
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String strServerId = ((map.get("serverId") != null && map.get("serverId") != "") ? map.get("serverId").toString() : "0");
        String playerName = (map.get("playerName") != null ? map.get("playerName").toString() : "");
        String playerAccount = (map.get("playerAccount") != null ? map.get("playerAccount").toString() : "");
        String playerId = (map.get("playerId") != null ? map.get("playerId").toString() : "");
        String userId = (map.get("userId") != null ? map.get("userId").toString() : "");
        Player player = new Player();
        player.setServerId(Integer.parseInt(strServerId));
        player.setPlatformId(Integer.parseInt(strPlatformId));
        player.setPlayerName(playerName);
        player.setPlayerAccount(playerAccount);
        player.setPlayerId(Integer.parseInt(playerId));
        Result re;
        int temp = playerDaoImpl.ChangeBanToNormal(player, userId);
        if (temp > 0) {
            System.out.println("玩家解除禁封成功");
            re = new Result(200, "玩家解除禁封成功", null);
        } else {
            System.out.println("玩家解除禁封失败");
            re = new Result(400, "玩家解除禁封失败", null);
        }
        return re;
    }

    public Result ImportPlayer(Map map) {
        int len = 10;
        int platformId = 0;
        int serverId = 0;
        Result re;

        String strlist = map.get("list").toString();
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String strServerId = ((map.get("serverId") != null && map.get("serverId") != "") ? map.get("serverId").toString() : "0");
        if (Objects.equals(strPlatformId, "")) {
            System.out.println("礼包导入失败");
            re = new Result(400, "礼包导入失败", null);
            return re;
        }
        JSONArray jsonArray = null;
        try {
            platformId = Integer.parseInt(strPlatformId);
            serverId = Integer.parseInt(strServerId);
            jsonArray = new JSONArray(strlist);
            System.out.println(jsonArray);
            System.out.println(jsonArray.length());
            len = jsonArray.length();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        int[] temp = new int[len];
        temp = playerDaoImpl.ImportPlayer(jsonArray, platformId, serverId);
        if (temp.length > 0) {
            System.out.println("玩家导入成功");
            re = new Result(200, "玩家导入成功", null);
        } else {
            System.out.println("玩家导入失败");
            re = new Result(400, "玩家导入失败", null);
        }
        return re;
    }
}
