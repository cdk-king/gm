package com.cdk.dao;

import com.cdk.entity.User;

import java.util.List;
import java.util.Map;

public interface UtilsDao {
    public List<Map<String, Object>> getUserAllRight(User user);

    public List<Map<String, Object>> getUserAllRole(User user);
}
