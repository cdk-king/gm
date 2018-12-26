package com.cdk.dao.impl;

import com.cdk.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

@Repository
public class RegisterDaoImpl {
    private static Logger logger = Logger.getLogger(String.valueOf(RegisterDaoImpl.class));
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int addRegisterUser(User user) {
        String addDatetime = df.format(new Date());
        String sql = "insert into t_user (name,account,password,nick,phone,email,state,addDatetime,lastDatetime,isDelete) " + " values ('" +
                user.getName() + "','" + user.getName() + "','" + user.getPassword() + "','" + user.getName() + "','" + user.getPhone() + "'" +
                ",'','0','" + addDatetime + "','" + addDatetime + "','0')";
        logger.info("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int checkRegisterUser(User user) {
        String sql = "select * from t_user where name='" + user.getName() + "' and isDelete!=1";
        logger.info("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

}
