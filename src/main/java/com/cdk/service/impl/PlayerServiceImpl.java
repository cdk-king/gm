package com.cdk.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cdk.dao.impl.PlayerDaoImpl;
import com.cdk.entity.Player;
import com.cdk.result.Result;
import com.cdk.util.ApiHandeler;
import com.cdk.util.HttpRequestUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Objects;

@Service
public class PlayerServiceImpl extends ApiHandeler {
    private static Logger logger = LoggerFactory.getLogger(PlayerServiceImpl.class);

    public PlayerServiceImpl() {
        super();
    }

    @Autowired
    public PlayerDaoImpl playerDaoImpl;

    public Result getPlayerDetailInfo(Map map) {
        String gameId = ((map.get("gameId") != null && map.get("gameId") != "") ? map.get("gameId").toString() : "0");
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String WorldID = (map.get("WorldID") != null ? map.get("WorldID").toString() : "");
        String PlayerID = (map.get("PlayerID") != null ? map.get("PlayerID").toString() : "");

        long time = Math.abs(System.currentTimeMillis() / 1000);
        String strTime = time + "";
        String operator = strPlatformId;
        String key = MANAGEMENT_KEY;
        String sign = getSign(strTime, operator, key);
        String param = TIME_KEY + time + OPERATOR_KEY + operator + "&sign=" + sign;
        if (!Objects.equals(WorldID, "")) {
            param += "&WorldID=" + WorldID;
        }
        if (!Objects.equals(PlayerID, "")) {
            param += "&PlayerID=" + PlayerID;
        }
        apiUrl = getApiUrl(gameId, strPlatformId, WorldID);

        if (Objects.equals(apiUrl, "")) {
            return new Result(400, "操作失败,接口不存在", "");
        }

        String url = apiUrl + "/QueryPlayer/PlayerInfoBase";
        logger.debug(url);
        logger.debug(param);
        HttpRequestUtil httpRequestUtil = new HttpRequestUtil();
        String data = httpRequestUtil.sendGet(url, param);
        logger.debug(data);
        Result re;
        if (!data.isEmpty()) {
            re = new Result(200, "玩家详细信息获取成功", data);
        } else {
            re = new Result(400, "玩家详细信息获取失败", "");
        }
        return re;
    }

    public Result getPlayerFromServer(Map map) {
        String gameId = ((map.get("gameId") != null && map.get("gameId") != "") ? map.get("gameId").toString() : "0");
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String strServerId = ((map.get("WorldID") != null && map.get("WorldID") != "") ? map.get("WorldID").toString() : "0");
        String StrPageNo = (map.get("pageNo") != null ? map.get("pageNo").toString() : "1");
        String StrPageSize = (map.get("pageSize") != null ? map.get("pageSize").toString() : "5");
        String WorldID = (map.get("WorldID") != null ? map.get("WorldID").toString() : "");
        String AccountName = (map.get("AccountName") != null ? map.get("AccountName").toString() : "");
        String PlayerID = (map.get("PlayerID") != null ? map.get("PlayerID").toString() : "");
        String PlayerName = (map.get("PlayerName") != null ? map.get("PlayerName").toString() : "");
        String LoginBan = (map.get("LoginBan") != null ? map.get("LoginBan").toString() : "");
        String TalkBan = (map.get("TalkBan") != null ? map.get("TalkBan").toString() : "");

        int pageNo = 1;
        int pageSize = 5;
        try {
            PlayerName = URLEncoder.encode(PlayerName, "UTF-8");
            AccountName = URLEncoder.encode(AccountName, "UTF-8");
            pageNo = Integer.parseInt(StrPageNo);
            pageSize = Integer.parseInt(StrPageSize);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int platformId = Integer.parseInt(strPlatformId);
        int serverId = Integer.parseInt(strServerId);
        Player player = new Player();
        player.setPlatformId(platformId);
        player.setServerId(serverId);


        long time = Math.abs(System.currentTimeMillis() / 1000);
        String strTime = time + "";
        String operator = strPlatformId;
        String key = MANAGEMENT_KEY;
        String sign = getSign(strTime, operator, key);

        String param = TIME_KEY + time + OPERATOR_KEY + operator + "&sign=" + sign;
        if (!Objects.equals(WorldID, "")) {
            param += "&WorldID=" + WorldID;
        }
        if (!Objects.equals(AccountName, "")) {
            param += "&AccountName=" + AccountName;
        }
        if (!Objects.equals(PlayerID, "")) {
            param += "&PlayerID=" + PlayerID;
        }
        if (!Objects.equals(PlayerName, "")) {
            param += "&PlayerName=" + PlayerName;
        }
        if (!Objects.equals(LoginBan, "")) {
            param += "&LoginBan=" + LoginBan;
        }
        if (!Objects.equals(TalkBan, "")) {
            param += "&TalkBan=" + TalkBan;
        }

        apiUrl = getApiUrl(gameId, strPlatformId, strServerId);

        if (Objects.equals(apiUrl, "")) {
            return new Result(400, "操作失败,接口不存在", "");
        }

        String url = apiUrl + "/QueryPlayer/PlayerList";
        logger.debug(url);
        logger.debug(param);
        HttpRequestUtil httpRequestUtil = new HttpRequestUtil();
        String data = httpRequestUtil.sendGet(url, param);

        Result re;
        if (!data.isEmpty()) {
            re = new Result(200, "玩家列表获取成功", data);
        } else {
            re = new Result(400, "玩家列表获取失败", "");
        }

        return re;
    }

    public Result getPlayer(Map map) {
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String strServerId = ((map.get("serverId") != null && map.get("serverId") != "") ? map.get("serverId").toString() : "0");
        String isPage = (map.get("isPage") != null ? map.get("isPage").toString() : "");
        String StrPageNo = (map.get("pageNo") != null ? map.get("pageNo").toString() : "1");
        String StrPageSize = (map.get("pageSize") != null ? map.get("pageSize").toString() : "5");
        String strSearchForm = (map.get("searchForm") != null ? map.get("searchForm").toString() : "");
        JSONObject jsonObject = JSON.parseObject(strSearchForm);
        //将jsonObj转换成Map
        Map<String, Object> searchForm = JSONObject.toJavaObject(jsonObject, Map.class);
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


    public Result Ban(Map map) {
        String gameId = ((map.get("gameId") != null && map.get("gameId") != "") ? map.get("gameId").toString() : "0");
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String WorldID = (map.get("WorldID") != null ? map.get("WorldID").toString() : "");
        String PlayerIds = (map.get("PlayerIds") != null ? map.get("PlayerIds").toString() : "");
        String AccountName = (map.get("AccountName") != null ? map.get("AccountName").toString() : "");
        String PlayerName = (map.get("playerName") != null ? map.get("playerName").toString() : "");
        String Remove = (map.get("Remove") != null ? map.get("Remove").toString() : "");
        String HowLong = ((map.get("HowLong") != null && map.get("HowLong") != "") ? map.get("HowLong").toString() : "0");
        String userId = (map.get("userId") != null ? map.get("userId").toString() : "");
        String decodePlayerName = PlayerName;
        try {
            PlayerName = URLEncoder.encode(PlayerName, "UTF-8");
            AccountName = URLEncoder.encode(AccountName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        long time = Math.abs(System.currentTimeMillis() / 1000);
        String strTime = time + "";
        String operator = strPlatformId;
        String key = MANAGEMENT_KEY;
        String sign = getSign(strTime, operator, key);
        String param = TIME_KEY + time + OPERATOR_KEY + operator + "&sign=" + sign;
        if (!Objects.equals(WorldID, "")) {
            param += "&WorldID=" + WorldID;
        }
        if (!Objects.equals(PlayerIds, "")) {
            param += "&PlayerIds=" + PlayerIds;
        } else if (!Objects.equals(AccountName, "")) {
            param += "&AccountName=" + AccountName;
        }
        if (!Objects.equals(Remove, "")) {
            param += "&Remove=" + Remove;
        }
        if (!Objects.equals(HowLong, "")) {
            param += "&HowLong=" + HowLong;
        }
        apiUrl = getApiUrl(gameId, strPlatformId, WorldID);

        if (Objects.equals(apiUrl, "")) {
            return new Result(400, "操作失败,接口不存在", "");
        }

        String url = apiUrl + "/UpdateAccount/FreezeAccount";
        HttpRequestUtil httpRequestUtil = new HttpRequestUtil();
        String data = httpRequestUtil.sendGet(url, param);
        Result re;
        if (!data.isEmpty()) {

            Player player = new Player();
            player.setServerId(Integer.parseInt(WorldID));
            player.setPlatformId(Integer.parseInt(strPlatformId));
            player.setPlayerAccount(AccountName);
            player.setBanTime(Integer.parseInt(HowLong));

            if (Objects.equals(Remove, "0")) {
                playerDaoImpl.SaveBanLog(player, userId, PlayerIds, decodePlayerName, gameId);
                re = new Result(200, "玩家禁封成功", data);
            } else {
                playerDaoImpl.SaveBanToNormalLog(player, userId, PlayerIds, decodePlayerName, gameId);
                re = new Result(200, "玩家解除禁封成功", data);
            }
        } else {
            if (Objects.equals(Remove, "0")) {
                re = new Result(400, "玩家禁封失败", data);
            } else {
                re = new Result(400, "玩家解除禁封失败", data);
            }
        }
        return re;
    }

    public Result talkBan(Map map) {
        String gameId = ((map.get("gameId") != null && map.get("gameId") != "") ? map.get("gameId").toString() : "0");
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String WorldID = (map.get("WorldID") != null ? map.get("WorldID").toString() : "");
        String PlayerID = (map.get("PlayerID") != null ? map.get("PlayerID").toString() : "");
        String PlayerName = (map.get("PlayerName") != null ? map.get("PlayerName").toString() : "");
        String PlayerAccount = (map.get("PlayerAccount") != null ? map.get("PlayerAccount").toString() : "");
        String Remove = (map.get("Remove") != null ? map.get("Remove").toString() : "");
        String HowLong = ((map.get("HowLong") != null && map.get("HowLong") != "") ? map.get("HowLong").toString() : "0");
        String userId = (map.get("userId") != null ? map.get("userId").toString() : "");
        String decodePlayerName = PlayerName;
        try {
            PlayerName = URLEncoder.encode(PlayerName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        long time = Math.abs(System.currentTimeMillis() / 1000);
        String strTime = time + "";
        String operator = strPlatformId;
        String key = MANAGEMENT_KEY;
        String sign = getSign(strTime, operator, key);
        String param = TIME_KEY + time + OPERATOR_KEY + operator + "&sign=" + sign;
        if (!Objects.equals(WorldID, "")) {
            param += "&WorldID=" + WorldID;
        }
        if (!Objects.equals(PlayerID, "")) {
            param += "&PlayerID=" + PlayerID;
        } else if (!Objects.equals(PlayerName, "")) {
            param += "&PlayerName=" + PlayerName;
        }
        if (!Objects.equals(Remove, "")) {
            param += "&Remove=" + Remove;
        }
        if (!Objects.equals(HowLong, "")) {
            param += "&HowLong=" + HowLong;
        }
        apiUrl = getApiUrl(gameId, strPlatformId, WorldID);

        if (Objects.equals(apiUrl, "")) {
            return new Result(400, "操作失败,接口不存在", "");
        }

        if (Objects.equals(apiUrl, "")) {
            return new Result(400, "操作失败,接口不存在", "");
        }

        String url = apiUrl + "/UpdatePlayer/GagPlayer";
        HttpRequestUtil httpRequestUtil = new HttpRequestUtil();
        String data = httpRequestUtil.sendGet(url, param);

        Result re;
        if (!data.isEmpty()) {
            Player player = new Player();
            player.setServerId(Integer.parseInt(WorldID));
            player.setPlatformId(Integer.parseInt(strPlatformId));
            player.setPlayerName(decodePlayerName);
            player.setPlayerId(PlayerID);
            player.setPlayerAccount(PlayerAccount);
            player.setProhibitSpeakTime(Integer.parseInt(HowLong));
            if (Objects.equals(Remove, "0")) {
                playerDaoImpl.SaveProhibitSpeakLog(player, userId, gameId);
                re = new Result(200, "玩家禁言成功", data);
            } else {
                playerDaoImpl.SaveProhibitSpeakToNormalLog(player, userId, gameId);
                re = new Result(200, "玩家解除禁言成功", data);
            }
        } else {
            if (Objects.equals(Remove, "0")) {
                re = new Result(400, "玩家禁言失败", data);
            } else {
                re = new Result(400, "玩家解除禁言失败", data);
            }
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
        String strProhibitSpeakTime = (map.get("prohibitSpeakTime") != null ? map.get("prohibitSpeakTime").toString() : "0");
        int prohibitSpeakTime = Integer.parseInt(strProhibitSpeakTime);
        Player player = new Player();
        player.setServerId(Integer.parseInt(strServerId));
        player.setPlatformId(Integer.parseInt(strPlatformId));
        player.setPlayerName(playerName);
        player.setPlayerAccount(playerAccount);
        player.setPlayerId(playerId);
        player.setProhibitSpeakTime(prohibitSpeakTime);
        Result re;
        int temp = playerDaoImpl.ChangeToProhibitSpeak(player, userId);
        if (temp > 0) {
            logger.debug("玩家禁言成功");
            re = new Result(200, "玩家禁言成功", null);
        } else {
            logger.debug("玩家禁言失败");
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
        player.setPlayerId(playerId);
        Result re;
        int temp = playerDaoImpl.ChangeProhibitSpeakToNormal(player, userId);
        if (temp > 0) {
            logger.debug("玩家解除禁言成功");
            re = new Result(200, "玩家解除禁言成功", null);
        } else {
            logger.debug("玩家解除禁言失败");
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
        String strBanTime = (map.get("banTime") != null ? map.get("banTime").toString() : "0");
        int banTime = Integer.parseInt(strBanTime);
        Player player = new Player();
        player.setServerId(Integer.parseInt(strServerId));
        player.setPlatformId(Integer.parseInt(strPlatformId));
        player.setPlayerName(playerName);
        player.setPlayerAccount(playerAccount);
        player.setPlayerId(playerId);
        player.setBanTime(banTime);
        Result re;
        int temp = playerDaoImpl.ChangeToBan(player, userId);
        if (temp > 0) {
            logger.debug("玩家禁封成功");
            re = new Result(200, "玩家禁封成功", null);
        } else {
            logger.debug("玩家禁封失败");
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
        player.setPlayerId(playerId);
        Result re;
        int temp = playerDaoImpl.ChangeBanToNormal(player, userId);
        if (temp > 0) {
            logger.debug("玩家解除禁封成功");
            re = new Result(200, "玩家解除禁封成功", null);
        } else {
            logger.debug("玩家解除禁封失败");
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
            logger.debug("礼包导入失败");
            re = new Result(400, "礼包导入失败", null);
            return re;
        }
        JSONArray jsonArray = null;
        try {
            platformId = Integer.parseInt(strPlatformId);
            serverId = Integer.parseInt(strServerId);
            jsonArray = new JSONArray(strlist);
            len = jsonArray.length();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        int[] temp = new int[len];
        temp = playerDaoImpl.ImportPlayer(jsonArray, platformId, serverId);
        if (temp.length > 0) {
            logger.debug("玩家导入成功");
            re = new Result(200, "玩家导入成功", null);
        } else {
            logger.debug("玩家导入失败");
            re = new Result(400, "玩家导入失败", null);
        }
        return re;
    }
}
