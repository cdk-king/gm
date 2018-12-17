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
import java.util.logging.Logger;

@Repository
public class NewGiftDaoImpl {
    private static Logger logger = Logger.getLogger(String.valueOf(NewGiftDaoImpl.class));
    public static final String Divider = "############################";
    public static final String Split = "----------------";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int ImportGift(JSONArray jsonArray, int platformId, int gameId) {
        String sql[] = new String[jsonArray.size()];
        String strSql = "";
        strSql = "";
        int temp = 1;
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String sqlSelect = "select * from t_gift_upload where giftId = '" + jsonObject.get("giftId") + "' and  platformId = '" + platformId + "'";
            List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlSelect);
            if (list.size() > 0) {
                //存在，更新
                String sqlUpdate =
                        "UPDATE t_gift_upload as a SET a.giftName='" + jsonObject.get("giftId") + "', a.limitCount='" + jsonObject.get("limit") +
                                "',a.expire_time = '" + jsonObject.get("expire_time") + "'," + "a.goods_prize1='" + jsonObject.get("goods_prize1") +
                                "',a.value_prize1='" + jsonObject.get("value_prize1") + "' ";
                jdbcTemplate.update(sqlUpdate);
            } else {
                //没有，新增
                String sqlInsert =
                        "insert into t_gift_upload (giftId,giftName,limitCount,expire_time,goods_prize1,value_prize1,platformId,gameId) values ('" +
                                jsonObject.get("giftId") + "','" + jsonObject.get("giftId") + "', '" + jsonObject.get("limit") + "','" +
                                jsonObject.get("expire_time") + "','" + jsonObject.get("goods_prize1") + "', '" + jsonObject.get("value_prize1") +
                                "'  ,'" + platformId + "','" + gameId + "' ) ; ";
                jdbcTemplate.update(sqlInsert);

            }
        }
        return temp;
    }

    public Map<String, Object> getGiftUpload(NewGift newGift, String isPage, int pageNo, int pageSize, String strPlatform) {

        String sql =
                "select a.* , b.platform  from t_gift_upload as a join  t_gameplatform as b on a.platformId = b.platformId where a.platformId IN (" +
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

        logger.info("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        int total = list.size();
        if (!Objects.equals(isPage, "")) {
            sql += " order by id limit " + (pageNo - 1) * pageSize + ", " + pageSize;
        }

        list = jdbcTemplate.queryForList(sql);
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        JsonMap.put("total", total);
        return JsonMap;
    }
}
