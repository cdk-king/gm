package com.cdk.dao.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cdk.entity.NewGift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class NewGiftDaoImpl {
    public static final String Divider = "############################";
    public static final String Split = "----------------";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int[] ImportGift(JSONArray jsonArray, int platformId) {
        String sql[] = new String[jsonArray.size()];
        String strSql = "";
        //清空数据库表
        strSql = "truncate table t_gift_upload";
        jdbcTemplate.update(strSql);
        strSql = "";
        int[] temp = new int[jsonArray.size()];
        for (int i = 1; i < jsonArray.size(); i++) {

            JSONObject jsonObject = jsonArray.getJSONObject(i);
            sql[i] = "insert into t_gift_upload (giftId,limitCount,expire_time,goods_prize1,value_prize1,platformId) values ('" +
                    jsonObject.get("giftId") + "', '" + jsonObject.get("limit") + "','" + jsonObject.get("expire_time") + "','" +
                    jsonObject.get("goods_prize1") + "', '" + jsonObject.get("value_prize1") + "'  ,'" + platformId + "' ) ; ";
            strSql += sql[i];
        }
        System.out.println(strSql);
        temp = jdbcTemplate.batchUpdate(sql);
        return temp;
    }

    public Map<String, Object> getGiftUpload(NewGift newGift, String isPage, int pageNo, int pageSize, String strPlatform) {

        String sql = "select a.* , b.platform  from t_gift_upload as a join  t_gameplatform as b on a.platformId = b.id where a.platformId IN (" +
                strPlatform + ")  and b.isDelete != 1  ";
        if (newGift.getPlatformId() != 0) {
            sql += " and a.platformId ='" + newGift.getPlatformId() + "' ";
        }
        if (newGift.getGiftName() != "") {
            sql += " and a.giftName LIKE '%" + newGift.getGiftName() + "%'";
        }

        if (newGift.getGiftTag() != "") {
            sql += " and a.giftTag LIKE '%" + newGift.getGiftTag() + "%'";
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
}
