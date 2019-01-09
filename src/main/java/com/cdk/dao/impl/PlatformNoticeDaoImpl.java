package com.cdk.dao.impl;

import com.cdk.entity.PlatformNotice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class PlatformNoticeDaoImpl {
    private static Logger logger = LoggerFactory.getLogger(PlatformNoticeDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> getPlatformNotice(PlatformNotice platformNotice, String isPage, int pageNo, int pageSize, String strPlatform,
            String gameId) {
        String sql =
                "select a.*,b.platform,c.name as userName from t_platform_notice as a  join  t_gameplatform as b on a.platformId = b.platformId join t_user as c on c.id = a.addUser join t_game as d on d.id = b.gameId and d.isDelete!=1 where d.id='" +
                        gameId + "' and a.gameId = '" + gameId + "' and a.platformId in (" + strPlatform +
                        ") and a.isDelete != 1  and b.isDelete != 1 ";
        if (!Objects.equals(platformNotice.getPlatformId(), 0)) {
            sql += " and a.platformId ='" + platformNotice.getPlatformId() + "' ";
        }
        if (!Objects.equals(platformNotice.getServerList(), "") && !Objects.equals(platformNotice.getServerList(), "0")) {
            sql += " and a.serverList LIKE '%" + platformNotice.getServerList() + "%' ";
        }
        if (!Objects.equals(platformNotice.getNoticeContent(), "")) {
            sql += " and a.noticeContent LIKE '%" + platformNotice.getNoticeContent() + "%' ";
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


    public int addPlatformNotice(PlatformNotice platformNotice) {
        String startDatetime = "null";
        String endDatetime = "null";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (!Objects.equals(platformNotice.getStartDatetime(), null)) {
            startDatetime = "'" + formatter.format(platformNotice.getStartDatetime()) + "'";
        }
        logger.debug("startDatetime:" + startDatetime);
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (!Objects.equals(platformNotice.getEndDatetime(), null)) {
            endDatetime = "'" + formatter.format(platformNotice.getEndDatetime()) + "'";
        }
        logger.debug("endDatetime:" + endDatetime);
        String sql =
                "insert into t_platform_notice (gameId,platformId,serverList,noticeTitle,noticeContent,startDatetime,endDatetime,sendState,addUser,addDatetime,isDelete,moneyList,propList) " +
                        " values ('" + platformNotice.getGameId() + "','" + platformNotice.getPlatformId() + "','" + platformNotice.getServerList() +
                        "','" + platformNotice.getNoticeTitle() + "','" + platformNotice.getNoticeContent() + "'," + startDatetime + "," +
                        endDatetime + ",'0','" + platformNotice.getAddUser() + "','" + addDatetime + "','0','" + platformNotice.getMoneyList() +
                        "','" + platformNotice.getPropList() + "')";
        logger.debug("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int editPlatformNotice(PlatformNotice platformNotice) {
        String startDatetime = "null";
        String endDatetime = "null";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (!Objects.equals(platformNotice.getStartDatetime(), null)) {
            startDatetime = "'" + formatter.format(platformNotice.getStartDatetime()) + "'";
        }
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (!Objects.equals(platformNotice.getEndDatetime(), null)) {
            endDatetime = "'" + formatter.format(platformNotice.getEndDatetime()) + "'";
        }

        String sql = "UPDATE  t_platform_notice  set gameId='" + platformNotice.getGameId() + "', platformId='" + platformNotice.getPlatformId() +
                "',serverList='" + platformNotice.getServerList() + "',noticeTitle='" + platformNotice.getNoticeTitle() + "',noticeContent='" +
                platformNotice.getNoticeContent() + "',startDatetime=" + startDatetime + ",endDatetime=" + endDatetime + ",addUser='" +
                platformNotice.getAddUser() + "', moneyList = '" + platformNotice.getMoneyList() + "' , propList = '" + platformNotice.getPropList() +
                "'  where id = '" + platformNotice.getId() + "'";
        logger.debug("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int deletePlatformNotice(PlatformNotice platformNotice) {
        String sql = "UPDATE  t_platform_notice  set isDelete = 1 where id = '" + platformNotice.getId() + "'";
        logger.debug("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int sendPlatformNotice(PlatformNotice platformNotice) {
        String sql = "UPDATE  t_platform_notice  set sendState = 1 where id = '" + platformNotice.getId() + "'";
        logger.debug("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int[] deleteAllPlatformNotice(String[] idList) {
        String strSql = "";
        String sql[] = new String[idList.length];
        int[] temp = new int[idList.length];

        for (int i = 0; i < idList.length; i++) {
            sql[i] = "UPDATE  t_platform_notice  set isDelete='1' where id = '" + idList[i] + "';";
            strSql += sql;
        }
        logger.debug("sql：" + strSql);
        temp = jdbcTemplate.batchUpdate(sql);
        return temp;
    }

    /**
     * 获取最新的公告
     * @param strPlatform
     * @return
     */
    public Map<String, String> getLastNotice(String strPlatform) {
        String sql = "select * from t_platform_notice where isDelete!=1 and  sendState = 1 and  platformId=" + strPlatform +
                " order by startDatetime DESC LIMIT 1";
        logger.debug("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        Map<String, String> JsonMap = new HashMap();
        if (list.size() > 0) {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                date = simpleDateFormat.parse(list.get(0).get("startDatetime").toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            JsonMap.put("startDatetime", String.valueOf(date.getTime() / 1000L));
            JsonMap.put("noticeTitle", list.get(0).get("noticeTitle").toString());
            JsonMap.put("noticeContent", list.get(0).get("noticeContent").toString());
            JsonMap.put("propList", list.get(0).get("propList").toString());
            JsonMap.put("moneyList", list.get(0).get("moneyList").toString());
        }
        return JsonMap;
    }

    public int changeNoticeSendState(String id, String error, int state, long time) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(time * 1000);// new Date()为获取当前系统时间，也可使用当前时间戳
        String sql =
                "update t_platform_notice set sendState = " + state + ",errorList = '" + error + "',startDatetime='" + addDatetime + "' where id = " +
                        id;
        logger.debug("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }
}
