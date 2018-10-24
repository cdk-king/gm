package com.cdk.service;

import com.cdk.dao.UserDao;
import com.cdk.entity.User;
import com.cdk.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
public interface UserService {
    public Result addUser(User user);
    public Result getUser();

}
