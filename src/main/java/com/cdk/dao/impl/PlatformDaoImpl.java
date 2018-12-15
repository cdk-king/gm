package com.cdk.dao.impl;

import com.cdk.dao.PlatformDao;
import com.cdk.entity.Platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class PlatformDaoImpl implements PlatformDao {
    public static final String Divider = "############################";
    public static final String Split = "----------------";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Map<String, Object> getAllPlatform(Platform platform, String gameName, String isPage, int pageNo, int pageSize) {

        String sql = "select a.*,b.gameName,c.role from t_gameplatform as a left JOIN " +
                "  t_game  as b on a.gameId = b.id and b.isDelete!=1  left JOIN" +
                "  t_role as c on a.roleId = c.id and c.isDelete != 1  where a.isDelete != 1 ";

        if (platform.getPlatform() != "") {
            sql += " and a.platform LIKE '%" + platform.getPlatform() + "%'";
        }
        if (platform.getPlatform_describe() != "") {
            sql += " and a.platform_describe LIKE '%" + platform.getPlatform_describe() + "%'";
        }
        if (platform.getPlatformTag() != "") {
            sql += " and a.platformTag LIKE '%" + platform.getPlatformTag() + "%'";
        }
        if (gameName != "") {
            sql += " and b.gameName LIKE '%" + gameName + "%'";
        }
        //0：全部，1：冻结，2：未冻结
        if (!Objects.equals(platform.getState(), null) && !Objects.equals(platform.getState(), 0)) {
            if (Objects.equals(platform.getState(), 1)) {
                sql += " and a.state = 1 ";
            } else if (Objects.equals(platform.getState(), 2)) {
                sql += " and a.state != 1 ";
            }
        }

        sql += " order by a.id ";

        System.out.println("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        int total = list.size();
        if (!Objects.equals(isPage, "")) {
            sql += " limit " + (pageNo - 1) * pageSize + ", " + pageSize;
        }

        list = jdbcTemplate.queryForList(sql);
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        JsonMap.put("total", total);
        return JsonMap;
    }

    @Override
    public List<Map<String, Object>> getAllGameList() {
        String sql = "select * from t_game where isDelete != 1 ";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    @Override
    public List<Map<String, Object>> getAllRoleList() {
        String sql = "select * from t_role where isDelete != 1 ";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    @Override
    public int addPlatform(Platform platform) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        String sql =
                "insert into t_gameplatform (platformId,platform,platformTag,platform_describe,gameId,roleId,sort,addUser,addDatetime,state,isDelete) " +
                        " values ('" + platform.getPlatformId() + "','" + platform.getPlatform() + "','" + platform.getPlatformTag() + "','" +
                        platform.getPlatform_describe() + "','" + platform.getGameId() + "','" + platform.getRoleId() + "','0','" +
                        platform.getAddUser() + "','" + addDatetime + "','0','0')";
        System.out.println("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    @Override
    public int editPlatform(Platform platform) {
        String sql = "UPDATE t_gameplatform as a SET a.platformId = '" + platform.getPlatformId() + "' , a.platform='" + platform.getPlatform() +
                "',a.platform_describe = '" + platform.getPlatform_describe() + "'," + "a.gameId='" + platform.getGameId() + "',a.roleId='" +
                platform.getRoleId() + "'," + "a.platformTag='" + platform.getPlatformTag() + "' ,a.addUser = '" + platform.getAddUser() +
                "' where a.id =" + platform.getId() + "";
        System.out.println("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    @Override
    public int deletePlatform(Platform platform) {
        String sql = "UPDATE t_gameplatform SET isDelete='1' where id ='" + platform.getId() + "'";
        System.out.println("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    @Override
    public int changeStateToNormal_Platform(Platform platform) {
        String sql = "UPDATE t_gameplatform SET state='0' where id ='" + platform.getId() + "'";
        System.out.println("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    @Override
    public int changeStateToFrozen_Platform(Platform platform) {
        String sql = "UPDATE t_gameplatform SET state='1' where id ='" + platform.getId() + "'";
        System.out.println("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }


    @Override
    public int[] deleteAllPlatform(String[] platformList) {
        String sql[] = new String[platformList.length];
        String strSql = "";
        int[] temp = new int[platformList.length];
        //jdbcTemplate.update(sql)只能运行一条语句，不可使用拼接
        //jdbcTemplate.batchUpdate可执行多条语句，同时还能规避执行过程中中断
        //这期间任一条SQL语句出现问题都会回滚[**]会所有语句没有执行前的最初状态
        for (int i = 0; i < platformList.length; i++) {
            sql[i] = "UPDATE  t_gameplatform  set isDelete='1' where id = '" + platformList[i] + "'; ";
            strSql += sql;
        }
        System.out.println("sql：" + strSql);
        temp = jdbcTemplate.batchUpdate(sql);
        return temp;
    }
}
