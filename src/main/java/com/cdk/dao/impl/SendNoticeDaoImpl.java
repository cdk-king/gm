package com.cdk.dao.impl;

import com.cdk.entity.Notice;

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
public class SendNoticeDaoImpl {
    private static Logger logger = Logger.getLogger(String.valueOf(SendNoticeDaoImpl.class));
    public static final String Divider = "############################";
    public static final String Split = "----------------";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> getSendNoticeSendType() {
        String sql = "select * from t_send_notice_sendtype";
        logger.info("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        list = jdbcTemplate.queryForList(sql);
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        return JsonMap;
    }

    public Map<String, Object> getSendNoticeNoticeType() {
        String sql = "select * from t_send_notice_noticetype";
        logger.info("sql：" + sql);
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
        logger.info("sql：" + sql);
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

    public int[] deleteAllNotice(String[] idList) {

        String strSql = "";
        String sql[] = new String[idList.length];
        int[] temp = new int[idList.length];

        for (int i = 0; i < idList.length; i++) {
            sql[i] = "UPDATE  t_send_notice  set isDelete='1' where id = '" + idList[i] + "';";
            strSql += sql;
            //jdbcTemplate.update(sql)只能运行一条语句，不可使用拼接
            //jdbcTemplate.batchUpdate可执行多条语句，同时还能规避执行过程中中断
            //这期间任一条SQL语句出现问题都会回滚[**]会所有语句没有执行前的最初状态
        }
        logger.info("sql：" + strSql);
        temp = jdbcTemplate.batchUpdate(sql);
        return temp;
    }

    public int sendNotice(String id) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        String sql = "UPDATE  t_send_notice  set sendState='1' , sendDatetime = '" + addDatetime + "' where id = '" + id + "' ";
        logger.info("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int sendNoticeToError(String id) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        String sql = "UPDATE  t_send_notice  set sendState='2'  where id = '" + id + "' ";
        logger.info("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }
}
