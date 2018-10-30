package com.cdk.dao.impl;

import com.cdk.dao.LoginDao;
import com.cdk.entity.VueLoginInfoVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class LoginDaoImpl implements LoginDao {
    public static final String Divider = "############################";
    public static final String Split = "----------------";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> login(VueLoginInfoVo loginInfoVo) {
        String sql = "select * from t_user where name = '" + loginInfoVo.getUsername() + "' order by id desc limit 1";
        System.out.println("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }
}
