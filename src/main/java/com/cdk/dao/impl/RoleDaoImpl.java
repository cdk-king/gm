package com.cdk.dao.impl;

import com.cdk.dao.RoleDao;
import com.cdk.entity.Role;

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
public class RoleDaoImpl implements RoleDao {
    private static Logger logger = Logger.getLogger(String.valueOf(RoleDaoImpl.class));
    public static final String Divider = "############################";
    public static final String Split = "----------------";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int addRole(Role role) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳

        String sql = "insert into t_role  (role,role_describe,addUser,addDatetime,state,isDelete) " + " values ('" + role.getRole() + "','" +
                role.getRole_describe() + "','" + role.getAddUser() + "','" + addDatetime + "','0','0')";
        logger.info("sql：" + sql);
        int temp = jdbcTemplate.update(sql);

        return temp;
    }

    public List<Map<String, Object>> getRoleById(String id) {
        String sql = "select * from t_role where id='" + id + "' and isDelete != 1  ";

        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);

        return list;
    }

    @Override
    public Map<String, Object> getRole(Role role, String isPage, int pageNo, int pageSize) {
        // GROUP_CONCAT 默认长度1024 要炸
        String sql = "select * ,(select GROUP_CONCAT(distinct b.rightId) as info from t_role_rights as b JOIN t_right as c on b.rightId = c.id \n" +
                "where a.id= b.roleId and c.isDelete!=1 and b.isDelete!=1 )\n" + "as rights";
        sql += " from t_role as a where a.isDelete != 1  ";
        if (role.getRole() != "") {
            sql += " and a.role LIKE '%" + role.getRole() + "%'";
        }
        if (role.getRole_describe() != "") {
            sql += " and a.role_describe LIKE '%" + role.getRole_describe() + "%'";
        }
        if (!Objects.equals(role.getState(), null) && !Objects.equals(role.getState(), 0)) {
            if (Objects.equals(role.getState(), 1)) {
                sql += " and a.state = 1 ";
            } else if (Objects.equals(role.getState(), 1)) {
                sql += " and a.state != 1 ";
            }
        }
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        int total = list.size();
        if (!Objects.equals(isPage, "")) {
            sql += " limit " + (pageNo - 1) * pageSize + ", " + pageSize;
        }

        logger.info("sql：" + sql);
        list = jdbcTemplate.queryForList(sql);
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        JsonMap.put("total", total);
        return JsonMap;
    }

    @Override
    public int editRole(Role role) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳

        String sql = "UPDATE t_role as a SET a.role='" + role.getRole() + "',a.role_describe = '" + role.getRole_describe() + "',a.addUser = '" +
                role.getAddUser() + "' where a.id =" + role.getId() + "";
        logger.info("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    @Override
    public int changeStateToFrozen_Role(Role role) {

        String sql = "UPDATE t_role SET state='1' where id ='" + role.getId() + "'";
        logger.info("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    @Override
    public int changeStateToNormal_Role(Role role) {
        String sql = "UPDATE t_role SET state='0' where id ='" + role.getId() + "'";
        logger.info("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    @Override
    public int deleteRole(Role role) {
        String sql = "UPDATE t_role SET isDelete='1' where id ='" + role.getId() + "'";
        logger.info("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    @Override
    public int deleteRoleRights(int id, String[] rightList) {
        int temp = 0;
        String sql = "";
        String strSql = "";
        for (int i = 0; i < rightList.length; i++) {
            //UPDATE user_roles  set  isDelete='1' where
            sql = "delete from  t_role_rights  where roleId ='" + id + "' and rightId = '" + rightList[i] + "'";
            strSql += sql;
            //只能运行一条语句，不可使用拼接
            temp = jdbcTemplate.update(sql);
        }
        logger.info("sql：" + strSql);
        return temp;
    }

    @Override
    public int InsertRoleRights(int id, String[] rightList) {
        int temp = 0;
        String sql = "";
        String strSql = "";
        for (int i = 0; i < rightList.length; i++) {
            sql = "insert into t_role_rights  (roleId,rightId,isDelete) " + " values ( " + id + " , " + rightList[i] + " , '0')";
            strSql += sql;
            //只能运行一条语句，不可使用拼接
            temp = jdbcTemplate.update(sql);
        }
        logger.info("sql：" + strSql);
        return temp;
    }

    @Override
    public int[] deleteAllRole(String[] roleList) {
        int[] temp = new int[roleList.length];
        String sql[] = new String[roleList.length];
        String strSql = "";
        for (int i = 0; i < roleList.length; i++) {
            //UPDATE user_roles  set  isDelete='1' where
            sql[i] = "UPDATE  t_role  set isDelete='1' where id = '" + roleList[i] + "';";
            strSql += sql;
            //jdbcTemplate.update(sql)只能运行一条语句，不可使用拼接
            //jdbcTemplate.batchUpdate可执行多条语句，同时还能规避执行过程中中断
            //这期间任一条SQL语句出现问题都会回滚[**]会所有语句没有执行前的最初状态
        }
        temp = jdbcTemplate.batchUpdate(sql);
        logger.info("sql：" + strSql);
        return temp;
    }

    public int addRight(String id, String rightId) {
        int temp = 0;
        String sql = "";
        sql = "select * from  t_role_rights  where roleId = " + id + " and rightId =  " + rightId + " and isDelete!=1";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        int total = list.size();
        if (!Objects.equals(total, 0)) {
            temp = 99;
            return temp;
        } else {
            sql = "insert into t_role_rights  (roleId,rightId,isDelete) " + " values ( " + id + " , " + rightId + " , '0')";
            //只能运行一条语句，不可使用拼接
            temp = jdbcTemplate.update(sql);
            return temp;
        }
    }
}
