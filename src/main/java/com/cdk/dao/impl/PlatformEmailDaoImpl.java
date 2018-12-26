package com.cdk.dao.impl;

import com.cdk.entity.PlatformEmail;

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
public class PlatformEmailDaoImpl {
    private static Logger logger = Logger.getLogger(String.valueOf(PlatformEmailDaoImpl.class));

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> getPlatformEmail(PlatformEmail platformEmail, String isPage, int pageNo, int pageSize, String strPlatform) {
        String sql =
                "select a.*,b.platform,c.name as userName from t_platform_email as a  join  t_gameplatform as b on a.platformId = b.platformId join t_user as c on c.id = a.addUser where a.platformId in (" +
                        strPlatform + ") and a.isDelete != 1  and b.isDelete != 1 ";
        if (!Objects.equals(platformEmail.getPlatformId(), 0)) {
            sql += " and a.platformId ='" + platformEmail.getPlatformId() + "' ";
        }
        if (!Objects.equals(platformEmail.getServerList(), "") && !Objects.equals(platformEmail.getServerList(), "0")) {
            sql += " and a.serverList LIKE '%" + platformEmail.getServerList() + "%' ";
        }
        if (!Objects.equals(platformEmail.getEmailContent(), "")) {
            sql += " and a.emailContent LIKE '%" + platformEmail.getEmailContent() + "%' ";
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

    public int editPlatformEmail(PlatformEmail platformEmail) {
        String startDatetime = "null";
        String endDatetime = "null";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (!Objects.equals(platformEmail.getStartDatetime(), null)) {
            startDatetime = "'" + formatter.format(platformEmail.getStartDatetime()) + "'";
        }
        logger.info("startDatetime:" + startDatetime);
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (!Objects.equals(platformEmail.getEndDatetime(), null)) {
            endDatetime = "'" + formatter.format(platformEmail.getEndDatetime()) + "'";
        }
        logger.info("endDatetime:" + endDatetime);

        String sql =
                "update t_platform_email SET platformId = '" + platformEmail.getPlatformId() + "',serverList = '" + platformEmail.getServerList() +
                        "',emailTitle = '" + platformEmail.getEmailTitle() + "',emailContent ='" + platformEmail.getEmailContent() +
                        "',sendReason ='" + platformEmail.getSendReason() + "',startDatetime=" + startDatetime + ",endDatetime=" + endDatetime +
                        "  where id  = '" + platformEmail.getId() + "'";
        logger.info(sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int addPlatformEmail(PlatformEmail platformEmail) {
        String startDatetime = "null";
        String endDatetime = "null";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (!Objects.equals(platformEmail.getStartDatetime(), null)) {
            startDatetime = "'" + formatter.format(platformEmail.getStartDatetime()) + "'";
        }
        logger.info("startDatetime:" + startDatetime);
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (!Objects.equals(platformEmail.getEndDatetime(), null)) {
            endDatetime = "'" + formatter.format(platformEmail.getEndDatetime()) + "'";
        }
        logger.info("endDatetime:" + endDatetime);
        String sql =
                "insert into t_platform_email (platformId,serverList,emailTitle,emailContent,sendReason,startDatetime,endDatetime,sendState,addUser,addDatetime,isDelete) " +
                        " values ('" + platformEmail.getPlatformId() + "','" + platformEmail.getServerList() + "','" + platformEmail.getEmailTitle() +
                        "','" + platformEmail.getEmailContent() + "','" + platformEmail.getSendReason() + "'," + startDatetime + "," + endDatetime +
                        ",'0','" + platformEmail.getAddUser() + "','" + addDatetime + "','0')";
        logger.info("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int deletePlatformEmail(PlatformEmail platformEmail) {
        String sql = "UPDATE  t_platform_email  set isDelete = 1 where id = '" + platformEmail.getId() + "'";
        logger.info("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int sendPlatformEmail(PlatformEmail platformEmail, int state, String error, long time) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(time * 1000);// new Date()为获取当前系统时间，也可使用当前时间戳
        String sql = "UPDATE  t_platform_email  set sendState = '" + state + "',errorList = '" + error + "' ,startDatetime='" + addDatetime +
                "' where id = '" + platformEmail.getId() + "'";
        logger.info("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int[] deleteAllPlatformEmail(String[] idList) {
        String strSql = "";
        String sql[] = new String[idList.length];
        int[] temp = new int[idList.length];

        for (int i = 0; i < idList.length; i++) {
            sql[i] = "UPDATE  t_platform_email  set isDelete='1' where id = '" + idList[i] + "';";
            strSql += sql;
        }
        logger.info("sql：" + strSql);
        temp = jdbcTemplate.batchUpdate(sql);
        return temp;
    }
}
