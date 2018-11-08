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

@Repository
public class EmailDaoImpl {
    public static final String Divider = "############################";
    public static final String Split = "----------------";

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public Map<String, Object> getEmail(Email email, String isPage, int pageNo, int pageSize) {
        String sql =
                "select a.*,b.platform,c.name as userName,d.server from t_send_email as a  join  t_gameplatform as b on a.platformId = b.id join t_user as c on c.id = a.addUser join t_gameserver as d on d.id = a.serverId where a.isDelete != 1  and b.isDelete != 1 and c.isDelete != 1 and d.isDelete !=1 ";
        if (!Objects.equals(email.getPlatformId(), 0)) {
            sql += " and a.platformId ='" + email.getPlatformId() + "' ";
        }
        if (!Objects.equals(email.getServerId(), 0)) {
            sql += " and a.serverId LIKE '%" + email.getServerId() + "%' ";
        }
        if (!Objects.equals(email.getEmailContent(), "")) {
            sql += " and a.emailContent LIKE '%" + email.getEmailContent() + "%' ";
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

    public int addEmail(Email email) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        System.out.println(email.getMinRegistrationTime());
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
        System.out.println("minRegistrationTime:" + minRegistrationTime);
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (!Objects.equals(email.getMaxRegistrationTime(), null)) {
            maxRegistrationTime = formatter.format(email.getMaxRegistrationTime());
            sql += "'" + maxRegistrationTime + "', ";
        } else {
            sql += "null,";
        }
        System.out.println("maxRegistrationTime:" + maxRegistrationTime);

        sql += "'" + email.getIsOnline() + "','" + email.getSex() + "','" + email.getPlayerNameList() + "','" + email.getPlayerAccountList() + "','" +
                email.getPlayerIdList() + "','" + email.getAddUser() + "','" + addDatetime + "','0','0')";
        System.out.println("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }
}
