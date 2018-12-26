package com.cdk.dao.impl;

import com.cdk.entity.Notice;

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
public class SendNoticeDaoImpl {
    private static Logger logger = LoggerFactory.getLogger(SendNoticeDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> getSendNoticeSendType() {
        String sql = "select * from t_send_notice_sendtype";
        logger.debug("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        list = jdbcTemplate.queryForList(sql);
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        return JsonMap;
    }

    public Map<String, Object> getSendNoticeNoticeType() {
        String sql = "select * from t_send_notice_noticetype";
        logger.debug("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        list = jdbcTemplate.queryForList(sql);
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        return JsonMap;
    }

    public int addNotice(Notice notice) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        String sql =
                "insert into t_send_notice (platformId,serverList,sendType,noticeType,timeInterval,cycleTime,noticeContent,sendState,addUser,addDatetime,isDelete) " +
                        " values ('" + notice.getPlatformId() + "','" + notice.getServerList() + "','" + notice.getSendType() + "','" +
                        notice.getNoticeType() + "','" + notice.getTimeInterval() + "','" + notice.getCycleTime() + "','" +
                        notice.getNoticeContent() + "','0','" + notice.getAddUser() + "','" + addDatetime + "','0')";
        logger.debug("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public Map<String, Object> getNotice(Notice notice, String isPage, int pageNo, int pageSize, String strPlatform) {
        String sql =
                "select a.*,b.platform from t_send_notice as a  join  t_gameplatform as b on a.platformId = b.platformId  where a.platformId in (" +
                        strPlatform + ") and a.isDelete != 1  and b.isDelete != 1 ";
        if (!Objects.equals(notice.getPlatformId(), 0)) {
            sql += " and a.platformId ='" + notice.getPlatformId() + "' ";
        }
        if (!Objects.equals(notice.getServerList(), "") && !Objects.equals(notice.getServerList(), "0")) {
            sql += " and a.serverList LIKE '%" + notice.getServerList() + "%' ";
        }
        if (!Objects.equals(notice.getNoticeContent(), "")) {
            sql += " and a.noticeContent LIKE '%" + notice.getNoticeContent() + "%' ";
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

    public int[] deleteAllNotice(String[] idList) {
        String strSql = "";
        String sql[] = new String[idList.length];
        int[] temp = new int[idList.length];

        for (int i = 0; i < idList.length; i++) {
            sql[i] = "UPDATE  t_send_notice  set isDelete='1' where id = '" + idList[i] + "';";
            strSql += sql;
        }
        logger.debug("sql：" + strSql);
        temp = jdbcTemplate.batchUpdate(sql);
        return temp;
    }

    public int sendNotice(String id) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        String sql = "UPDATE  t_send_notice  set sendState='1' , sendDatetime = '" + addDatetime + "' where id = '" + id + "' ";
        logger.debug("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int sendNoticeToError(String id) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        String sql = "UPDATE  t_send_notice  set sendState='2'  where id = '" + id + "' ";
        logger.debug("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }
}
