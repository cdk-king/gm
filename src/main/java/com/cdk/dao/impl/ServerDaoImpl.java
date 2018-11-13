package com.cdk.dao.impl;

import com.cdk.dao.ServerDao;
import com.cdk.entity.Platform;
import com.cdk.entity.Server;
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

@Repository
public class ServerDaoImpl implements ServerDao {
    public static final String Divider = "############################";
    public static final String Split = "----------------";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Map<String, Object> getAllServer(Server server, String platformName, String gameName, String isPage, int pageNo, int pageSize) {
        String sql = "select a.*,b.platform,c.gameName from t_gameserver as a left JOIN \n" +
                " t_gameplatform  as b on a.platformId = b.id and b.isDelete!=1  left JOIN \n" +
                " t_game as c on b.gameId = c.id and c.isDelete != 1  where a.isDelete != 1 ";

        if (server.getServer() != "") {
            sql += " and a.server LIKE '%" + server + "%'";
        }
        if (server.getServer_describe() != "") {
            sql += " and a.server_describe LIKE '%" + server.getServer_describe() + "%'";
        }
        if (server.getServerIp() != "") {
            sql += " and a.serverIp LIKE '%" + server.getServerIp() + "%'";
        }
        if (platformName != "") {
            sql += " and b.platform LIKE '%" + platformName + "%'";
        }
        if (gameName != "") {
            sql += " and c.gameName LIKE '%" + gameName + "%'";
        }
        //0：全部，1：冻结，2：未冻结
        if (!Objects.equals(server.getState(), null) && !Objects.equals(server.getState(), 0)) {
            if (Objects.equals(server.getState(), 1)) {
                sql += " and a.state = 1 ";
            } else if (Objects.equals(server.getState(), 2)) {
                sql += " and a.state != 1 ";
            }
        }
        sql += " order by a.id ";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        int total = list.size();
        if (!Objects.equals(isPage, "")) {
            sql += " limit " + (pageNo - 1) * pageSize + ", " + pageSize;
        }

        System.out.println("sql：" + sql);
        list = jdbcTemplate.queryForList(sql);
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        JsonMap.put("total", total);

        return JsonMap;
    }

    @Override
    public int addServer(Server server) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        String sql = "insert into t_gameserver (server,serverIp,serverPort,server_describe,platformId,sort,addUser,addDatetime,state,isDelete) " +
                " values ('" + server.getServer() + "','" + server.getServerIp() + "','" + server.getServerPort() + "','" +
                server.getServer_describe() + "','" + server.getPlatformId() + "','0','" + server.getAddUser() + "','" + addDatetime + "','0','0')";
        System.out.println("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    @Override
    public int editServer(Server server) {
        String sql = "UPDATE t_gameserver as a SET a.server='" + server.getServer() + "',a.server_describe = '" + server.getServer_describe() + "'," +
                "a.platformId='" + server.getPlatformId() + "'," + "a.serverIp='" + server.getServerIp() + "' ,a.serverPort = '" +
                server.getServerPort() + "',a.addUser = '" + server.getAddUser() + "' where a.id =" + server.getId() + "";
        System.out.println("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    @Override
    public List<Map<String, Object>> getAllPlatformList() {
        String sql = "select * from t_gameplatform where isDelete != 1 ";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        System.out.println("sql：" + sql);
        return list;
    }

    @Override
    public int changeStateToNormal_Server(Server server) {
        String sql = "UPDATE t_gameserver SET state='0' where id ='" + server.getId() + "'";
        System.out.println("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    @Override
    public int changeStateToFrozen_Server(Server server) {
        String sql = "UPDATE t_gameserver SET state='1' where id ='" + server.getId() + "'";
        System.out.println("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    @Override
    public int deleteServer(Server server) {
        String sql = "UPDATE t_gameserver SET isDelete='1' where id ='" + server.getId() + "'";
        System.out.println("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    @Override
    public int[] deleteAllServer(String[] serverList) {
        String sql[] = new String[serverList.length];
        String strSql = "";
        int[] temp = new int[serverList.length];
        //jdbcTemplate.update(sql)只能运行一条语句，不可使用拼接
        //jdbcTemplate.batchUpdate可执行多条语句，同时还能规避执行过程中中断
        //这期间任一条SQL语句出现问题都会回滚[**]会所有语句没有执行前的最初状态
        for (int i = 0; i < serverList.length; i++) {
            sql[i] = "UPDATE  t_gameserver  set isDelete='1' where id = '" + serverList[i] + "'; ";
            strSql += sql;
        }
        System.out.println("sql：" + strSql);
        temp = jdbcTemplate.batchUpdate(sql);
        return temp;
    }

    @Override
    public List<Map<String, Object>> getPlatformListForUser(User user) {
        String sql = "SELECT d.id as platformId, d.platform as platformName  from t_user as a \n" + "join t_user_roles as b on a.id = b.userId \n" +
                "join t_role as c on b.roleId = c.id \n" + "join t_gameplatform as d on c.id = d.roleId  \n" +
                "where a.isDelete != 1 and c.isDelete!=1 and d.isDelete!=1 and  a.id =" + user.getId();
        System.out.println("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);

        return list;
    }

    @Override
    public List<Map<String, Object>> getServerListForPlatform(Platform platform) {
        String sql = "SELECT a.id as serverId,a.server as serverName,a.serverIp from t_gameserver as a \n" +
                "join t_gameplatform as b on a.platformId = b.id \n" + "where a.isDelete != 1 and b.isDelete!=1  and  b.id =" + platform.getId();

        System.out.println("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    @Override
    public List<Map<String, Object>> getServerTree() {
        String sql =
                "SELECT a.id as gameId ,b.id as platformId,c.id as serverId,a.gameName,b.platform,c.server FROM t_game as a join t_gameplatform as b on a.id = b.gameId \n" +
                        "join t_gameserver as c on b.id = c.platformId where a.isDelete!=1 and b.isDelete!=1 and c.isDelete !=1 ORDER BY a.id ,b.id,c.id";

        System.out.println("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }
}
