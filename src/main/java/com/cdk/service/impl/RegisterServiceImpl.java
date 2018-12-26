package com.cdk.service.impl;

import com.cdk.dao.impl.RegisterDaoImpl;
import com.cdk.entity.User;
import com.cdk.result.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.logging.Logger;

@Service
public class RegisterServiceImpl {
    private static Logger logger = Logger.getLogger(String.valueOf(RegisterServiceImpl.class));
    @Autowired
    public RegisterDaoImpl registerDaoImpl;

    public Result addRegisterUser(Map map) {
        String strUser = (map.get("name") != null ? map.get("name").toString() : "");
        String password = (map.get("password") != null ? map.get("password").toString() : "");
        String phone = (map.get("phone") != null ? map.get("phone").toString() : "");
        Result re;
        User user = new User();
        user.setName(strUser);
        user.setPassword(password);
        user.setPhone(phone);
        int check = registerDaoImpl.checkRegisterUser(user);
        if (check > 0) {
            return new Result(400, "存在相同的用户名", "");
        }
        int temp = registerDaoImpl.addRegisterUser(user);
        if (temp > 0) {

            re = new Result(200, "用户注册成功", "");
        } else {
            re = new Result(400, "用户注册失败", "");
        }
        return re;
    }


}
