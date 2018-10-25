package com.cdk.service;

import com.cdk.dao.UserDao;
import com.cdk.entity.User;
import com.cdk.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface UserService {
    public Result addUser(Map map);
    public Result getUser(Map map);
    public Result editUser(Map map);
    public Result editPassword(Map map);
    public Result changeStateToFrozen_User(Map map);
    public Result changeStateToNormal_User(Map map);
}
