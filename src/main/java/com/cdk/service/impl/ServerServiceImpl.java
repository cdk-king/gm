package com.cdk.service.impl;

import com.cdk.cache.CacheManagerImpl;
import com.cdk.dao.impl.ServerDaoImpl;
import com.cdk.entity.Platform;
import com.cdk.entity.Server;
import com.cdk.entity.User;
import com.cdk.result.Result;
import com.cdk.util.ApiHandeler;
import com.cdk.util.HttpRequestUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

@Service
public class ServerServiceImpl extends ApiHandeler {
    private static Logger logger = Logger.getLogger(String.valueOf(ServerServiceImpl.class));
    public static final String Divider = "############################";
    public static final String Split = "----------------";

    @Autowired
    public ServerDaoImpl serverDaoImpl;

    public Result getAllServer(Map map) {
        String serverName = (map.get("server") != null ? map.get("server").toString() : "");
        String serverIp = (map.get("serverIp") != null ? map.get("serverIp").toString() : "");
        String platformName = (map.get("platform") != null ? map.get("platform").toString() : "");
        String gameName = (map.get("gameName") != null ? map.get("gameName").toString() : "");
        String server_describe = (map.get("server_describe") != null ? map.get("server_describe").toString() : "");
        String addUser = (map.get("addUser") != null ? map.get("addUser").toString() : "");
        String addDatetime = (map.get("addDatetime") != null ? map.get("addDatetime").toString() : "");
        String state = (map.get("state") != null ? map.get("state").toString() : "");
        String isPage = (map.get("isPage") != null ? map.get("isPage").toString() : "");
        if (state == "") {
            state = "-1";
        }

        String StrPageNo = (map.get("pageNo") != null ? map.get("pageNo").toString() : "1");
        String StrPageSize = (map.get("pageSize") != null ? map.get("pageSize").toString() : "5");

        logger.info("pageNo：" + StrPageNo);
        logger.info("pageSize：" + StrPageSize);
        int pageNo = 1;
        int pageSize = 5;

        try {
            pageNo = Integer.parseInt(StrPageNo);
            pageSize = Integer.parseInt(StrPageSize);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        Result re;
        Server server = new Server();
        server.setServer(serverName);
        server.setServerIp(serverIp);
        server.setServer_describe(server_describe);
        server.setState(Integer.parseInt(state));


        Map<String, Object> JsonMap = serverDaoImpl.getAllServer(server, platformName, gameName, isPage, pageNo, pageSize);
        if (Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(400, "服务器列表获取失败", "");
        } else {
            re = new Result(200, "服务器列表获取成功", JsonMap);
        }
        return re;
    }


    public Result addServer(Map map) {

        String serverId = (map.get("serverId") != null ? map.get("serverId").toString() : "");
        String platformId = (map.get("platformId") != null ? map.get("platformId").toString() : "");
        String serverName = (map.get("server") != null ? map.get("server").toString() : "");
        String serverIp = (map.get("serverIp") != null ? map.get("serverIp").toString() : "");
        String serverPort = (map.get("serverPort") != null ? map.get("serverPort").toString() : "");
        String server_describe = (map.get("server_describe") != null ? map.get("server_describe").toString() : "");
        String sort = (map.get("sort") != null ? map.get("sort").toString() : "");
        String addUser = (map.get("addUser") != null ? map.get("addUser").toString() : "");
        String state = (map.get("state") != null ? map.get("state").toString() : "");

        Server server = new Server();
        server.setServer(serverName);
        server.setServerId(Integer.parseInt(serverId));
        server.setServerIp(serverIp);
        server.setServer_describe(server_describe);
        server.setPlatformId(Integer.parseInt(platformId));
        server.setAddUser(addUser);
        server.setServerPort(serverPort);

        Result re;
        int temp = serverDaoImpl.addServer(server);
        if (temp > 0) {
            logger.info("服务器添加成功");
            re = new Result(200, "服务器添加成功", null);
        } else {
            logger.info("服务器添加失败");
            re = new Result(400, "服务器添加失败", null);
        }
        return re;
    }


    public Result editServer(Map map) {

        String id = (map.get("id") != null ? map.get("id").toString() : "");
        String platformId = (map.get("platformId") != null ? map.get("platformId").toString() : "");
        String serverName = (map.get("server") != null ? map.get("server").toString() : "");
        String serverIp = (map.get("serverIp") != null ? map.get("serverIp").toString() : "");
        String serverPort = (map.get("serverPort") != null ? map.get("serverPort").toString() : "");
        String server_describe = (map.get("server_describe") != null ? map.get("server_describe").toString() : "");
        String sort = (map.get("sort") != null ? map.get("sort").toString() : "");
        String addUser = (map.get("addUser") != null ? map.get("addUser").toString() : "");
        String state = (map.get("state") != null ? map.get("state").toString() : "");

        Server server = new Server();
        server.setId(Integer.parseInt(id));
        server.setServer(serverName);
        server.setServerIp(serverIp);
        server.setServer_describe(server_describe);
        server.setPlatformId(Integer.parseInt(platformId));
        server.setAddUser(addUser);
        server.setServerPort(serverPort);

        Result re;
        int temp = serverDaoImpl.editServer(server);
        if (temp > 0) {
            logger.info("服务器信息修改成功");
            re = new Result(200, "服务器信息更新成功", null);
        } else {
            logger.info("服务器信息修改失败");
            re = new Result(400, "服务器信息更新失败", null);
        }
        return re;
    }

    public Result getAllPlatformList(Map map) {
        List<Map<String, Object>> list = serverDaoImpl.getAllPlatformList();
        Result re;
        if (list.size() > 0) {
            logger.info("渠道列表获取成功");
            re = new Result(200, "渠道列表获取成功", list);
        } else {
            logger.info("渠道列表获取失败");
            re = new Result(400, "渠道列表获取失败", list);
        }
        return re;
    }

    public Result changeStateToNormal_Server(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        logger.info("id：" + id);
        Server server = new Server();
        server.setId(Integer.parseInt(id));
        Result re;
        int temp = serverDaoImpl.changeStateToNormal_Server(server);
        if (temp > 0) {
            logger.info("服务器解冻成功");
            re = new Result(200, "服务器解冻成功", null);
        } else {
            logger.info("服务器解冻失败");
            re = new Result(400, "服务器解冻失败", null);
        }
        return re;
    }

    public Result changeStateToFrozen_Server(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        logger.info("id：" + id);
        Server server = new Server();
        server.setId(Integer.parseInt(id));
        Result re;
        int temp = serverDaoImpl.changeStateToFrozen_Server(server);
        if (temp > 0) {
            logger.info("服务器冻结成功");
            re = new Result(200, "服务器冻结成功", null);
        } else {
            logger.info("服务器冻结失败");
            re = new Result(400, "服务器冻结失败", null);
        }
        return re;
    }

    public Result deleteServer(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        logger.info("id：" + id);
        Server server = new Server();
        server.setId(Integer.parseInt(id));

        Result re;
        int temp = serverDaoImpl.deleteServer(server);
        if (temp > 0) {
            logger.info("服务器删除成功");
            re = new Result(200, "服务器删除成功", null);
        } else {
            logger.info("服务器删除失败");
            re = new Result(400, "服务器删除失败", null);
        }
        return re;
    }

    public Result deleteAllServer(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        logger.info("id：" + id);
        if (Objects.equals(id, "")) {
            logger.info("无任何批量删除操作");
            return new Result(400, "无任何批量删除操作", null);
        }
        String[] ObjectArry = id.split(",");
        logger.info("ObjectArry：" + ObjectArry);
        int[] temp = new int[ObjectArry.length];
        Result re;
        temp = serverDaoImpl.deleteAllServer(ObjectArry);
        if (temp.length != 0) {
            logger.info("服务器批量删除成功");
            re = new Result(200, "服务器批量删除成功", null);
        } else if (ObjectArry.length == 0) {
            logger.info("无任何删除操作");
            re = new Result(400, "无任何删除操作", null);
        } else {
            logger.info("服务器批量删除失败");
            re = new Result(400, "服务器批量删除失败", null);
        }
        return re;
    }

    public Result getPlatformListForUser(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        logger.info("id：" + id);
        User user = new User();
        user.setId(Integer.parseInt(id));

        List<Map<String, Object>> list = serverDaoImpl.getPlatformListForUser(user);
        Result re;
        if (list.size() != 0) {
            re = new Result(200, "用户渠道列表获取成功", list);
        } else {
            re = new Result(400, "用户渠道列表获取失败", list);
        }
        return re;
    }

    public Result getServerListForPlatform(Map map) {
        String platformId = (map.get("platformId") != null ? map.get("platformId").toString() : "");
        logger.info("platformId：" + platformId);
        if (Objects.equals(platformId, "")) {
            return new Result(400, "渠道服务器列表获取失败", null);
        }
        Platform platform = new Platform();
        platform.setPlatformId(Integer.parseInt(platformId));

        List<Map<String, Object>> list = serverDaoImpl.getServerListForPlatform(platform);
        Result re;
        if (list.size() != 0) {
            re = new Result(200, "渠道服务器列表获取成功", list);
        } else {
            re = new Result(400, "渠道服务器列表获取失败", list);
        }
        return re;
    }

    public Result getServerTree(Map map) {

        List<Map<String, Object>> list = serverDaoImpl.getServerTree();
        logger.info(list.toString());
        Result re;
        if (list.size() != 0) {
            re = new Result(200, "服务器树状结构获取成功", list);
        } else {
            re = new Result(400, "服务器树状结构获取失败", list);
        }
        return re;
    }

    public Result SynServerList(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        logger.info("id：" + id);
        String param = "";

        String url = "http://123.207.115.217:20000/serversByJson";
        logger.info(url);
        HttpRequestUtil httpRequestUtil = new HttpRequestUtil();
        String data = httpRequestUtil.sendGet(url, param);
        logger.info(data);
        Result re;
        if (data.length() != 0) {
            JSONArray jsonArray = null;
            try {
                jsonArray = new JSONArray(data);
            } catch (JSONException e) {
                e.printStackTrace();
                re = new Result(400, "服务器列表数据解析失败", data);
            }
            int[] temp = serverDaoImpl.SynServerList(jsonArray);

            re = new Result(200, "服务器列表获取成功", data);
        } else {
            re = new Result(400, "服务器列表获取失败", data);
        }
        return re;
    }

    public Result setDefaultServer(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        logger.info("id：" + id);
        Server server = new Server();
        server.setId(Integer.parseInt(id));
        Result re;
        int temp = serverDaoImpl.setDefaultServer(server);
        if (temp > 0) {
            logger.info("默认服务器设置成功");
            re = new Result(200, "默认服务器设置成功", null);
        } else {
            logger.info("默认服务器设置失败");
            re = new Result(400, "默认服务器设置失败", null);
        }
        return re;
    }

    /***
     * 状态（{0  未开启,  1维护，2新服，3良好，4爆满}）
     * @param map
     * @return
     */
    public Result ChangeState(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        String strState = (map.get("state") != null ? map.get("state").toString() : "0");
        if (Objects.equals(strState, "")) {
            strState = "0";
        }
        int state = Integer.parseInt(strState);
        Server server = new Server();
        server.setId(Integer.parseInt(id));
        server.setState(state);
        int temp = serverDaoImpl.ChangeState(server);
        Result re;
        if (temp > 0) {
            logger.info("服务器状态设置成功");
            re = new Result(200, "服务器状态设置成功", null);
        } else {
            logger.info("服务器状态设置失败");
            re = new Result(400, "服务器状态设置失败", null);
        }
        return re;
    }

    @Autowired
    private CacheManagerImpl cacheManagerImpl;


    Logger logger2 = Logger.getLogger("getServerListLog");

    /***
     *
     * @param map
     * @return
     */
    public Map<String, Object> getServerList(Map map) {
        Result re;
        int def = 0;
        String platform = (map.get("platform") != null ? map.get("platform").toString() : "");
        String channel = (map.get("channel") != null ? map.get("channel").toString() : "");
        String while_id = (map.get("while_id") != null ? map.get("while_id").toString() : "");
        Map<String, Object> JsonMap = new HashMap<>();
        if (cacheManagerImpl.isContains("ServerList-" + platform)) {
            JsonMap = (Map<String, Object>) cacheManagerImpl.getCacheByKey("ServerList-" + platform).getDatas();
            logger2.info("已找到缓存" + "ServerList-" + platform);
            logger2.info(JsonMap.toString());
        } else {
            logger2.info("未找到缓存" + "ServerList-" + platform);
            JsonMap = serverDaoImpl.getServerList(platform);
            int timeOut = 60 * 1000;
            cacheManagerImpl.putCache("ServerList-" + platform, JsonMap, timeOut);
            logger2.info("已设置缓存，缓存时间" + Math.floor(timeOut / 1000L) + "秒");
            logger2.info(JsonMap.toString());
        }


        List<Map<String, Object>> list = (List<Map<String, Object>>) JsonMap.get("list");
        int len = list.size();
        Map<String, Object> all = new HashMap<>();
        if (len > 0) {

            Map<String, Object> serverList = new HashMap<>();
            for (int i = 0; i < len; i++) {
                String serverId = list.get(i).get("serverId").toString();
                String area = list.get(i).get("area").toString();
                String state = list.get(i).get("state").toString();
                String server = list.get(i).get("server").toString();
                String ip = list.get(i).get("serverIp").toString();
                String port = list.get(i).get("serverPort").toString();
                Map<String, Object> ServerItem = new HashMap<>();
                ServerItem.put("real", serverId);
                ServerItem.put("area", area);
                ServerItem.put("show", serverId);
                ServerItem.put("status", state);
                ServerItem.put("name", server);
                ServerItem.put("ip", ip + ":" + port);
                ServerItem.put("platform", platform);
                serverList.put(serverId, ServerItem);
                if (Objects.equals(list.get(i).get("isDefault").toString(), "1")) {
                    def = Integer.parseInt(list.get(i).get("isDefault").toString());
                }
            }
            all.put("servers", serverList);
            Map<String, Object> area = new HashMap<>();
            int arealen = Integer.parseInt(JsonMap.get("total").toString());
            int count = 1;
            while (arealen > 0) {
                area.put(count + "", count + "" + "-50服");
                arealen = arealen - 50;
                count++;
            }
            all.put("area", area);
            all.put("default", def);

        }

        return all;
    }
}