package com.cdk.service.impl;

import com.cdk.dao.impl.LoginDaoImpl;
import com.cdk.entity.User;
import com.cdk.entity.VueLoginInfoVo;
import com.cdk.result.Result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl {
    private static Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
    @Autowired
    public LoginDaoImpl loginDaoImpl;

    public Result login(VueLoginInfoVo loginInfoVo) {
        String username = loginInfoVo.getUsername();
        String password = loginInfoVo.getPassword();
        Result re;
        List<Map<String, Object>> list = loginDaoImpl.login(loginInfoVo);
        if (list != null && list.size() >= 1) {
            if (!Objects.equals(list.get(0).get("password"), password)) {
                re = new Result(400, "登录失败，密码错误", list.get(0).get("password") + "|" + password);
                logger.debug("登录失败，密码错误");
                return re;
            }
        } else {
            re = new Result(400, "登录失败，用户名无效", password);
            logger.debug("登录失败，用户名无效");
            return re;
        }
        re = new Result(200, "登录成功", list.get(0));
        logger.debug("用户登录成功");
        return re;
    }

    public Result getTouristId() {
        Result re;
        List<Map<String, Object>> list = loginDaoImpl.getTouristId();
        if (Objects.equals(list.get(0).get("configValue"), null)) {
            re = new Result(400, "游客Id获取失败", "");
        } else {
            re = new Result(200, "游客Id获取成功", list.get(0).get("configValue").toString());
        }
        return re;
    }

    public Result getTouristName() {
        Result re;
        List<Map<String, Object>> list = loginDaoImpl.getTouristName();
        if (Objects.equals(list.get(0).get("configValue"), null)) {
            re = new Result(400, "游客name获取失败", "");
        } else {
            re = new Result(200, "游客name获取成功", list.get(0).get("configValue").toString());
        }
        return re;
    }

    public Result setTouristId(Map map) {
        String userId = (map.get("userId") != null ? map.get("userId").toString() : "0");
        Result re;
        int temp = loginDaoImpl.setTouristId(userId);
        if (temp > 0) {
            re = new Result(200, "游客Id设置成功", "");
        } else {
            re = new Result(400, "游客Id设置失败", "");
        }
        return re;
    }

    public Result setTouristName(Map map) {
        String name = (map.get("name") != null ? map.get("name").toString() : "");
        Result re;
        int temp = loginDaoImpl.setTouristName(name);
        if (temp > 0) {
            re = new Result(200, "游客name设置成功", "");
        } else {
            re = new Result(400, "游客name设置失败", "");
        }
        return re;
    }

    public Result getThisUserInfo(Map map) {
        String name = (map.get("name") != null ? map.get("name").toString() : "");
        Result re;
        if (Objects.equals(name, "")) {
            return new Result(400, "用户名无效", "");
        }
        User user = new User();
        user.setName(name);
        List<Map<String, Object>> list = loginDaoImpl.getThisUserInfo(user);
        if (Objects.equals(list.get(0), null)) {
            re = new Result(400, "当前用户信息获取失败", "");
        } else {
            re = new Result(200, "当前用户信息获取成功", list.get(0));
        }
        return re;
    }
}
