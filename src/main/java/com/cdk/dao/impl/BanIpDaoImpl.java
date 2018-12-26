package com.cdk.dao.impl;

import com.cdk.entity.BanIp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class BanIpDaoImpl {
    private static Logger logger = LoggerFactory.getLogger(BanIpDaoImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> getBanIp(BanIp banIp, String isPage, int pageNo, int pageSize, String strPlatform) {
        String sql =
                "select a.* , b.platform ,c.server from t_ban_ip as a join  t_gameplatform as b on a.platformId = b.platformId join t_gameserver as c on a.serverId = c.serverId  where a.platformId IN (" +
                        strPlatform + ")  and b.isDelete != 1 and c.isDelete != 1 and a.isDelete != 1 ";
        if (banIp.getPlatformId() != 0) {
            sql += " and a.platformId ='" + banIp.getPlatformId() + "' ";
        }
        if (banIp.getServerId() != 0) {
            sql += " and a.serverId ='" + banIp.getServerId() + "' ";
        }
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

    public int addBanIp(BanIp banIp) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        String sql = "insert t_ban_ip (platformId,serverId,ip,banLong,note,addUser,isDelete,addDatetime,banState) values ('" + banIp.getPlatformId() +
                "','" + banIp.getServerId() + "','" + banIp.getIp() + "','" + banIp.getBanLong() + "','" + banIp.getNote() + "','" +
                banIp.getAddUser() + "','0','" + addDatetime + "',0)";
        logger.debug(sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int changeBanState(int id, int banState) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        String sql = "";
        if (banState == 1) {
            sql = "update t_ban_ip set banState = '" + banState + "',banDatetime = '" + addDatetime + "'  where id = " + id;
        } else {
            sql = "update t_ban_ip set banState = '" + banState + "' where id = " + id;
        }
        logger.debug(sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int deleteBanIp(int id) {
        String sql = "update t_ban_ip set isDelete = '1' where id = " + id;
        logger.debug(sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int[] deleteAllBanIp(String[] idList) {
        String strSql = "";
        String sql[] = new String[idList.length];
        int[] temp = new int[idList.length];
        for (int i = 0; i < idList.length; i++) {
            sql[i] = "UPDATE  t_ban_ip  set isDelete='1' where id = '" + idList[i] + "';";
            strSql += sql;
        }
        logger.debug("sql：" + strSql);
        temp = jdbcTemplate.batchUpdate(sql);
        return temp;
    }
}
