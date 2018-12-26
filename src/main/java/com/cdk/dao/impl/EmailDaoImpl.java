package com.cdk.dao.impl;

import com.cdk.entity.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

@Repository
public class EmailDaoImpl {
    private static Logger logger = Logger.getLogger(String.valueOf(EmailDaoImpl.class));

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> getEmail(Email email, String isPage, int pageNo, int pageSize, String strPlatform) {
        String sql =
                "select a.*,b.platform,c.name as userName,d.server from t_send_email as a  join  t_gameplatform as b on a.platformId = b.platformId join t_user as c on c.id = a.addUser join t_gameserver as d on d.serverId = a.serverId where a.platformId IN (" +
                        strPlatform + ") and a.isDelete != 1  and b.isDelete != 1 and c.isDelete != 1 and d.isDelete !=1 ";
        if (!Objects.equals(email.getPlatformId(), 0)) {
            sql += " and a.platformId ='" + email.getPlatformId() + "' ";
        }
        if (!Objects.equals(email.getServerId(), 0)) {
            sql += " and a.serverId LIKE '%" + email.getServerId() + "%' ";
        }
        if (!Objects.equals(email.getEmailContent(), "")) {
            sql += " and a.emailContent LIKE '%" + email.getEmailContent() + "%' ";
        }
        logger.info("sql：" + sql);
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

    public int addEmail(Email email) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        logger.info(email.getMinRegistrationTime() + "");
        String minRegistrationTime = "";
        String maxRegistrationTime = "";

        String sql =
                "insert into t_send_email (platformId,serverId,emailTitle,emailContent,sendReason,sendType,minLevel,maxLevel,minVipLevel,maxVipLevel," +
                        "minRegistrationTime,maxRegistrationTime,isOnline,sex,playerNameList,playerAccountList,playerIdList,addUser,addDatetime,isDelete,sendState) " +
                        " values ('" + email.getPlatformId() + "','" + email.getServerId() + "','" + email.getEmailTitle() + "','" +
                        email.getEmailContent() + "','" + email.getSendReason() + "','" + email.getSendType() + "','" + email.getMinLevel() + "','" +
                        email.getMaxLevel() + "','" + email.getMinVipLevel() + "','" + email.getMaxVipLevel() + "',";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (!Objects.equals(email.getMinRegistrationTime(), null)) {
            minRegistrationTime = formatter.format(email.getMinRegistrationTime());
            sql += "'" + minRegistrationTime + "',";
        } else {
            sql += "null,";
        }
        logger.info("minRegistrationTime:" + minRegistrationTime);
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (!Objects.equals(email.getMaxRegistrationTime(), null)) {
            maxRegistrationTime = formatter.format(email.getMaxRegistrationTime());
            sql += "'" + maxRegistrationTime + "', ";
        } else {
            sql += "null,";
        }
        logger.info("maxRegistrationTime:" + maxRegistrationTime);

        sql += "'" + email.getIsOnline() + "','" + email.getSex() + "','" + email.getPlayerNameList() + "','" + email.getPlayerAccountList() + "','" +
                email.getPlayerIdList() + "','" + email.getAddUser() + "','" + addDatetime + "','0','0')";
        logger.info("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int editEmail(Email email) {
        logger.info(email.getMinRegistrationTime() + "");
        String minRegistrationTime = "";
        String maxRegistrationTime = "";


        String sql = "update t_send_email SET platformId='" + email.getPlatformId() + "',serverId='" + email.getServerId() + "',emailTitle='" +
                email.getEmailTitle() + "',emailContent='" + email.getEmailContent() + "',sendReason='" + email.getSendReason() + "',sendType='" +
                email.getSendType() + "',minLevel='" + email.getMinLevel() + "',maxLevel='" + email.getMaxLevel() + "',minVipLevel='" +
                email.getMinVipLevel() + "',maxVipLevel='" + email.getMaxVipLevel() + "',";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (!Objects.equals(email.getMinRegistrationTime(), null)) {
            minRegistrationTime = formatter.format(email.getMinRegistrationTime());
            sql += "minRegistrationTime='" + minRegistrationTime + "',";
        } else {

        }
        logger.info("minRegistrationTime:" + minRegistrationTime);
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (!Objects.equals(email.getMaxRegistrationTime(), null)) {
            maxRegistrationTime = formatter.format(email.getMaxRegistrationTime());
            sql += "maxRegistrationTime='" + maxRegistrationTime + "', ";
        } else {

        }
        logger.info("maxRegistrationTime:" + maxRegistrationTime);

        sql += "isOnline='" + email.getIsOnline() + "',sex='" + email.getSex() + "', playerNameList='" + email.getPlayerNameList() +
                "', playerAccountList='" + email.getPlayerAccountList() + "',playerIdList='" + email.getPlayerIdList() + "' where id = '" +
                email.getId() + "' ";
        logger.info("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int sendEmail(Email email, int state) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳

        String sql = "update t_send_email SET sendState='" + state + "',sendDatetime = '" + addDatetime + "' where id= '" + email.getId() + "'";

        logger.info("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int deleteEmail(Email email) {
        String sql = "update t_send_email SET isDelete='1' where id= '" + email.getId() + "'";

        logger.info("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }
}
