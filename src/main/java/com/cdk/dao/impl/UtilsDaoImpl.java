package com.cdk.dao.impl;

import com.cdk.dao.UtilsDao;
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
}
