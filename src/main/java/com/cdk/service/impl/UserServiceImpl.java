package com.cdk.service.impl;

import com.cdk.dao.UserDao;
import com.cdk.dao.impl.UserDaoImpl;
import com.cdk.entity.User;
import com.cdk.result.Result;
import com.cdk.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    public UserDaoImpl userDaoImpl;
    public Result addUser(User user){

        Result re = new Result(200,"","");
        return re;
    }
    public Result getUser(){
        String list = userDaoImpl.find();
        System.out.println(list);
        Result re = new Result(200,"",list);
        return re;
    }
    public Result insert(String name,String password){
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        System.out.println(user);
        int temp = userDaoImpl.insert(user);
        System.out.println(temp);
        Result re=new Result(200,"","");
        if(temp> 0){
            re = new Result(200,"成功",temp);
        }
        return re;
    }
}
