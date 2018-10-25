package com.cdk.dao;

import com.cdk.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

public interface UserDao {

    int test();

    public int addUser(User user);

    public Map<String,Object> getUser(User user, int pageNo, int pageSize);

    public int editUser(User user);

    public int editPassword(User user);

    public int changeStateToFrozen_User(User user);

    public int changeStateToNormal_User(User user);

    public int deleteUser(User user);

    public int[] deleteAllUser(String[] idList);

    public int insertUserRoles(String id,String[] roleList);

    public int deleteUserRoles(String id,String[] roleList);
}

