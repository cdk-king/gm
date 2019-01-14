package com.cdk.dao.impl;

import com.cdk.entity.Platform;
import com.cdk.entity.Server;
import com.cdk.entity.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class ServerDaoImpl {
    private static Logger logger = LoggerFactory.getLogger(ServerDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> getAllServer(Server server, String gameId, String platformId, String channelId, String gameName, String isPage,
            int pageNo, int pageSize) {
        String sql = "select a.*,b.platform,c.gameName from t_gameserver as a left JOIN \n" +
                " t_gameplatform  as b on a.platformId = b.platformId and b.isDelete!=1  left JOIN \n" +
                " t_game as c on b.gameId = c.id and c.isDelete != 1  where c.id = '" + gameId + "' and a.gameId = '" + gameId +
                "' and a.isDelete != 1 ";

        if (server.getServer() != "") {
            sql += " and a.server LIKE '%" + server.getServer() + "%'";
        }
        if (server.getServer_describe() != "") {
            sql += " and a.server_describe LIKE '%" + server.getServer_describe() + "%'";
        }
        if (server.getServerIp() != "") {
            sql += " and a.serverIp LIKE '%" + server.getServerIp() + "%'";
        }
        if (!Objects.equals(platformId, "0")) {
            sql += " and a.platformId ='" + platformId + "'";
        }
        if (!Objects.equals(channelId, "0")) {
            sql += " and a.channel like '%|" + channelId + "|%'";
        }
        if (gameName != "") {
            sql += " and c.gameName LIKE '%" + gameName + "%'";
        }
        if (!Objects.equals(server.getState(), null) && !Objects.equals(server.getState(), -1)) {
            sql += " and a.state ='" + server.getState() + "' ";

        }
        sql += " order by a.id ";
        logger.debug("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        int total = list.size();
        if (!Objects.equals(isPage, "")) {
            sql += " limit " + (pageNo - 1) * pageSize + ", " + pageSize;
        }

        logger.debug("sql：" + sql);
        list = jdbcTemplate.queryForList(sql);
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        JsonMap.put("total", total);
        return JsonMap;
    }

    /***
     * 外部通过平台Tag回去服务器列表
     * @param str
     * @return
     */
    public Map<String, Object> getServerList(String str) {
        String gameId = str.split(";")[0].toString();
        String platform = str.split(";")[1].toString();
        String channel = str.split(";")[2].toString();
        String sql = "select a.*,b.platform,c.gameName from t_gameserver as a left JOIN \n" +
                " t_gameplatform  as b on a.platformId = b.platformId and b.isDelete!=1  left JOIN \n" +
                " t_game as c on b.gameId = c.id and c.isDelete != 1  where a.isDelete != 1 and a.platformTag = '" + platform + "' and a.gameId = '" +
                gameId + "'";
        if (Objects.equals(channel, "null")) {
            //没有渠道参数
        } else {
            String channelId = getChannalIdForGameIdForPlatformForChannel(gameId, platform, channel);
            if (Objects.equals(channelId, "")) {
                //找不到渠道Id
                Map<String, Object> JsonMap = new HashMap();
                JsonMap.put("list", new ArrayList<>());
                JsonMap.put("total", 0);
                return JsonMap;
            }
            sql += " and  a.channel like '%|" + channelId + "|%' ";
        }
        sql += " order by a.id ";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        int total = list.size();
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        JsonMap.put("total", total);
        return JsonMap;
    }

    public String getChannalIdForGameIdForPlatformForChannel(String gameId, String platform, String channel) {
        String sql =
                "select c.channelId from t_game as a join t_gameplatform  as b on a.id = b.gameId join t_platform_channel as c on c.platformId = b.platformId where a.isDelete!=1 and b.isDelete!=1 and c.isDelete!=1 and a.id='" +
                        gameId + "' and b.platformTag='" + platform + "' and c.channelTag = '" + channel + "'";
        sql += " order by a.id ";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        if (list.size() > 0) {
            return list.get(0).get("channelId").toString();
        } else {
            return "";
        }

    }

    public int addServer(Server server) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        String sql =
                "insert into t_gameserver (gameId,serverId,server,serverIp,serverPort,server_describe,platformId,sort,addUser,addDatetime,state,isDelete) " +
                        " values ('" + server.getGameId() + "','" + server.getServerId() + "','" + server.getServer() + "','" + server.getServerIp() +
                        "','" + server.getServerPort() + "','" + server.getServer_describe() + "','" + server.getPlatformId() + "','0','" +
                        server.getAddUser() + "','" + addDatetime + "','0','0')";
        logger.debug("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int editServer(Server server) {
        String sql = "UPDATE t_gameserver as a SET a.gameId = '" + server.getGameId() + "', a.serverId = '" + server.getServerId() + "', a.server='" +
                server.getServer() + "',a.server_describe = '" + server.getServer_describe() + "'," + "a.platformId='" + server.getPlatformId() +
                "'," + "a.serverIp='" + server.getServerIp() + "' ,a.serverPort = '" + server.getServerPort() + "',a.addUser = '" +
                server.getAddUser() + "',a.openServiceTime='" + server.getOpenServiceTime() + "'   where a.id =" + server.getId() + "";
        logger.debug("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public List<Map<String, Object>> getAllPlatformList(String gameId) {
        String sql = "select * from t_gameplatform where isDelete != 1 ";
        if (!Objects.equals(gameId, "")) {
            sql += " and gameId = '" + gameId + "'";
        }
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        logger.debug("sql：" + sql);
        return list;
    }

    public int changeStateToNormal_Server(Server server) {
        String sql = "UPDATE t_gameserver SET state='0' where id ='" + server.getId() + "'";
        logger.debug("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int changeStateToFrozen_Server(Server server) {
        String sql = "UPDATE t_gameserver SET state='1' where id ='" + server.getId() + "'";
        logger.debug("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int deleteServer(Server server) {
        String sql = "UPDATE t_gameserver SET isDelete='1' where id ='" + server.getId() + "'";
        logger.debug("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int[] deleteAllServer(String[] serverList) {
        String sql[] = new String[serverList.length];
        String strSql = "";
        int[] temp = new int[serverList.length];
        for (int i = 0; i < serverList.length; i++) {
            sql[i] = "UPDATE  t_gameserver  set isDelete='1' where id = '" + serverList[i] + "'; ";
            strSql += sql;
        }
        logger.debug("sql：" + strSql);
        temp = jdbcTemplate.batchUpdate(sql);
        return temp;
    }

    public List<Map<String, Object>> getPlatformListForUser(User user) {
        String sql = "SELECT d.platformId as platformId, d.platform as platformName  from t_user as a \n" +
                "join t_user_roles as b on a.id = b.userId \n" + "join t_role as c on b.roleId = c.id \n" +
                "join t_gameplatform as d on c.id = d.roleId  \n" + "where a.isDelete != 1 and c.isDelete!=1 and d.isDelete!=1 and  a.id =" +
                user.getId();
        logger.debug("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    public List<Map<String, Object>> getServerListForPlatform(Platform platform, String gameId) {
        String sql = "SELECT a.serverId as serverId,a.server as serverName,a.serverIp from t_gameserver as a \n" +
                "join t_gameplatform as b on a.platformId = b.platformId join t_game as c on b.gameId = c.id and c.isDelete!=1  where c.id='" +
                gameId + "' and a.gameId = '" + gameId + "' and a.isDelete != 1 and b.isDelete!=1  and  b.platformId =" + platform.getPlatformId();
        logger.debug("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    public int setDefaultServer(Server server) {
        String sql = "UPDATE t_server_config SET isDefault=0 ";
        int temp = jdbcTemplate.update(sql);
        sql = "UPDATE t_gameserver SET isDefault=1 where id ='" + server.getId() + "'";
        logger.debug("sql：" + sql);
        temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int ChangeState(Server server) {
        String sql = "UPDATE t_gameserver SET state='" + server.getState() + "' where id ='" + server.getId() + "'";
        logger.debug("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public List<Map<String, Object>> getServerTree(String gameId) {
        String sql =
                "SELECT a.id as gameId ,b.platformId as platformId,c.serverId as serverId,a.gameName,b.platform,c.server FROM t_game as a join t_gameplatform as b on a.id = b.gameId \n" +
                        "join t_gameserver as c on b.platformId = c.platformId and c.gameId = a.id where c.gameId = '" + gameId +
                        "' and a.isDelete!=1 and b.isDelete!=1 and c.isDelete !=1 ORDER BY a.id ,b.id,c.id";

        logger.debug("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    public String getGameServerApi(String gameId) {
        String sql = "select * from t_game where isDelete != 1 ";
        if (!Objects.equals(gameId, "")) {
            sql += " and id = '" + gameId + "'";
        }
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        logger.debug("sql：" + sql);
        if (Objects.equals(list.get(0).get("serverApi"), null)) {
            return "";
        }
        return list.get(0).get("serverApi").toString();
    }

    private static boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
        boolean isSameMonth = isSameYear && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        boolean isSameDate = isSameMonth && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
        return isSameDate;
    }

    public int[] SynServerList(JSONArray jsonArray, String gameId) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        SimpleDateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        int[] temp = null;
        logger.debug("jsonArray.length():" + jsonArray.length());
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
                                "' and gameId = '" + gameId + "'  and isDelete != 1";
                List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlSelect);
                Date synData = dfDate.parse(jsonObject.get("time").toString());
                Date oldData = df.parse(list.get(0).get("openServiceTime").toString());
                if (list.size() > 0) {
                    //存在，更新
                    String sqlUpdate = "UPDATE t_gameserver as a SET a.server='" + jsonObject.get("sname") + "',a.server_describe = '" +
                            jsonObject.get("sname") + "'," + "a.platformId='" + jsonObject.get("pid") + "',a.platformTag='" +
                            jsonObject.get("pname") + "',";
                    if (!isSameDate(synData, oldData)) {
                        sqlUpdate += "a.openServiceTime='" + jsonObject.get("time") + "',";
                    }
                    sqlUpdate += "a.addDatetime='" + addDatetime + "'," + "a.serverIp='" + jsonObject.get("domain") + "',a.area='" +
                            jsonObject.get("area") + "' ,a.serverPort = '" + jsonObject.get("port") + "',a.addUser = 'cdk' where a.serverId =" +
                            jsonObject.get("sid") + " and  a.platformId = '" + jsonObject.get("pid") + "' and a.gameId = '" + gameId + "' ";
                    jdbcTemplate.update(sqlUpdate);
                } else {
                    //没有，新增
                    String sqlInsert =
                            "insert into t_gameserver (serverId,server,serverIp,serverPort,platformId,platformTag,server_describe,state,sort,addUser,addDatetime,isDelete,isDefault,area,openServiceTime,channel,gameId) values ('" +
                                    jsonObject.get("sid") + "', '" + jsonObject.get("sname") + "','" + jsonObject.get("domain") + "','" +
                                    jsonObject.get("port") + "','" + jsonObject.get("pid") + "','" + jsonObject.get("pname") + "','" +
                                    jsonObject.get("sname") + "','0','0','cdk','" + addDatetime + "',0,'0','" + jsonObject.get("area") + "','" +
                                    jsonObject.get("time") + "','','" + gameId + "' ) ; ";
                    jdbcTemplate.update(sqlInsert);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return temp;
    }

}
