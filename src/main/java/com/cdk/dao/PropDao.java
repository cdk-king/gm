package com.cdk.dao;

import com.cdk.entity.Prop;

import java.util.Map;

public interface PropDao {

    public Map<String, Object> getProp(Prop prop, String isPage, int pageNo, int pageSize, String strPlatform);

    public int addProp(Prop prop);

    public int editProp(Prop prop);

    public int deleteProp(Prop prop);

    public int changeStateToNormal_Game(Prop prop);

    public int changeStateToFrozen_Game(Prop prop);

    public int[] deleteAllProp(String[] propList);
}
