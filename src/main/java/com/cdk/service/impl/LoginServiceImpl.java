package com.cdk.service.impl;

import com.cdk.dao.impl.LoginDaoImpl;
import com.cdk.entity.VueLoginInfoVo;
import com.cdk.result.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl {

    public static final String Divider = "############################";
    public static final String Split = "----------------";
    @Autowired
    public LoginDaoImpl loginDaoImpl;

    public Result login(VueLoginInfoVo loginInfoVo) {
        String username = loginInfoVo.getUsername();
        String password = loginInfoVo.getPassword();
        Result re;
        List<Map<String, Object>> list = loginDaoImpl.login(loginInfoVo);
        if (list != null && list.size() >= 1) {
            //因为string属于符合数据类型，所以应该是使用equals，假如我们使用==比较，肯定是比较它们的内存地址了
            if (!Objects.equals(list.get(0).get("password"), password)) {
                re = new Result(400, "登录失败，密码错误", list.get(0).get("password") + "|" + password);
                System.out.println("登录失败，密码错误");
                return re;
            }
        } else {
            re = new Result(400, "登录失败，用户名无效", password);
            System.out.println("登录失败，用户名无效");
            return re;
        }

        re = new Result(200, "登录成功", list.get(0));
        System.out.println("用户登录成功");
        return re;
    }
}
