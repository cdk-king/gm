package com.cdk.dao.impl;


import com.cdk.dao.GiftDao;
import com.cdk.entity.Gift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class GiftDaoImpl implements GiftDao {
    public static final String Divider = "############################";
    public static final String Split = "----------------";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Map<String, Object> getGift(Gift gift, String isPage, int pageNo, int pageSize, String strPlatform) {
        String sql = "select a.* , b.platform  from t_gift as a join  t_gameplatform as b on a.platformId = b.platformId where a.platformId IN (" +
                strPlatform + ") and a.isDelete != 1  and b.isDelete != 1  ";
        if (gift.getPlatformId() != 0) {
            sql += " and a.platformId ='" + gift.getPlatformId() + "' ";
        }
        if (gift.getGiftName() != "") {
            sql += " and a.giftName LIKE '%" + gift.getGiftName() + "%'";
        }

        if (gift.getGiftTag() != "") {
            sql += " and a.giftTag LIKE '%" + gift.getGiftTag() + "%'";
        }

        //0：全部，1：冻结，2：未冻结
        if (!Objects.equals(gift.getState(), null) && !Objects.equals(gift.getState(), 0)) {
            if (Objects.equals(gift.getState(), 1)) {
                sql += " and a.state = 1 ";
            } else if (Objects.equals(gift.getState(), 2)) {
                sql += " and a.state != 1 ";
            }
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


    @Override
    public int addGift(Gift gift) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        String sql =
                "insert into t_gift (giftName,giftTag,gift_describe,platformId,giftType,addUser,addDatetime,sort,state,isDelete) " + " values ('" +
                        gift.getGiftName() + "','" + gift.getGiftTag() + "','" + gift.getGift_describe() + "','" + gift.getPlatformId() + "','" +
                        gift.getGiftType() + "','" + gift.getAddUser() + "','" + addDatetime + "','0','0','0')";
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    @Override
    public Map<String, Object> getGiftListForPlatformId(Gift gift) {
        String sql = "select * from t_gift where platformId = " + gift.getPlatformId() + "  and isDelete!=1 ";
        System.out.println("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        return JsonMap;
    }

    public Map<String, Object> getNewGiftListForPlatformId(Gift gift) {
        String sql = "select *  from t_gift_upload where platformId = " + gift.getPlatformId() + " ";
        System.out.println("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        return JsonMap;
    }
}
