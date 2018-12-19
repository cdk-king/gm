package com.cdk.dao.impl;

import com.cdk.dao.UtilsDao;
import com.cdk.entity.Game;
import com.cdk.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Repository
public class UtilsDaoImpl implements UtilsDao {
    private static Logger logger = Logger.getLogger(String.valueOf(UtilsDaoImpl.class));
    public static final String Divider = "############################";
    public static final String Split = "----------------";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> getUserAllRight(User user) {
        String sql = "SELECT e.id,e.name,c.role,a.rightName,a.rightTag from t_right as a join\n" +
                "t_role_rights as b on a.id = b.rightId join t_role as c on c.id = b.roleId " +
                "join t_user_roles as d on c.id = d.roleId join t_user as e on d.userId = e.id " +
                "where e.isDelete != 1 and c.isDelete!=1 and a.isDelete!=1  and  e.id = " + user.getId();

        logger.info("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    public List<Map<String, Object>> getServerUrl(String platformId) {
        String sql = "SELECT * from t_gameserver where platformId = " + platformId + " and isDelete!=1";
        logger.info("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    @Override
    public List<Map<String, Object>> getUserAllRole(User user) {
        String sql =
                "SELECT a.id,c.name,a.role from t_role as a " + "join t_user_roles as b on a.id = b.roleId join t_user as c on b.userId = c.id " +
                        "where a.isDelete != 1 and c.isDelete!=1 and  c.id = " + user.getId();
        logger.info("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    @Override
    public List<Map<String, Object>> getGameListForUser(User user) {
        String sql = "select a.id  ,a.gameName  from t_game as a \n" +
                " join t_gameplatform as b on b.gameId = a.id   join t_role as c on b.roleId = c.id  " +
                "join t_user_roles as d on c.id = d.roleId join t_user as e on d.userId = e.id " + "where e.id = '" + user.getId() +
                "' and a.isDelete != 1 and b.isDelete!=1  and c.isDelete!=1 and d.isDelete!=1 and e.isDelete != 1 group by a.id";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    @Override
    public List<Map<String, Object>> getPlatformListForGameId(Game game) {
        String sql =
                "select a.platformId,a.platform from t_gameplatform as a join t_game as b on a.gameId = b.id " + "where b.id = '" + game.getId() +
                        "' and a.isDelete!=1 and b.isDelete!=1  GROUP BY a.id ";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    @Override
    public List<Map<String, Object>> getPlatformListForUserIdAndGameId(User user, Game game) {
        String sql = "SELECT d.platformId, d.platform   from t_user as a \n" + "join t_user_roles as b on a.id = b.userId \n" +
                "join t_role as c on b.roleId = c.id \n" + "join t_gameplatform as d on c.id = d.roleId  \n" +
                "where a.isDelete != 1 and c.isDelete!=1 and d.isDelete!=1 and  a.id ='" + user.getId() + "' and d.gameId ='" + game.getId() + "' ";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    public int getPlatformIdForServerId(String serverId) {
        String sql = "SELECT platformId from t_gameserver where serverId= '" + serverId + "' and isDelete!=1";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        int platformId = Integer.parseInt(list.get(0).get("platformId").toString());
        return platformId;
    }

    public Map<String, Object> getValueTypeList(int gameId, String allow) {
        String sql = "select * from t_prize_valuetype where valueTypeId  in (" + allow + ") and gameId=" + gameId;
        logger.info("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        list = jdbcTemplate.queryForList(sql);
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        return JsonMap;
    }
}
