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

@Repository
public class SendNoticeDaoImpl {

    public static final String Divider = "############################";
    public static final String Split = "----------------";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> getSendNoticeSendType() {
        String sql = "select * from t_send_notice_sendtype";
        System.out.println("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        list = jdbcTemplate.queryForList(sql);
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        return JsonMap;
    }

    public Map<String, Object> getSendNoticeNoticeType() {
        String sql = "select * from t_send_notice_noticetype";
        System.out.println("sql：" + sql);
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
                "insert into t_send_notice (plarformId,serverList,sendType,noticeType,timeInterval,cycleTime,startDatetime,endDatetime,sendDatetime,noticeContent,sendState,addUser,addDatetime,isDelete) " +
                        " values ('" + notice.getPlatformId() + "','" + notice.getServerList() + "','" + notice.getSendType() + "','" +
                        notice.getNoticeType() + "','" + notice.getTimeInterval() + "','" + notice + "','','','','','','','')";
        System.out.println("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }
}
