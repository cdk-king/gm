package com.cdk.dao.impl;


import com.cdk.entity.CDK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class CDK_DaoImpl {

    public static final String Divider = "############################";
    public static final String Split = "----------------";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> getCDK(CDK cdk, String isPage, int pageNo, int pageSize, String strPlatform) {

        String sql =
                "select a.* , b.platform  from t_cdk as a join  t_gameplatform as b on a.platformId = b.id where a.platformId IN (" + strPlatform +
                        ")  and b.isDelete != 1  ";
        if (cdk.getPlatformId() != 0) {
            sql += " and a.platformId ='" + cdk.getPlatformId() + "' ";
        }
        if (cdk.getCouponId() != 0) {
            sql += " and a.couponId LIKE '%" + cdk.getCouponId() + "%'";
        }

        if (cdk.getSequenceId() != 0) {
            sql += " and a.sequenceId LIKE '%" + cdk.getSequenceId() + "%'";
        }

        if (cdk.getCdk() != "") {
            sql += " and a.cdk LIKE '%" + cdk.getCdk() + "%'";
        }

        System.out.println("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        int total = list.size();
        if (!Objects.equals(isPage, "")) {
            sql += " limit " + (pageNo - 1) * pageSize + ", " + pageSize;
        }

        list = jdbcTemplate.queryForList(sql);
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        JsonMap.put("total", total);
        return JsonMap;
    }

    public int exchangeCDK(Map map, int platform, String cdk) {
        String couponID = (map.get("couponID") != null ? map.get("couponID").toString() : "");
        String sequenceID = (map.get("sequenceID") != null ? map.get("sequenceID").toString() : "");
        String sql =
                "insert into t_cdk (couponId,sequenceId,platformId,cdk,isUsed) values ('" + couponID + "','" + sequenceID + "','" + platform + "','" +
                        cdk + "',1)";
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

}
