package com.cdk.service.impl;

import com.cdk.dao.impl.RoleDaoImpl;
import com.cdk.entity.Role;
import com.cdk.result.Result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class RoleServiceImpl {
    private static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
    @Autowired
    public RoleDaoImpl roleDaoImpl;

    public Result addRole(Map map) {
        String roleName = (map.get("role") != null ? map.get("role").toString() : "");
        String role_describe = (map.get("role_describe") != null ? map.get("role_describe").toString() : "");
        String addUser = (map.get("addUser") != null ? map.get("addUser").toString() : "");
        Role role = new Role();
        role.setRole(roleName);
        role.setRole_describe(role_describe);
        role.setAddUser(addUser);

        Result re;
        int temp = roleDaoImpl.addRole(role);
        if (temp > 0) {
            logger.debug("角色添加成功");
            re = new Result(200, "角色添加成功", null);
        } else {
            logger.debug("角色添加失败");
            re = new Result(400, "角色添加失败", null);
        }
        return re;
    }

    public Result getRoleById(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        Result re;
        List<Map<String, Object>> list = roleDaoImpl.getRoleById(id);
        if (list.size() > 0) {
            logger.debug("角色信息获取成功");
            re = new Result(200, "角色信息获取成功", list);
        } else {
            logger.debug("角色信息获取失败");
            re = new Result(400, "角色信息获取失败", list);
        }
        return re;
    }

    public Result getRole(Map map) {
        String roleName = (map.get("role") != null ? map.get("role").toString() : "");
        String role_describe = (map.get("role_describe") != null ? map.get("role_describe").toString() : "");
        String addUser = (map.get("addUser") != null ? map.get("addUser").toString() : "");
        String state = (map.get("state") != null ? map.get("state").toString() : "");
        String isPage = (map.get("isPage") != null ? map.get("isPage").toString() : "");
        if (state == "") {
            state = "0";
        }
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

        Role role = new Role();
        role.setRole(roleName);
        role.setRole_describe(role_describe);
        role.setAddUser(addUser);
        role.setState(Integer.parseInt(state));

        Result re;
        Map<String, Object> JsonMap = roleDaoImpl.getRole(role, isPage, pageNo, pageSize);
        if (Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(400, "角色列表为空", "");
        } else {
            re = new Result(200, "角色列表获取成功", JsonMap);
        }
        return re;
    }

    public Result editRole(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        String roleName = (map.get("role") != null ? map.get("role").toString() : "");
        String role_describe = (map.get("role_describe") != null ? map.get("role_describe").toString() : "");
        String addUser = (map.get("addUser") != null ? map.get("addUser").toString() : "");

        Result re;
        Role role = new Role();
        role.setId(Integer.parseInt(id));
        role.setRole(roleName);
        role.setRole_describe(role_describe);
        role.setAddUser(addUser);

        int temp = roleDaoImpl.editRole(role);
        if (temp > 0) {
            logger.debug("角色信息修改成功");
            re = new Result(200, "角色信息更新成功", null);
        } else {
            logger.debug("角色信息修改失败");
            re = new Result(400, "角色信息更新失败", null);
        }

        return re;
    }

    public Result changeStateToFrozen_Role(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        Role user = new Role();
        user.setId(Integer.parseInt(id));
        Result re;
        int temp = roleDaoImpl.changeStateToFrozen_Role(user);
        if (temp > 0) {
            logger.debug("角色冻结成功");
            re = new Result(200, "角色冻结成功", null);
        } else {
            logger.debug("角色冻结失败");
            re = new Result(400, "角色冻结失败", null);
        }
        return re;
    }

    public Result changeStateToNormal_Role(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        Role role = new Role();
        role.setId(Integer.parseInt(id));
        Result re;
        int temp = roleDaoImpl.changeStateToNormal_Role(role);
        if (temp > 0) {
            logger.debug("角色解冻成功");
            re = new Result(200, "角色解冻成功", null);
        } else {
            logger.debug("角色解冻失败");
            re = new Result(400, "角色解冻失败", null);
        }
        return re;
    }

    public Result deleteRole(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        Role role = new Role();
        role.setId(Integer.parseInt(id));
        Result re;
        int temp = roleDaoImpl.deleteRole(role);
        if (temp > 0) {
            logger.debug("角色删除成功");
            re = new Result(200, "角色删除成功", null);
        } else {
            logger.debug("角色删除失败");
            re = new Result(400, "角色删除失败", null);

        }
        return re;
    }

    public Result deleteRoleRights(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        String deleteRoleRights = map.get("deleteRoleRights").toString();
        if (Objects.equals(deleteRoleRights, "")) {
            logger.debug("无任何添加操作");
            return new Result(200, "无任何添加操作", null);
        }

        String[] ObjectArry = deleteRoleRights.split(",");

        Role role = new Role();
        role.setId(Integer.parseInt(id));

        Result re;
        int temp = 99;
        temp = roleDaoImpl.deleteRoleRights(Integer.parseInt(id), ObjectArry);

        if (temp > 0 && temp != 99) {
            logger.debug("角色权限删除成功");
            re = new Result(200, "角色权限删除成功", null);
        } else if (temp == 99) {
            logger.debug("无任何删除操作");
            re = new Result(400, "无任何删除操作", null);
        } else {
            logger.debug("角色权限删除失败");
            re = new Result(400, "角色权限删除失败", null);
        }
        return re;
    }

    public Result addRight(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        String rightId = (map.get("rightId") != null ? map.get("rightId").toString() : "");
        if (Objects.equals(rightId, "")) {
            logger.debug("无任何添加操作");
            return new Result(200, "无任何添加操作", null);
        }
        Result re;
        int temp = 0;
        temp = roleDaoImpl.addRight(id, rightId);
        if (temp > 0) {
            logger.debug("权限添加成功");
            re = new Result(200, "权限添加成功", null);
        } else if (temp == 99) {
            logger.debug("权限已存在");
            re = new Result(200, "权限已存在", null);
        } else {
            logger.debug("权限添加失败");
            re = new Result(400, "权限添加失败", null);
        }
        return re;
    }


    public Result InsertRoleRights(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        String InsertRoleRights = map.get("InsertRoleRights").toString();

        if (Objects.equals(InsertRoleRights, "")) {
            logger.debug("无任何添加操作");
            return new Result(200, "无任何添加操作", null);
        }

        String[] ObjectArry = InsertRoleRights.split(",");

        Role role = new Role();
        role.setId(Integer.parseInt(id));

        Result re;
        int temp = 99;
        temp = roleDaoImpl.InsertRoleRights(Integer.parseInt(id), ObjectArry);

        if (temp > 0 && temp != 99) {
            logger.debug("角色权限添加成功");
            re = new Result(200, "角色权限添加成功", null);
        } else if (temp == 99) {
            logger.debug("无任何添加操作");
            re = new Result(200, "无任何添加操作", null);
        } else {
            logger.debug("角色权限添加失败");
            re = new Result(400, "角色权限添加失败", null);
        }
        return re;
    }

    public Result deleteAllRole(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        if (Objects.equals(id, "")) {
            logger.debug("无任何批量删除操作");
            return new Result(200, "无任何批量删除操作", null);
        }
        String[] ObjectArry = id.split(",");
        Result re;
        int[] temp = new int[ObjectArry.length];
        temp = roleDaoImpl.deleteAllRole(ObjectArry);
        if (temp.length != 0) {
            logger.debug("角色批量删除成功");
            re = new Result(200, "角色批量删除成功", null);
        } else if (ObjectArry.length == 0) {
            logger.debug("无任何删除操作");
            re = new Result(400, "无任何删除操作", null);
        } else {
            logger.debug("角色批量删除失败");
            re = new Result(400, "角色批量删除失败", null);
        }
        return re;
    }
}
