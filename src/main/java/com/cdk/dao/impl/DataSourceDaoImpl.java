package com.cdk.dao.impl;

import com.cdk.entity.DataSource;

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

@Repository
public class DataSourceDaoImpl {
    private static Logger logger = LoggerFactory.getLogger(DataSourceDaoImpl.class);


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> getDataSource(DataSource dataSource, String isPage, int pageNo, int pageSize) {
        String sql =
                "select a.* , b.platform  from t_datasource as a join  t_gameplatform as b on a.platformId = b.platformId where a.isDelete != 1 and b.isDelete != 1  ";
        if (dataSource.getPlatformId() != 0) {
            sql += " and a.platformId = '" + dataSource.getPlatformId() + "'";
        }
        List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql);
        int total = list.size();
        sql += " limit " + (pageNo - 1) * pageSize + ", " + pageSize;
        logger.debug("sql：" + sql);
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

    public int addDataSource(DataSource dataSource) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String addDatetime = df.format(new Date());
        String sql =
                "insert into  t_datasource (platformId,dataSource_id,dataSource_url,dataSource_name,dataSource_password,addDatetime,addUser,isDelete) values ('" +
                        dataSource.getPlatformId() + "','" + dataSource.getDataSource_id() + "','" + dataSource.getDataSource_url() + "','" +
                        dataSource.getDataSource_name() + "','" + dataSource.getDataSource_password() + "','" + addDatetime + "','" +
                        dataSource.getAddUser() + "','0');";
        logger.debug("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }


    public int editDataSource(DataSource dataSource) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String addDatetime = df.format(new Date());
        String sql = "update t_datasource set platformId = '" + dataSource.getPlatformId() + "',dataSource_id='" + dataSource.getDataSource_id() +
                "',dataSource_url='" + dataSource.getDataSource_url() + "',dataSource_name='" + dataSource.getDataSource_name() + "',addUser='" +
                dataSource.getAddUser() + "',addDatetime='" + addDatetime + "',dataSource_password='" + dataSource.getDataSource_password() +
                "',isDelete='0' where id = '" + dataSource.getId() + "'";
        logger.debug("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int deleteDataSource(String id) {
        String sql = "update  t_datasource  set isDelete = '1' where id = '" + id + "'";
        logger.debug("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int[] deleteAllDataSource(String[] list) {
        String sql[] = new String[list.length];
        String strSql = "";
        int[] temp = new int[list.length];
        for (int i = 0; i < list.length; i++) {
            sql[i] = "UPDATE  t_datasource  set isDelete='1' where id = '" + list[i] + "';";
            strSql += sql;
        }
        logger.debug("sql：" + strSql);
        temp = jdbcTemplate.batchUpdate(sql);
        return temp;
    }
}
