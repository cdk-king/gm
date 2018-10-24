package com.cdk.dao;

import com.cdk.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserDao {

    public int addUser(User user);

    @Query("select bean from User bean ")
    public List<User> getUser();

    public int editUser(User user);

    public int deleteUser(int id);
}

