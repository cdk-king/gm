package com.cdk.dao.impl;

import com.cdk.entity.User;
import com.cdk.entity.VueLoginInfoVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class LoginDaoImpl {
    private static Logger logger = LoggerFactory.getLogger(LoginDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> login(VueLoginInfoVo loginInfoVo) {
        String sql = "select * from t_user where account = '" + loginInfoVo.getUsername() + "' and isDelete!=1  order by id desc limit 1";
        logger.debug("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    public List<Map<String, Object>> getTouristId() {
        String sql = "select * from t_config where configKey = 'touristId' order by id desc limit 1";
        logger.debug("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    public List<Map<String, Object>> getTouristName() {
        String sql = "select * from t_config where configKey = 'touristName' order by id desc limit 1";
        logger.debug("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    public int setTouristId(String userId) {
        String sql = "update t_config set configValue='" + userId + "' where configKey = 'touristId' order by id desc limit 1";
        logger.debug("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int setTouristName(String name) {
        String sql = "update t_config set configValue='" + name + "' where configKey = 'touristName' order by id desc limit 1";
        logger.debug("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public List<Map<String, Object>> getThisUserInfo(User user) {
        String sql = "select * from t_user where name = '" + user.getName() + "' order by id desc limit 1";
        logger.debug("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }
}
