package com.cdk.dao.impl;

import com.cdk.entity.Player;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class PlayerDaoImpl {
    private static Logger logger = LoggerFactory.getLogger(PlayerDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> getPlayer(Player player, String isPage, int pageNo, int pageSize, Map searchForm) {
        String sql =
                "select a.* , b.platform ,c.server from t_player as a join  t_gameplatform as b on a.platformId = b.platformId join t_gameserver as c on a.serverId = c.serverId where b.isDelete != 1 and c.isDelete != 1 ";
        if (player.getPlatformId() != 0) {
            sql += " and a.platformId ='" + player.getPlatformId() + "' ";
        }
        if (player.getServerId() != 0) {
            sql += " and a.serverId='" + player.getServerId() + "' ";
        }
        if (!Objects.equals(searchForm.get("playerName"), "")) {
            sql += " and a.playerName='" + searchForm.get("playerName") + "' ";
        }
        if (!Objects.equals(searchForm.get("playerAccount"), "")) {
            sql += " and a.playerAccount='" + searchForm.get("playerAccount") + "' ";
        }
        if (!Objects.equals(searchForm.get("playerId"), "")) {
            sql += " and a.playerId='" + searchForm.get("playerId") + "' ";
        }
        if (!Objects.equals(searchForm.get("playerId"), "")) {
            sql += " and a.playerId='" + searchForm.get("playerId") + "' ";
        }
        if (!Objects.equals(searchForm.get("lastIp"), "")) {
            sql += " and a.lastIp='" + searchForm.get("lastIp") + "' ";
        }
        if (!Objects.equals(searchForm.get("isOnline"), "")) {
            if (Objects.equals(searchForm.get("isOnline"), "1")) {
                sql += " and a.isOnline='0' ";
            }
            if (Objects.equals(searchForm.get("isOnline"), "2")) {
                sql += " and a.isOnline='1' ";
            }
        }
        if (!Objects.equals(searchForm.get("isProhibitSpeak"), "")) {
            if (Objects.equals(searchForm.get("isProhibitSpeak"), "1")) {
                sql += " and a.isProhibitSpeak='0' ";
            }
            if (Objects.equals(searchForm.get("isProhibitSpeak"), "2")) {
                sql += " and a.isProhibitSpeak='1' ";
            }
        }
        if (!Objects.equals(searchForm.get("isBan"), "")) {
            if (Objects.equals(searchForm.get("isBan"), "1")) {
                sql += " and a.isBan='0' ";
            }
            if (Objects.equals(searchForm.get("isBan"), "2")) {
                sql += " and a.isBan='1' ";
            }
        }
        if (!Objects.equals(searchForm.get("minVipLevel"), "") && !Objects.equals(searchForm.get("minVipLevel"), null)) {
            sql += " and a.vipLevel>='" + searchForm.get("minVipLevel") + "' ";
        }
        if (!Objects.equals(searchForm.get("maxVipLevel"), "") && !Objects.equals(searchForm.get("maxVipLevel"), null)) {
            sql += " and a.vipLevel<='" + searchForm.get("maxVipLevel") + "' ";
        }

        if (!Objects.equals(searchForm.get("minDiamond"), "") && !Objects.equals(searchForm.get("minDiamond"), null)) {
            sql += " and a.diamond>='" + searchForm.get("minDiamond") + "' ";
        }
        if (!Objects.equals(searchForm.get("maxDiamond"), "") && !Objects.equals(searchForm.get("maxDiamond"), null)) {
            sql += " and a.diamond<='" + searchForm.get("maxDiamond") + "' ";
        }

        if (!Objects.equals(searchForm.get("minRechargeAmount"), "") && !Objects.equals(searchForm.get("minRechargeAmount"), null)) {
            sql += " and a.rechargeAmount>='" + searchForm.get("minRechargeAmount") + "' ";
        }
        if (!Objects.equals(searchForm.get("maxRechargeAmount"), "") && !Objects.equals(searchForm.get("maxRechargeAmount"), null)) {
            sql += " and a.rechargeAmount<='" + searchForm.get("maxRechargeAmount") + "' ";
        }

        if (!Objects.equals(searchForm.get("minLevel"), "") && !Objects.equals(searchForm.get("minLevel"), null)) {
            sql += " and a.level>='" + searchForm.get("minLevel") + "' ";
        }
        if (!Objects.equals(searchForm.get("maxLevel"), "") && !Objects.equals(searchForm.get("maxLevel"), null)) {
            sql += " and a.level<='" + searchForm.get("maxLevel") + "' ";
        }
        if (!Objects.equals(searchForm.get("minRegistrationTime"), "") && !Objects.equals(searchForm.get("minRegistrationTime"), null)) {
            sql += " and a.registrationTime>='" + searchForm.get("minRegistrationTime") + "' ";
        }
        if (!Objects.equals(searchForm.get("maxRegistrationTime"), "") && !Objects.equals(searchForm.get("maxRegistrationTime"), null)) {
            sql += " and a.registrationTime<='" + searchForm.get("maxRegistrationTime") + "' ";
        }

        if (!Objects.equals(searchForm.get("minCombatPower"), "") && !Objects.equals(searchForm.get("minCombatPower"), null)) {
            sql += " and a.combatPower>='" + searchForm.get("minCombatPower") + "' ";
        }
        if (!Objects.equals(searchForm.get("maxCombatPower"), "") && !Objects.equals(searchForm.get("maxCombatPower"), null)) {
            sql += " and a.combatPower<='" + searchForm.get("maxCombatPower") + "' ";
        }

        logger.debug("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        int total = list.size();
        if (!Objects.equals(isPage, "")) {
            sql += " limit " + (pageNo - 1) * pageSize + ", " + pageSize;
        }

        list = jdbcTemplate.queryForList(sql);
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        JsonMap.put("total", total);
        return JsonMap;
    }

    public int ChangeToProhibitSpeak(Player player, String userId) {
        String sql = "UPDATE t_player SET isProhibitSpeak='1',prohibitSpeakTime = '" + player.getProhibitSpeakTime() + "'  where playerName ='" +
                player.getPlayerName() + "' and  playerAccount = '" + player.getPlayerAccount() + "' and playerId = '" + player.getPlayerId() +
                "' and  platformId = '" + player.getPlatformId() + "' and serverId = '" + player.getServerId() + "' ";
        logger.debug(sql);
        int temp = jdbcTemplate.update(sql);
        if (temp > 0) {
            //SaveProhibitSpeakLog(player, userId);
        }
        return temp;
    }

    public void SaveProhibitSpeakLog(Player player, String userId, String playerId, String playerAccount) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        String sql =
                "insert t_player_prohibitspeak_log (playerName,playerId,playerAccount,platformId,serverId,addDatetime,userId,isToProhibitSpeak,prohibitSpeakTime) values ('" +
                        player.getPlayerName() + "','" + playerId + "','" + playerAccount + "','" + player.getPlatformId() + "','" +
                        player.getServerId() + "','" + addDatetime + "','" + userId + "','1','" + player.getProhibitSpeakTime() + "')  ";
        logger.debug(sql);
        int temp = jdbcTemplate.update(sql);
        if (temp > 0) {
            logger.debug("禁言日志保存成功");
        } else {
            logger.debug("禁言日志保存失败");
        }
    }

    public int ChangeProhibitSpeakToNormal(Player player, String userId) {
        String sql = "UPDATE t_player SET isProhibitSpeak='0' where playerName ='" + player.getPlayerName() + "' and  playerAccount = '" +
                player.getPlayerAccount() + "' and playerId = '" + player.getPlayerId() + "' and  platformId = '" + player.getPlatformId() +
                "' and serverId = '" + player.getServerId() + "' ";
        logger.debug(sql);
        int temp = jdbcTemplate.update(sql);
        if (temp > 0) {
        }
        return temp;
    }

    public void SaveProhibitSpeakToNormalLog(Player player, String userId, String playerId, String playerAccount) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        String sql =
                "insert t_player_prohibitspeak_log (playerName,playerId,playerAccount,platformId,serverId,addDatetime,userId,isToProhibitSpeak,prohibitSpeakTime) values ('" +
                        player.getPlayerName() + "','" + playerId + "','" + playerAccount + "','" + player.getPlatformId() + "','" +
                        player.getServerId() + "','" + addDatetime + "','" + userId + "','0','0')  ";
        logger.debug(sql);
        int temp = jdbcTemplate.update(sql);
        if (temp > 0) {
            logger.debug("解除禁言日志保存成功");
        } else {
            logger.debug("解除禁言日志保存失败");
        }
    }

    public int ChangeToBan(Player player, String userId) {
        String sql = "UPDATE t_player SET isBan='1',banTime = '" + player.getBanTime() + "'  where playerName ='" + player.getPlayerName() +
                "' and  playerAccount = '" + player.getPlayerAccount() + "' and playerId = '" + player.getPlayerId() + "' and  platformId = '" +
                player.getPlatformId() + "' and serverId = '" + player.getServerId() + "'   ";
        logger.debug(sql);
        int temp = jdbcTemplate.update(sql);
        if (temp > 0) {
        }
        return temp;
    }

    public void SaveBanLog(Player player, String userId, String PlayerIds, String PlayerName) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        String sql = "insert t_player_ban_log (playerAccount,playerId,playerName,platformId,serverId,addDatetime,userId,isToBan,banTime) values ('" +
                player.getPlayerAccount() + "','" + PlayerIds + "','" + PlayerName + "','" + player.getPlatformId() + "','" + player.getServerId() +
                "','" + addDatetime + "','" + userId + "','1','" + player.getBanTime() + "')  ";
        logger.debug(sql);
        int temp = jdbcTemplate.update(sql);
        if (temp > 0) {
            logger.debug("禁封日志保存成功");
        } else {
            logger.debug("禁封日志保存失败");
        }
    }

    public int ChangeBanToNormal(Player player, String userId) {
        String sql = "UPDATE t_player SET isBan='0' where playerName ='" + player.getPlayerName() + "' and  playerAccount = '" +
                player.getPlayerAccount() + "' and playerId = '" + player.getPlayerId() + "' and  platformId = '" + player.getPlatformId() +
                "' and serverId = '" + player.getServerId() + "' ";
        logger.debug(sql);
        int temp = jdbcTemplate.update(sql);
        if (temp > 0) {
        }
        return temp;
    }

    public void SaveBanToNormalLog(Player player, String userId, String PlayerIds, String PlayerName) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        String sql = "insert t_player_ban_log (playerAccount,playerId,playerName,platformId,serverId,addDatetime,userId,isToBan,banTime) values ('" +
                player.getPlayerAccount() + "','" + PlayerIds + "','" + PlayerName + "','" + player.getPlatformId() + "','" + player.getServerId() +
                "','" + addDatetime + "','" + userId + "','0','0')  ";
        logger.debug(sql);
        int temp = jdbcTemplate.update(sql);
        if (temp > 0) {
            logger.debug("解除禁封日志保存成功");
        } else {
            logger.debug("解除禁封日志保存失败");
        }
    }

    public int[] ImportPlayer(JSONArray jsonArray, int platformId, int serverId) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        String sql[] = new String[jsonArray.length()];
        String strSql = "";
        //清空数据库表
        strSql = "truncate table t_player";
        jdbcTemplate.update(strSql);
        int[] temp = new int[jsonArray.length()];
        for (int i = 1; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                sql[i] = "insert into t_player (playerName,playerAccount,playerId,isOnline,lastIp,vipLevel,diamond,rechargeAmount," +
                        "level,registrationTime,combatPower,addDateTime,isProhibitSpeak,isBan,platformId,serverId) values ( '" +
                        jsonObject.get("playerName") + "','" + jsonObject.get("playerAccount") + "','" + jsonObject.get("playerId") + "', '" +
                        jsonObject.get("isOnline") + "'  ,'" + jsonObject.get("lastIp") + "'  ,'" + jsonObject.get("vipLevel") + "' ,'" +
                        jsonObject.get("diamond") + "'  ,'" + jsonObject.get("rechargeAmount") + "'  ,'" + jsonObject.get("level") + "'  ,  '" +
                        jsonObject.get("registrationTime") + "'  ,'" + jsonObject.get("combatPower") + "'  ,'" + addDatetime + "'  ,'" +
                        jsonObject.get("isProhibitSpeak") + "'  ,'" + jsonObject.get("isBan") + "'  ,'" + platformId + "'  ,'" + serverId + "' ) ; ";
                strSql += sql;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        temp = jdbcTemplate.batchUpdate(sql);
        return temp;
    }
}
