package com.cdk.dao;

import com.cdk.entity.VueLoginInfoVo;

import java.util.List;
import java.util.Map;

public interface LoginDao {
    public List<Map<String, Object>> login(VueLoginInfoVo loginInfoVo);
}
