package com.cdk.dao.impl;

import com.cdk.entity.Player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class PlayerDaoImpl {


    public static final String Divider = "############################";
    public static final String Split = "----------------";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> getPlayer(Player player, String isPage, int pageNo, int pageSize, Map searchForm) {
        String sql =
                "select a.* , b.platform ,c.server from t_player as a join  t_gameplatform as b on a.platformId = b.id join t_gameserver as c on a.serverId = c.id where b.isDelete != 1 and c.isDelete != 1 ";
        if (player.getPlatformId() != 0) {
            sql += " and a.platformId ='" + player.getPlatformId() + "' ";
        }
        if (player.getServerId() != 0) {
            sql += " and a.serverId='" + player.getServerId() + "' ";
        }
        System.out.println("playerName:" + searchForm.get("playerName"));
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
        System.out.println("isOnline:" + searchForm.get("isOnline"));
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
        System.out.println(searchForm.get("minRegistrationTime"));
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

        System.out.println("sql：" + sql);
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

    public int ChangeToProhibitSpeak(Player player) {
        String sql = "UPDATE t_player SET isProhibitSpeak='1' where playerName ='" + player.getPlayerName() + "' and  playerAccount = '" +
                player.getPlayerAccount() + "' and playerId = '" + player.getPlayerId() + "' and  platformId = '" + player.getPlatformId() +
                "' and serverId = '" + player.getServerId() + "'    ";
        System.out.println(sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

}
