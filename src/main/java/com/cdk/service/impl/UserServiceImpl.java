package com.cdk.service.impl;

import com.cdk.dao.impl.UserDaoImpl;
import com.cdk.entity.User;
import com.cdk.result.Result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class UserServiceImpl {
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    public UserDaoImpl userDaoImpl;

    public Result addUser(Map map) {
        String account = (map.get("account") != null ? map.get("account").toString() : "");
        String name = (map.get("name") != null ? map.get("name").toString() : "");
        String password = "123456";//默认初始密码
        String phone = (map.get("phone") != null ? map.get("phone").toString() : "");
        String email = (map.get("email") != null ? map.get("email").toString() : "");
        String type = (map.get("type") != null ? map.get("type").toString() : "");

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setName(name);
        user.setPhone(phone);
        user.setEmail(email);
        user.setType(type);

        int temp = userDaoImpl.addUser(user);
        Result re;
        if (temp > 0) {
            logger.debug("用户添加成功");
            re = new Result(200, "用户添加成功", null);
        } else {
            logger.debug("用户添加失败");
            re = new Result(400, "用户添加失败", null);
        }
        re = new Result(200, "", "");
        return re;
    }

    public Result editUser(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        String account = (map.get("account") != null ? map.get("account").toString() : "");
        String name = (map.get("name") != null ? map.get("name").toString() : "");
        String phone = (map.get("phone") != null ? map.get("phone").toString() : "");
        String email = (map.get("email") != null ? map.get("email").toString() : "");
        String type = (map.get("type") != null ? map.get("type").toString() : "");

        User user = new User();
        user.setId(Integer.parseInt(id));
        user.setAccount(account);
        user.setName(name);
        user.setPhone(phone);
        user.setEmail(email);
        user.setType(type);
        int temp = userDaoImpl.editUser(user);
        Result re;
        if (temp > 0) {
            logger.debug("用户信息修改成功");
            re = new Result(200, "用户信息更新成功", null);
        } else {
            logger.debug("用户信息修改失败");
            re = new Result(400, "用户信息更新失败", null);
        }
        return re;
    }

    public Result getAllUser() {
        Result re;
        Map<String, Object> JsonMap = userDaoImpl.getAllUser();
        if (Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(200, "用户列表为空", "");
        } else {
            re = new Result(200, "用户列表获取成功", JsonMap);
        }

        return re;
    }

    public Result getUserById(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        Result re;
        User user = new User();
        user.setId(Integer.parseInt(id));
        Map<String, Object> JsonMap = userDaoImpl.getUserById(user);
        if (Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(400, "用户获取失败", "");
        } else {
            re = new Result(200, "用户获取成功", JsonMap);
        }
        return re;
    }

    public Result getUser(Map map) {
        String account = (map.get("account") != null ? map.get("account").toString() : "");
        String name = (map.get("name") != null ? map.get("name").toString() : "");
        String phone = (map.get("phone") != null ? map.get("phone").toString() : "");
        String email = (map.get("email") != null ? map.get("email").toString() : "");
        String state = (map.get("state") != null ? map.get("state").toString() : "0");
        if (state == "") {
            state = "0";
        }
        User user = new User();
        user.setName(name);
        user.setAccount(account);
        user.setPhone(phone);
        user.setEmail(email);
        user.setState(Integer.parseInt(state));
        String StrPageNo = (map.get("pageNo") != null ? map.get("pageNo").toString() : "1");
        String StrPageSize = (map.get("pageSize") != null ? map.get("pageSize").toString() : "5");
        int pageNo = 1;
        int pageSize = 5;
        try {
            pageNo = Integer.parseInt(StrPageNo);
            pageSize = Integer.parseInt(StrPageSize);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        Result re;
        Map<String, Object> JsonMap = userDaoImpl.getUser(user, pageNo, pageSize);
        if (Objects.equals(JsonMap.get("total"), 0)) {
            re = new Result(200, "用户列表为空", "");
        } else {
            re = new Result(200, "用户列表获取成功", JsonMap);
        }

        return re;
    }

    public Result editPassword(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        String password = (map.get("password") != null ? map.get("password").toString() : "");
        User user = new User();
        user.setId(Integer.parseInt(id));
        user.setPassword(password);
        Result re;
        int temp = userDaoImpl.editPassword(user);
        if (temp > 0) {
            logger.debug("密码修改成功");
            re = new Result(200, "用户密码修改成功", null);
        } else {
            logger.debug("密码修改失败");
            re = new Result(400, "用户密码修改失败", null);
        }
        return re;
    }

    public Result changeStateToFrozen_User(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        User user = new User();
        user.setId(Integer.parseInt(id));
        Result re;
        int temp = userDaoImpl.changeStateToFrozen_User(user);
        if (temp > 0) {
            logger.debug("用户冻结成功");
            re = new Result(200, "用户冻结成功", null);
        } else {
            logger.debug("用户冻结失败");
            re = new Result(400, "用户冻结失败", null);
        }
        return re;
    }

    public Result changeStateToNormal_User(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        User user = new User();
        user.setId(Integer.parseInt(id));
        Result re;
        int temp = userDaoImpl.changeStateToNormal_User(user);
        if (temp > 0) {
            logger.debug("用户解冻成功");
            re = new Result(200, "用户解冻成功", null);
        } else {
            logger.debug("用户解冻失败");
            re = new Result(400, "用户解冻失败", null);
        }
        return re;
    }

    public Result deleteUser(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        User user = new User();
        user.setId(Integer.parseInt(id));
        Result re;
        int temp = userDaoImpl.deleteUser(user);
        if (temp > 0) {
            logger.debug("用户删除成功");
            re = new Result(200, "用户删除成功", null);
        } else {
            logger.debug("用户删除失败");
            re = new Result(400, "用户删除失败", null);
        }
        return re;
    }

    public Result deleteAllUser(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        if (Objects.equals(id, "")) {
            logger.debug("无任何批量删除操作");
            return new Result(400, "无任何批量删除操作", null);
        }
        String[] objectArry = id.split(",");
        Result re;
        int[] temp = userDaoImpl.deleteAllUser(objectArry);
        if (temp.length != 0) {
            logger.debug("用户批量删除成功");
            re = new Result(200, "用户批量删除成功", null);
        } else if (objectArry.length == 0) {
            logger.debug("无任何删除操作");
            re = new Result(200, "无任何删除操作", null);
        } else {
            logger.debug("用户批量删除失败");
            re = new Result(400, "用户批量删除失败", null);
        }
        return re;
    }

    public Result insertUserRoles(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        String InsertUserRoles = map.get("InsertUserRoles").toString();
        if (Objects.equals(InsertUserRoles, "")) {
            logger.debug("无任何添加操作");
            return new Result(200, "无任何添加操作", null);
        }

        String[] ObjectArry = InsertUserRoles.split(",");
        Result re;
        int temp = 99;
        temp = userDaoImpl.insertUserRoles(id, ObjectArry);
        if (temp > 0 && temp != 99) {
            logger.debug("用户角色添加成功");
            re = new Result(200, "用户角色添加成功", null);
        } else if (temp == 99) {
            logger.debug("无任何添加操作");
            re = new Result(200, "无任何添加操作", null);
        } else {
            logger.debug("用户角色添加失败");
            re = new Result(400, "用户角色添加失败", null);
        }
        return re;
    }

    public Result addRole(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        String roleId = (map.get("roleId") != null ? map.get("roleId").toString() : "");
        if (Objects.equals(roleId, "")) {
            logger.debug("无任何添加操作");
            return new Result(200, "无任何添加操作", null);
        }
        Result re;
        int temp = 0;
        temp = userDaoImpl.addRole(id, roleId);
        if (temp > 0) {
            logger.debug("用户角色添加成功");
            re = new Result(200, "用户角色添加成功", null);
        } else if (temp == 99) {
            logger.debug("用户角色已存在");
            re = new Result(200, "用户角色已存在", null);
        } else {
            logger.debug("用户角色添加失败");
            re = new Result(400, "用户角色添加失败", null);
        }
        return re;
    }


    public Result deleteUserRoles(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        String deleteUserRoles = map.get("deleteUserRoles").toString();
        if (Objects.equals(deleteUserRoles, "")) {
            logger.debug("无任何删除操作");
            return new Result(200, "无任何删除操作", null);
        }

        String[] ObjectArry = deleteUserRoles.split(",");
        Result re;
        int temp = 99;
        temp = userDaoImpl.deleteUserRoles(id, ObjectArry);
        if (temp > 0 && temp != 99) {
            logger.debug("用户角色删除成功");
            re = new Result(200, "用户角色删除成功", null);
        } else if (temp == 99) {
            logger.debug("无任何删除操作");
            re = new Result(200, "无任何删除操作", null);
        } else {
            logger.debug("用户角色删除失败");
            re = new Result(400, "用户角色删除失败", null);
        }
        return re;
    }
}
