package com.cdk.dao.impl;


import com.cdk.entity.CDK;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class CDK_DaoImpl {
    private static Logger logger = LoggerFactory.getLogger(CDK_DaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public CouponDaoImpl couponDaoImpl;

    public Map<String, Object> getCDK(CDK cdk, String giftName, String isPage, int pageNo, int pageSize, String strPlatform, String gameId) {

        String sql =
                "select a.* , b.platform ,c.giftName from t_cdk as a join  t_gameplatform as b on a.platformId = b.platformId join t_gift_upload as c on c.giftId = a.couponId and c.platformId = a.platformId join t_game as d on d.id = b.gameId and d.isDelete!=1 where d.id='" +
                        gameId + "' and a.gameId ='" + gameId + "' and a.platformId IN (" + strPlatform + ")  and b.isDelete != 1 ";
        if (cdk.getPlatformId() != 0) {
            sql += " and a.platformId ='" + cdk.getPlatformId() + "' ";
        }
        if (cdk.getCouponId() != 0) {
            sql += " and a.couponId LIKE '%" + cdk.getCouponId() + "%'";
        }

        if (cdk.getSequenceId() != 0) {
            sql += " and a.sequenceId LIKE '%" + cdk.getSequenceId() + "%'";
        }
        if (!Objects.equals(giftName, "")) {
            sql += " and c.giftName LIKE '%" + giftName + "%'";
        }
        if (cdk.getCouponId() != 0) {
            sql += " and a.couponId LIKE '%" + cdk.getCouponId() + "%'";
        }
        if (cdk.getCdk() != "") {
            sql += " and a.cdk LIKE '%" + cdk.getCdk() + "%'";
        }
        sql += " order by id desc ";
        logger.debug("sql：" + sql);
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

    public int exchangeCDK(int couponId, int sequenceId, int platformId, String cdk, String gameId) {
        String sql =
                "insert into t_cdk (gameId,couponId,sequenceId,platformId,cdk,isUsed) values ('" + gameId + "','" + couponId + "','" + sequenceId +
                        "','" + platformId + "','" + cdk + "',1)";
        int temp = jdbcTemplate.update(sql);
        return temp;
    }


    public List<Map<String, Object>> checkIsUsedCDK(int couponId, int sequenceId, String cdk, int platformId, String gameId) {
        String sql = "select * from t_cdk where gameId = '" + gameId + "' and couponId = '" + couponId + "' and sequenceId = '" + sequenceId +
                "' and cdk = '" + cdk + "' and platformId = '" + platformId + "' ";
        logger.debug(sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    public List<Map<String, Object>> checkCDK(int couponId, int sequenceId, String cdk, int platformId, String gameId) {
        String sql = "select * from t_coupon where gameId = '" + gameId + "' and couponId = '" + couponId + "' and end_sequence >= '" + sequenceId +
                "' and start_sequence <='" + sequenceId + "' and  platformId = '" + platformId + "' ";
        logger.debug(sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        if (Objects.equals(list.size(), 0)) {
            return null;
        }
        String strSalt = list.get(0).get("salt").toString();
        int salt = Integer.parseInt(strSalt);

        String s = couponDaoImpl.generate(couponId, sequenceId, salt);
        logger.debug("Origin" + cdk);
        logger.debug("generate" + s);
        if (Objects.equals(cdk, s)) {
            return list;
        } else {
            return null;
        }
    }

    public List<Map<String, Object>> checkCDKByServerId(Map map, String cdk, int serverId) {
        String sql = "select * from t_cdk as a join t_gameserver as b on a.platformId = b.platformId where a.couponId = '" + map.get("couponId") +
                "' and a.sequenceId = '" + map.get("sequenceId") + "' and a.cdk = '" + cdk + "' and b.Id = " + serverId;
        logger.debug(sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }
}
