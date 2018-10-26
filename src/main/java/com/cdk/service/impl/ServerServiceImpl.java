package com.cdk.service.impl;

import com.cdk.dao.impl.ServerDaoImpl;
import com.cdk.entity.Server;
import com.cdk.entity.User;
import com.cdk.result.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ServerServiceImpl {
    public static final String Divider = "############################";
    public static final String Split = "----------------";

    @Autowired
    public ServerDaoImpl serverDaoImpl;

    public Result getAllServer(Map map) {
        String serverName = (map.get("server") != null ? map.get("server").toString() : "");
        String serverIp = (map.get("serverIp") != null ? map.get("serverIp").toString() : "");
        String platformName = (map.get("platform") != null ? map.get("platform").toString() : "");
        String server_describe = (map.get("server_describe") != null ? map.get("server_describe").toString() : "");
        String addUser = (map.get("addUser") != null ? map.get("addUser").toString() : "");
        String addDatetime = (map.get("addDatetime") != null ? map.get("addDatetime").toString() : "");
        String state = (map.get("state") != null ? map.get("state").toString() : "");
        String isPage = (map.get("isPage") != null ? map.get("isPage").toString() : "");
        if (state == "") {
            state = "0";
        }
        System.out.println("server：" + serverName);
        System.out.println("serverIp：" + serverIp);
        System.out.println("platformName：" + platformName);
        System.out.println("server_describe：" + server_describe);
        System.out.println("addUser：" + addUser);
        System.out.println("addDatetime：" + addDatetime);
        System.out.println("state：" + state);

        String StrPageNo = (map.get("pageNo") != null ? map.get("pageNo").toString() : "1");
        String StrPageSize = (map.get("pageSize") != null ? map.get("pageSize").toString() : "5");

        System.out.println("pageNo：" + StrPageNo);
        System.out.println("pageSize：" + StrPageSize);
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


        Map<String, Object> JsonMap = serverDaoImpl.getAllServer(server, platformName, isPage, pageNo, pageSize);
        if (Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(400, "服务器列表获取失败", "");
        } else {
            re = new Result(200, "服务器列表获取成功", JsonMap);
        }
        return re;
    }


    public Result addServer(Map map) {

        String id = (map.get("id") != null ? map.get("id").toString() : "");
        String platformId = (map.get("platformId") != null ? map.get("platformId").toString() : "");
        String serverName = (map.get("server") != null ? map.get("server").toString() : "");
        String serverIp = (map.get("serverIp") != null ? map.get("serverIp").toString() : "");
        String server_describe = (map.get("server_describe") != null ? map.get("server_describe").toString() : "");
        String sort = (map.get("sort") != null ? map.get("sort").toString() : "");
        String addUser = (map.get("addUser") != null ? map.get("addUser").toString() : "");
        String state = (map.get("state") != null ? map.get("state").toString() : "");

        System.out.println("id：" + id);
        System.out.println("server：" + serverName);
        System.out.println("server_describe：" + server_describe);
        System.out.println("serverIP：" + serverIp);
        System.out.println("sort：" + sort);
        System.out.println("addUser：" + addUser);
        System.out.println("state：" + state);

        Server server = new Server();
        server.setServer(serverName);
        server.setServerIp(serverIp);
        server.setServer_describe(server_describe);
        server.setPlatformId(Integer.parseInt(platformId));
        server.setAddUser(addUser);

        Result re;
        int temp = serverDaoImpl.addServer(server);
        if (temp > 0) {
            System.out.println("服务器添加成功");
            re = new Result(200, "服务器添加成功", null);
        } else {
            System.out.println("服务器添加失败");
            re = new Result(400, "服务器添加失败", null);
        }
        return re;
    }


    public Result editServer(Map map) {

        String id = (map.get("id") != null ? map.get("id").toString() : "");
        String platformId = (map.get("platformId") != null ? map.get("platformId").toString() : "");
        String serverName = (map.get("server") != null ? map.get("server").toString() : "");
        String serverIp = (map.get("serverIp") != null ? map.get("serverIp").toString() : "");
        String server_describe = (map.get("server_describe") != null ? map.get("server_describe").toString() : "");
        String sort = (map.get("sort") != null ? map.get("sort").toString() : "");
        String addUser = (map.get("addUser") != null ? map.get("addUser").toString() : "");
        String state = (map.get("state") != null ? map.get("state").toString() : "");

        System.out.println("id：" + id);
        System.out.println("server：" + serverName);
        System.out.println("server_describe：" + server_describe);
        System.out.println("serverIP：" + serverIp);
        System.out.println("sort：" + sort);
        System.out.println("addUser：" + addUser);
        System.out.println("state：" + state);

        Server server = new Server();
        server.setId(Integer.parseInt(id));
        server.setServer(serverName);
        server.setServerIp(serverIp);
        server.setServer_describe(server_describe);
        server.setPlatformId(Integer.parseInt(platformId));
        server.setAddUser(addUser);

        Result re;
        int temp = serverDaoImpl.editServer(server);
        if (temp > 0) {
            System.out.println("服务器信息修改成功");
            re = new Result(200, "服务器信息更新成功", null);
        } else {
            System.out.println("服务器信息修改失败");
            re = new Result(400, "服务器信息更新失败", null);
        }
        return re;
    }

    public Result getAllPlatformList(Map map) {
        List<Map<String, Object>> list = serverDaoImpl.getAllPlatformList();
        Result re;
        if (list.size() > 0) {
            System.out.println("渠道列表获取成功");
            re = new Result(200, "渠道列表获取成功", list);
        } else {
            System.out.println("渠道列表获取失败");
            re = new Result(400, "渠道列表获取失败", list);
        }
        return re;
    }

    public Result changeStateToNormal_Server(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        System.out.println("id：" + id);
        Server server = new Server();
        server.setId(Integer.parseInt(id));
        Result re;
        int temp = serverDaoImpl.changeStateToNormal_Server(server);
        if (temp > 0) {
            System.out.println("服务器解冻成功");
            re = new Result(200, "服务器解冻成功", null);
        } else {
            System.out.println("服务器解冻失败");
            re = new Result(400, "服务器解冻失败", null);
        }
        return re;
    }

    public Result changeStateToFrozen_Server(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        System.out.println("id：" + id);
        Server server = new Server();
        server.setId(Integer.parseInt(id));
        Result re;
        int temp = serverDaoImpl.changeStateToFrozen_Server(server);
        if (temp > 0) {
            System.out.println("服务器冻结成功");
            re = new Result(200, "服务器冻结成功", null);
        } else {
            System.out.println("服务器冻结失败");
            re = new Result(400, "服务器冻结失败", null);
        }
        return re;
    }

    public Result deleteServer(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        System.out.println("id：" + id);
        Server server = new Server();
        server.setId(Integer.parseInt(id));

        Result re;
        int temp = serverDaoImpl.deleteServer(server);
        if (temp > 0) {
            System.out.println("服务器删除成功");
            re = new Result(200, "服务器删除成功", null);
        } else {
            System.out.println("服务器删除失败");
            re = new Result(400, "服务器删除失败", null);
        }
        return re;
    }

    public Result deleteAllServer(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        System.out.println("id：" + id);
        if (Objects.equals(id, "")) {
            System.out.println("无任何批量删除操作");
            return new Result(400, "无任何批量删除操作", null);
        }
        String[] ObjectArry = id.split(",");
        System.out.println("ObjectArry：" + ObjectArry);
        int[] temp = new int[ObjectArry.length];
        Result re;
        temp = serverDaoImpl.deleteAllServer(ObjectArry);
        if (temp.length != 0) {
            System.out.println("服务器批量删除成功");
            re = new Result(200, "服务器批量删除成功", null);
        } else if (ObjectArry.length == 0) {
            System.out.println("无任何删除操作");
            re = new Result(400, "无任何删除操作", null);
        } else {
            System.out.println("服务器批量删除失败");
            re = new Result(400, "服务器批量删除失败", null);
        }
        return re;
    }

    public Result getPlatformListForUser(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        System.out.println("id：" + id);
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

    public Result getServerListForUser(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        System.out.println("id：" + id);

        User user = new User();
        user.setId(Integer.parseInt(id));

        List<Map<String, Object>> list = serverDaoImpl.getPlatformListForUser(user);
        Result re;
        if (list.size() != 0) {
            re = new Result(200, "渠道服务器列表获取成功", list);
        } else {
            re = new Result(400, "渠道服务器列表获取失败", list);
        }
        return re;
    }

    public Result getServerTree(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        System.out.println("id：" + id);

        List<Map<String, Object>> list = serverDaoImpl.getServerTree();
        System.out.println(list);
        Result re;
        if (list.size() != 0) {
            re = new Result(200, "服务器树状结构获取成功", list);
        } else {
            re = new Result(400, "服务器树状结构获取失败", list);
        }
        return re;
    }
}

