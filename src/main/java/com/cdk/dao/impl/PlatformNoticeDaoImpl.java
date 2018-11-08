package com.cdk.dao.impl;

import com.cdk.entity.PlatformNotice;

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
public class PlatformNoticeDaoImpl {

    public static final String Divider = "############################";
    public static final String Split = "----------------";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> getPlatformNotice(PlatformNotice platformNotice, String isPage, int pageNo, int pageSize) {
        String sql =
                "select a.*,b.platform,c.name as userName from t_platform_notice as a  join  t_gameplatform as b on a.platformId = b.id join t_user as c on c.id = a.addUser where a.isDelete != 1  and b.isDelete != 1 ";
        if (!Objects.equals(platformNotice.getPlatformId(), 0)) {
            sql += " and a.platformId ='" + platformNotice.getPlatformId() + "' ";
        }
        if (!Objects.equals(platformNotice.getServerList(), "") && !Objects.equals(platformNotice.getServerList(), "0")) {
            sql += " and a.serverList LIKE '%" + platformNotice.getServerList() + "%' ";
        }
        if (!Objects.equals(platformNotice.getNoticeContent(), "")) {
            sql += " and a.noticeContent LIKE '%" + platformNotice.getNoticeContent() + "%' ";
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


    public int addPlatformNotice(PlatformNotice platformNotice) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startDatetime = formatter.format(platformNotice.getStartDatetime());
        System.out.println("startDatetime:" + startDatetime);
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String endDatetime = formatter.format(platformNotice.getEndDatetime());
        System.out.println("endDatetime:" + endDatetime);
        String sql =
                "insert into t_platform_notice (platformId,serverList,noticeTitle,noticeContent,startDatetime,endDatetime,sendState,addUser,addDatetime,isDelete) " +
                        " values ('" + platformNotice.getPlatformId() + "','" + platformNotice.getServerList() + "','" +
                        platformNotice.getNoticeTitle() + "','" + platformNotice.getNoticeContent() + "','" + startDatetime + "','" + endDatetime +
                        "','0','" + platformNotice.getAddUser() + "','" + addDatetime + "','0')";
        System.out.println("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int editPlatformNotice(PlatformNotice platformNotice) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startDatetime = formatter.format(platformNotice.getStartDatetime());
        System.out.println("startDatetime:" + startDatetime);
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String endDatetime = formatter.format(platformNotice.getEndDatetime());
        System.out.println("endDatetime:" + endDatetime);

        String sql =
                "UPDATE  t_platform_notice  set platformId='" + platformNotice.getPlatformId() + "',serverList='" + platformNotice.getServerList() +
                        "',noticeTitle='" + platformNotice.getNoticeTitle() + "',noticeContent='" + platformNotice.getNoticeContent() +
                        "',startDatetime='" + startDatetime + "',endDatetime='" + endDatetime + "',addUser='" + platformNotice.getAddUser() +
                        "' where id = '" + platformNotice.getId() + "'";
        System.out.println("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int deletePlatformNotice(PlatformNotice platformNotice) {
        String sql = "UPDATE  t_platform_notice  set isDelete = 1 where id = '" + platformNotice.getId() + "'";
        System.out.println("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int sendPlatformNotice(PlatformNotice platformNotice) {
        String sql = "UPDATE  t_platform_notice  set sendState = 1 where id = '" + platformNotice.getId() + "'";
        System.out.println("sql：" + sql);
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
        System.out.println("sql：" + strSql);
        temp = jdbcTemplate.batchUpdate(sql);
        return temp;
    }
}
