package com.cdk.dao;

import com.cdk.entity.Platform;

import java.util.List;
import java.util.Map;

public interface PlatformDao {
    public Map<String, Object> getAllPlatform(Platform platform, String gameName, String isPage, int pageNo, int pageSize);

    public List<Map<String, Object>> getAllGameList();

    public List<Map<String, Object>> getAllRoleList();

    public int addPlatform(Platform platform);

    public int editPlatform(Platform platform);

    public int deletePlatform(Platform platform);

    public int changeStateToNormal_Platform(Platform platform);

    public int changeStateToFrozen_Platform(Platform platform);

    public int[] deleteAllPlatform(String[] platformList);
}
