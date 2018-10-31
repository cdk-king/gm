package com.cdk.dao;

import com.cdk.entity.Gift;

import java.util.Map;

public interface GiftDao {

    public Map<String, Object> getGift(Gift gift, String isPage, int pageNo, int pageSize, String strPlatform);

    public int addGift(Gift gift);
}
