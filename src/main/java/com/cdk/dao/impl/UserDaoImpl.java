package com.cdk.dao.impl;

import com.cdk.dao.UserDao;
import com.cdk.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insert(User user) {
        String sql = "insert into t_user(name,password) values(?,?)";
        int temp = jdbcTemplate.update(
                sql,
                user.getName(),
                user.getPassword()
        );
        return 1;
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<User>();
        list.get(0).setName("123");
        return list;
    }

    public String find() {

        return "test";
    }


    @Override
    public int addUser(User user) {
        return 0;
    }

    @Override
    public List<User> getUser() {
        return null;
    }

    @Override
    public int editUser(User user) {
        return 0;
    }

    @Override
    public int deleteUser(int id) {
        return 0;
    }
}
