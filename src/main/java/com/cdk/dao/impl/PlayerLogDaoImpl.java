package com.cdk.dao.impl;

import com.cdk.entity.Player;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class PlayerLogDaoImpl {
    private static Logger logger = LoggerFactory.getLogger(PlayerLogDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> getPlayerBan(Player player, String isPage, int pageNo, int pageSize, String strPlatform, String gameId) {
        String sql =
                "select a.* , b.platform ,c.server,d.name as userName from t_player_ban_log as a join  t_gameplatform as b on a.platformId = b.platformId join t_gameserver as c on a.serverId = c.serverId join t_user as d on a.userId = d.id join t_game as e on b.gameId = e.id and e.isDelete!=1 where e.id='" +
                        gameId + "' and a.gameId='" + gameId + "' and a.platformId IN (" + strPlatform +
                        ")  and b.isDelete != 1 and c.isDelete != 1 and d.isDelete != 1 ";
        if (player.getPlatformId() != 0) {
            sql += " and a.platformId ='" + player.getPlatformId() + "' ";
        }
        if (player.getServerId() != 0) {
            sql += " and a.serverId ='" + player.getServerId() + "' ";
        }
        if (player.getPlayerName() != "") {
            sql += " and a.playerName LIKE '%" + player.getPlayerName() + "%'";
        }

        if (player.getPlayerAccount() != "") {
            sql += " and a.playerAccount LIKE '%" + player.getPlayerAccount() + "%'";
        }

        if (Objects.equals(player.getIsBan(), 1)) {
            sql += " and a.isToBan = '0'";
        }
        if (Objects.equals(player.getIsBan(), 2)) {
            sql += " and a.isToBan = '1'";
        }

        logger.debug("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        int total = list.size();
        sql += "  order by a.addDatetime desc";
        if (!Objects.equals(isPage, "")) {
            sql += " limit " + (pageNo - 1) * pageSize + ", " + pageSize;
        }
        list = jdbcTemplate.queryForList(sql);
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        JsonMap.put("total", total);
        return JsonMap;
    }

    public Map<String, Object> getPlayerProhibitSpeakLog(Player player, String isPage, int pageNo, int pageSize, String strPlatform, String gameId) {
        String sql =
                "select a.* , b.platform ,c.server,d.name as userName from t_player_prohibitspeak_log as a join  t_gameplatform as b on a.platformId = b.platformId join t_gameserver as c on a.serverId = c.serverId join t_user as d on a.userId = d.id join t_game as e on e.id = b.gameId and e.isDelete!=1 where e.id='" +
                        gameId + "' and a.gameId = '" + gameId + "' and a.platformId IN (" + strPlatform +
                        ")  and b.isDelete != 1 and c.isDelete != 1 and d.isDelete != 1 ";
        if (player.getPlatformId() != 0) {
            sql += " and a.platformId ='" + player.getPlatformId() + "' ";
        }
        if (player.getServerId() != 0) {
            sql += " and a.serverId ='" + player.getServerId() + "' ";
        }
        if (player.getPlayerName() != "") {
            sql += " and a.playerName LIKE '%" + player.getPlayerName() + "%'";
        }

        if (player.getPlayerAccount() != "") {
            sql += " and a.playerAccount LIKE '%" + player.getPlayerAccount() + "%'";
        }
        if (Objects.equals(player.getIsProhibitSpeak(), 1)) {
            sql += " and a.isToProhibitSpeak = '0'";
        }
        if (Objects.equals(player.getIsProhibitSpeak(), 2)) {
            sql += " and a.isToProhibitSpeak = '1'";
        }

        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        int total = list.size();
        sql += "  order by a.addDatetime desc";
        if (!Objects.equals(isPage, "")) {
            sql += " limit " + (pageNo - 1) * pageSize + ", " + pageSize;
        }

        list = jdbcTemplate.queryForList(sql);
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        JsonMap.put("total", total);
        return JsonMap;
    }
}
