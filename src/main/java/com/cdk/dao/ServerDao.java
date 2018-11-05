package com.cdk.dao;

import com.cdk.entity.Platform;
import com.cdk.entity.Server;
import com.cdk.entity.User;

import java.util.List;
import java.util.Map;

public interface ServerDao {

    public Map<String, Object> getAllServer(Server server, String platformName, String gameName, String isPage, int pageNo, int pageSize);

    public int addServer(Server server);

    public int editServer(Server server);

    public List<Map<String, Object>> getAllPlatformList();

    public int changeStateToNormal_Server(Server server);

    public int changeStateToFrozen_Server(Server server);

    public int deleteServer(Server server);

    public int[] deleteAllServer(String[] serverList);

    public List<Map<String, Object>> getPlatformListForUser(User user);

    public List<Map<String, Object>> getServerListForPlatform(Platform platform);

    public List<Map<String, Object>> getServerTree();
}
