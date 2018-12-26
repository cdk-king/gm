package com.cdk.dao.impl;

import com.cdk.entity.Channel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Repository
public class ChannelDaoImpl {
    private static Logger logger = Logger.getLogger(String.valueOf(ChannelDaoImpl.class));

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> getAllChannelFormPlatform(String platformId) {
        String sql =
                "select a.* , b.platform  from t_platform_channel as a join  t_gameplatform as b on a.platformId = b.platformId where a.platformId ='" +
                        platformId + "'  and a.isDelete != 1 and b.isDelete != 1  ";

        logger.info("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        int total = list.size();
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        JsonMap.put("total", total);
        return JsonMap;
    }

    public Map<String, Object> getAllChannel() {
        String sql =
                "select a.* , b.platform  from t_platform_channel as a join  t_gameplatform as b on a.platformId = b.platformId where a.isDelete != 1 and b.isDelete != 1  ";

        logger.info("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        int total = list.size();
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        JsonMap.put("total", total);
        return JsonMap;
    }

    public Map<String, Object> getChannel(String id) {
        String sql = "select a.* , b.platform  from t_gameserver as a join  t_gameplatform as b on a.platformId = b.platformId where a.id ='" + id +
                "'  and b.isDelete != 1  ";

        logger.info("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        int total = list.size();
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        JsonMap.put("total", total);
        return JsonMap;
    }

    public Map<String, Object> getChannelTable(Channel channel, String isPage, int pageNo, int pageSize) {
        String sql =
                "select a.* , b.platform  from t_platform_channel as a join  t_gameplatform as b on a.platformId = b.platformId where a.isDelete != 1 and b.isDelete != 1  ";
        if (channel.getChannelName() != "") {
            sql += " and a.channelName LIKE '%" + channel.getChannelName() + "%'";
        }
        if (channel.getChannelTag() != "") {
            sql += " and a.channelTag LIKE '%" + channel.getChannelTag() + "%'";
        }
        if (channel.getPlatformId() != 0) {
            sql += " and a.platformId = '" + channel.getPlatformId() + "'";
        }
        List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql);
        int total = list.size();
        sql += " limit " + (pageNo - 1) * pageSize + ", " + pageSize;
        logger.info("sql：" + sql);
        list = jdbcTemplate.queryForList(sql);
        Map<String, Object> JsonMap = new HashMap();
        if (list.size() != 0) {
            JsonMap.put("list", list);
        } else {
            JsonMap.put("list", 0);
        }
        JsonMap.put("total", total);

        return JsonMap;
    }

    public int saveCheckChannel(String id, String channel) {
        String sql = "update  t_gameserver  set channel = '" + channel + "' where id = '" + id + "'";
        logger.info("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }


    public int deleteChannel(String id) {
        String sql = "update  t_platform_channel  set isDelete = '1' where id = '" + id + "'";
        logger.info("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int addChannel(Channel channel) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String addDatetime = df.format(new Date());
        String sql =
                "insert into  t_platform_channel (platformId,channelId,channelName,channelTag,addUser,addDatetime,channel_describe,isDelete) values ('" +
                        channel.getPlatformId() + "','" + channel.getChannelId() + "','" + channel.getChannelName() + "','" +
                        channel.getChannelTag() + "','" + channel.getAddUser() + "','" + addDatetime + "','" + channel.getChannel_describe() +
                        "','0');";
        logger.info("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int editChannel(Channel channel) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String addDatetime = df.format(new Date());
        String sql = "update t_platform_channel set platformId = '" + channel.getPlatformId() + "',channelId='" + channel.getChannelId() +
                "',channelName='" + channel.getChannelName() + "',channelTag='" + channel.getChannelTag() + "',addUser='" + channel.getAddUser() +
                "',addDatetime='" + addDatetime + "',channel_describe='" + channel.getChannel_describe() + "',isDelete='0' where id = '" +
                channel.getId() + "'";
        logger.info("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }
}