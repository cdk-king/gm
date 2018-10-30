package com.cdk.dao.impl;

import com.cdk.dao.UtilsDao;
import com.cdk.entity.Game;
import com.cdk.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UtilsDaoImpl implements UtilsDao {
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

        System.out.println("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    @Override
    public List<Map<String, Object>> getUserAllRole(User user) {
        String sql =
                "SELECT a.id,c.name,a.role from t_role as a " + "join t_user_roles as b on a.id = b.roleId join t_user as c on b.userId = c.id " +
                        "where a.isDelete != 1 and c.isDelete!=1 and  c.id = " + user.getId();
        System.out.println("sql：" + sql);
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
        String sql = "select a.id,a.platform from t_gameplatform as a join t_game as b on a.gameId = b.id " + "where b.id = '" + game.getId() +
                "' and a.isDelete!=1 and b.isDelete!=1  GROUP BY a.id ";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }
}
