package com.cdk.dao.impl;

import com.cdk.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

@Repository
public class UserDaoImpl {
    private static Logger logger = Logger.getLogger(String.valueOf(UserDaoImpl.class));
    public static final String Divider = "############################";
    public static final String Split = "----------------";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int addUser(User user) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        String sql = "insert into t_user (name,account,password,nick,phone,email,state,addDatetime,lastDatetime,isDelete) " + " values ('" +
                user.getName() + "','" + user.getAccount() + "','" + user.getPassword() + "','" + user.getName() + "','" + user.getPhone() + "'" +
                ",'" + user.getEmail() + "','0','" + addDatetime + "','" + addDatetime + "','0')";
        logger.info("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public Map<String, Object> getAllUser() {
        String sql = "select * ,(select GROUP_CONCAT(distinct b.roleId) as info from t_user_roles as b JOIN t_role as c on b.roleId = c.id\n" +
                "where a.id= b.userId and c.isDelete!=1 and b.isDelete!=1 )\n" + "as roles";
        sql += " from t_user as a where a.isDelete != 1  ";
        logger.info("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        Map<String, Object> JsonMap = new HashMap();
        if (list.size() != 0) {
            JsonMap.put("list", list);
        } else {
            JsonMap.put("list", 0);
        }
        return JsonMap;
    }

    public Map<String, Object> getUserById(User user) {
        String sql = "select *  from t_user  where id='" + user.getId() + "' and isDelete != 1  ";
        logger.info("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        Map<String, Object> JsonMap = new HashMap();
        if (list.size() != 0) {
            JsonMap.put("list", list);
        } else {
            JsonMap.put("list", 0);
        }
        return JsonMap;
    }

    public Map<String, Object> getUser(User user, int pageNo, int pageSize) {
        //distinct去重，GROUP_CONCAT合并，注意GROUP_CONCAT的默认长度是1024，所以尽量不要拼接name
        String sql = "select * ,(select GROUP_CONCAT(distinct b.roleId) as info from t_user_roles as b JOIN t_role as c on b.roleId = c.id\n" +
                "where a.id= b.userId and c.isDelete!=1 and b.isDelete!=1 )\n" + "as roles";
        sql += " from t_user as a where a.isDelete != 1  ";
        if (user.getName() != "") {
            sql += " and a.name LIKE '%" + user.getName() + "%'";
        }
        if (user.getAccount() != "") {
            sql += " and a.account LIKE '%" + user.getAccount() + "%'";
        }
        if (!Objects.equals(user.getState(), null) && !Objects.equals(user.getState(), 0)) {
            if (Objects.equals(user.getState(), 1)) {
                sql += " and state = 1 ";
            } else if (Objects.equals(user.getState(), 2)) {
                sql += " and state != 1 ";
            }
        }
        List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql);
        int total = list.size();
        sql += " limit " + (pageNo - 1) * pageSize + ", " + pageSize;
        logger.info("sql：" + sql);
        list = jdbcTemplate.queryForList(sql);
        Map<String, Object> JsonMap = new HashMap();
        if (list.size() != 0) {
            JsonMap.put("list", list);
        } else {
            JsonMap.put("list", 0);
        }
        JsonMap.put("total", total);

        return JsonMap;
    }

    public int editUser(User user) {
        String sql =
                "UPDATE t_user SET account='" + user.getAccount() + "',name = '" + user.getName() + "',phone = '" + user.getPhone() + "',email = '" +
                        user.getEmail() + "' where id ='" + user.getId() + "'";
        logger.info("sql：" + sql);
        int temp = jdbcTemplate.update(sql);

        return temp;
    }

    public int checkSameUserName(User user) {
        String sql = "select * from t_user where name='" + user.getName() + "' and isDelete!=1";
        logger.info("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int editPassword(User user) {
        String sql = "UPDATE t_user SET password='" + user.getPassword() + "' where id ='" + user.getId() + "'";
        logger.info("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int changeStateToFrozen_User(User user) {

        String sql = "UPDATE t_user SET state='1' where id ='" + user.getId() + "'";
        logger.info("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int changeStateToNormal_User(User user) {
        String sql = "UPDATE t_user SET state='0' where id ='" + user.getId() + "'";
        logger.info("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int deleteUser(User user) {
        String sql = "UPDATE t_user SET isDelete='1' where id ='" + user.getId() + "'";
        logger.info("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int[] deleteAllUser(String[] idList) {
        String strSql = "";
        String sql[] = new String[idList.length];
        int[] temp = new int[idList.length];
        for (int i = 0; i < idList.length; i++) {
            sql[i] = "UPDATE  t_user  set isDelete='1' where id = '" + idList[i] + "';";
            strSql += sql;
        }
        logger.info("sql：" + strSql);
        temp = jdbcTemplate.batchUpdate(sql);
        return temp;
    }

    public int insertUserRoles(String id, String[] roleList) {
        int temp = 0;
        String sql = "";
        String strSql = "";
        for (int i = 0; i < roleList.length; i++) {
            sql = "insert into t_user_roles  (userId,roleId,isDelete) " + " values ( " + id + " , " + roleList[i] + " , '0')";
            strSql += sql;
            temp = jdbcTemplate.update(sql);
        }
        return temp;
    }

    public int addRole(String id, String roleId) {
        int temp = 0;
        String sql = "";
        sql = "select * from  t_user_roles  where userId = " + id + " and roleId =  " + roleId + " and isDelete!=1";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        int total = list.size();
        if (!Objects.equals(total, 0)) {
            temp = 99;
            return temp;
        } else {
            sql = "insert into t_user_roles  (userId,roleId,isDelete) " + " values ( " + id + " , " + roleId + " , '0')";
            temp = jdbcTemplate.update(sql);
            return temp;
        }
    }

    public int deleteUserRoles(String id, String[] roleList) {
        int temp = 0;
        String sql = "";
        for (int i = 0; i < roleList.length; i++) {
            sql = "delete from  t_user_roles  where userId ='" + id + "' and roleId = '" + roleList[i] + "'";
            temp = jdbcTemplate.update(sql);
        }
        return temp;
    }
}
