package com.cdk.dao.impl;

import com.cdk.dao.ServerDao;
import com.cdk.entity.Platform;
import com.cdk.entity.Server;
import com.cdk.entity.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
                " t_gameplatform  as b on a.platformId = b.platformId and b.isDelete!=1  left JOIN \n" +
                " t_game as c on b.gameId = c.id and c.isDelete != 1  where a.isDelete != 1 ";

        if (server.getServer() != "") {
            sql += " and a.server LIKE '%" + server.getServer() + "%'";
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
        if (!Objects.equals(server.getState(), null) && !Objects.equals(server.getState(), -1)) {

            sql += " and a.state ='" + server.getState() + "' ";

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


    /***
     * 外部通过平台Tag回去服务器列表
     * @param platform
     * @return
     */
    public Map<String, Object> getServerList(String platform) {
        String sql = "select a.*,b.platform,c.gameName from t_gameserver as a left JOIN \n" +
                " t_gameplatform  as b on a.platformId = b.platformId and b.isDelete!=1  left JOIN \n" +
                " t_game as c on b.gameId = c.id and c.isDelete != 1  where a.isDelete != 1 and a.platformTag = '" + platform + "' ";
        sql += " order by a.id ";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        int total = list.size();
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        JsonMap.put("total", total);
        return JsonMap;
    }

    @Override
    public int addServer(Server server) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        String sql =
                "insert into t_gameserver (serverId,server,serverIp,serverPort,server_describe,platformId,sort,addUser,addDatetime,state,isDelete) " +
                        " values ('" + server.getServerId() + "','" + server.getServer() + "','" + server.getServerIp() + "','" +
                        server.getServerPort() + "','" + server.getServer_describe() + "','" + server.getPlatformId() + "','0','" +
                        server.getAddUser() + "','" + addDatetime + "','0','0')";
        System.out.println("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    @Override
    public int editServer(Server server) {
        String sql = "UPDATE t_gameserver as a SET a.serverId = '" + server.getServerId() + "', a.server='" + server.getServer() +
                "',a.server_describe = '" + server.getServer_describe() + "'," + "a.platformId='" + server.getPlatformId() + "'," + "a.serverIp='" +
                server.getServerIp() + "' ,a.serverPort = '" + server.getServerPort() + "',a.addUser = '" + server.getAddUser() + "' where a.id =" +
                server.getId() + "";
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
        String sql = "SELECT d.platformId as platformId, d.platform as platformName  from t_user as a \n" +
                "join t_user_roles as b on a.id = b.userId \n" + "join t_role as c on b.roleId = c.id \n" +
                "join t_gameplatform as d on c.id = d.roleId  \n" + "where a.isDelete != 1 and c.isDelete!=1 and d.isDelete!=1 and  a.id =" +
                user.getId();
        System.out.println("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);

        return list;
    }

    @Override
    public List<Map<String, Object>> getServerListForPlatform(Platform platform) {
        String sql = "SELECT a.serverId as serverId,a.server as serverName,a.serverIp from t_gameserver as a \n" +
                "join t_gameplatform as b on a.platformId = b.platformId \n" + "where a.isDelete != 1 and b.isDelete!=1  and  b.platformId =" +
                platform.getPlatformId();

        System.out.println("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    public int setDefaultServer(Server server) {
        String sql = "UPDATE t_server_config SET isDefault=0 ";
        int temp = jdbcTemplate.update(sql);
        sql = "UPDATE t_gameserver SET isDefault=1 where id ='" + server.getId() + "'";
        System.out.println("sql：" + sql);
        temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int ChangeState(Server server) {
        String sql = "UPDATE t_gameserver SET state='" + server.getState() + "' where id ='" + server.getId() + "'";
        System.out.println("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    @Override
    public List<Map<String, Object>> getServerTree() {
        String sql =
                "SELECT a.id as gameId ,b.platformId as platformId,c.serverId as serverId,a.gameName,b.platform,c.server FROM t_game as a join t_gameplatform as b on a.id = b.gameId \n" +
                        "join t_gameserver as c on b.platformId = c.platformId where a.isDelete!=1 and b.isDelete!=1 and c.isDelete !=1 ORDER BY a.id ,b.id,c.id";

        System.out.println("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    public int[] SynServerList(JSONArray jsonArray) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        String sql[] = new String[jsonArray.length()];
        String strSql = "";
        int[] temp = null;
        System.out.println("jsonArray.length():" + jsonArray.length());
        if (Objects.equals(jsonArray.length(), 0)) {
            //数据空，退出
            return null;
        }
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = null;
            try {
                jsonObject = jsonArray.getJSONObject(i);
                String sqlSelect =
                        "select * from t_gameserver where serverId = '" + jsonObject.get("sid") + "' and  platformId = '" + jsonObject.get("pid") +
                                "'";
                List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlSelect);
                if (list.size() > 0) {
                    //存在，更新
                    String sqlUpdate = "UPDATE t_gameserver as a SET a.server='" + jsonObject.get("sname") + "',a.server_describe = '" +
                            jsonObject.get("sname") + "'," + "a.platformId='" + jsonObject.get("pid") + "',a.platformTag='" +
                            jsonObject.get("pname") + "',a.openServiceTime='" + jsonObject.get("time") + "',a.addDatetime='" + addDatetime + "'," +
                            "a.serverIp='" + jsonObject.get("domain") + "',a.area='" + jsonObject.get("area") + "' ,a.serverPort = '" +
                            jsonObject.get("port") + "',a.addUser = 'cdk' where a.id =" + jsonObject.get("sid") + " and  a.platformId = '" +
                            jsonObject.get("pid") + "' ";
                    jdbcTemplate.update(sqlUpdate);
                } else {
                    //没有，新增
                    String sqlInsert =
                            "insert into t_gameserver (serverId,server,serverIp,serverPort,platformId,platformTag,server_describe,state,sort,addUser,addDatetime,isDelete,isDefault,area,openServiceTime) values ('" +
                                    jsonObject.get("sid") + "', '" + jsonObject.get("sname") + "','" + jsonObject.get("domain") + "','" +
                                    jsonObject.get("port") + "','" + jsonObject.get("pid") + "','" + jsonObject.get("pname") + "','" +
                                    jsonObject.get("sname") + "','0','0','cdk','" + addDatetime + "',0,'0','" + jsonObject.get("area") + "','" +
                                    jsonObject.get("time") + "' ) ; ";
                    jdbcTemplate.update(sqlInsert);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return temp;
    }
}
