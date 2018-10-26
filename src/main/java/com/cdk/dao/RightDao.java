package com.cdk.dao;

import com.cdk.entity.Right;

import java.util.Map;

public interface RightDao {
    public int addRight(Right right);

    public Map<String, Object> getRight(Right right, String isPage, int pageNo, int pageSize);

    public int changeStateToFrozen_Right(Right right);

    public int changeStateToNormal_Right(Right right);

    public int deleteRight(Right right);

    public int[] deleteAllRight(String[] rightList);
}
