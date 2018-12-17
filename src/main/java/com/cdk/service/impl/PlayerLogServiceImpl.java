package com.cdk.service.impl;


import com.cdk.dao.impl.PlayerLogDaoImpl;
import com.cdk.entity.Player;
import com.cdk.result.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

@Service
public class PlayerLogServiceImpl {
    private static Logger logger = Logger.getLogger(String.valueOf(PlayerLogServiceImpl.class));
    public static final String Divider = "############################";
    public static final String Split = "----------------";

    @Autowired
    public PlayerLogDaoImpl playerLogDaoImpl;

    public Result getPlayerProhibitSpeakLog(Map map) {
        String playerName = (map.get("playerName") != null ? map.get("playerName").toString() : "");
        String playerAccount = (map.get("playerAccount") != null ? map.get("playerAccount").toString() : "");
        String playerId = (map.get("playerId") != null ? map.get("playerId").toString() : "");
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String strServerId = ((map.get("serverId") != null && map.get("serverId") != "") ? map.get("serverId").toString() : "0");
        String strIsToProhibitSpeak =
                ((map.get("isToProhibitSpeak") != null && map.get("isToProhibitSpeak") != "") ? map.get("isToProhibitSpeak").toString() : "0");
        String isPage = (map.get("isPage") != null ? map.get("isPage").toString() : "");
        String StrPageNo = (map.get("pageNo") != null ? map.get("pageNo").toString() : "1");
        String StrPageSize = (map.get("pageSize") != null ? map.get("pageSize").toString() : "5");
        String strPlatform = (map.get("strPlatform") != null ? map.get("strPlatform").toString() : "");
        logger.info("strPlatform：" + strPlatform);

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
        int isToProhibitSpeak = Integer.parseInt(strIsToProhibitSpeak);
        Player player = new Player();
        player.setPlatformId(platformId);
        player.setServerId(serverId);
        player.setPlayerName(playerName);
        player.setPlayerAccount(playerAccount);
        player.setIsProhibitSpeak(isToProhibitSpeak);

        Result re;
        Map<String, Object> JsonMap = playerLogDaoImpl.getPlayerProhibitSpeakLog(player, isPage, pageNo, pageSize, strPlatform);
        if (Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(400, "日志列表获取失败", "");
        } else {
            re = new Result(200, "日志列表获取成功", JsonMap);
        }

        return re;
    }


    public Result getPlayerBan(Map map) {
        String playerName = (map.get("playerName") != null ? map.get("playerName").toString() : "");
        String playerAccount = (map.get("playerAccount") != null ? map.get("playerAccount").toString() : "");
        String playerId = (map.get("playerId") != null ? map.get("playerId").toString() : "");
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String strServerId = ((map.get("serverId") != null && map.get("serverId") != "") ? map.get("serverId").toString() : "0");
        String strIsToBan = ((map.get("isToBan") != null && map.get("isToBan") != "") ? map.get("isToBan").toString() : "0");
        String isPage = (map.get("isPage") != null ? map.get("isPage").toString() : "");
        String StrPageNo = (map.get("pageNo") != null ? map.get("pageNo").toString() : "1");
        String StrPageSize = (map.get("pageSize") != null ? map.get("pageSize").toString() : "5");
        String strPlatform = (map.get("strPlatform") != null ? map.get("strPlatform").toString() : "");
        logger.info("strPlatform：" + strPlatform);

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
        int isToBan = Integer.parseInt(strIsToBan);
        Player player = new Player();
        player.setPlatformId(platformId);
        player.setServerId(serverId);
        player.setPlayerName(playerName);
        player.setPlayerAccount(playerAccount);
        player.setIsBan(isToBan);

        Result re;
        Map<String, Object> JsonMap = playerLogDaoImpl.getPlayerBan(player, isPage, pageNo, pageSize, strPlatform);
        if (Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(400, "日志列表获取失败", "");
        } else {
            re = new Result(200, "日志列表获取成功", JsonMap);
        }

        return re;
    }
}
