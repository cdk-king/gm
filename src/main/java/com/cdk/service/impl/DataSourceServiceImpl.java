package com.cdk.service.impl;

import com.cdk.dao.impl.DataSourceDaoImpl;
import com.cdk.dao.impl.UtilsDaoImpl;
import com.cdk.entity.DataSource;
import com.cdk.result.Result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class DataSourceServiceImpl {
    private static Logger logger = LoggerFactory.getLogger(DataSourceServiceImpl.class);

    @Autowired
    private DataSourceDaoImpl dataSourceDaoImpl;
    @Autowired
    private UtilsDaoImpl utilsDaoImpl;

    public Result getDataSource(Map map) {
        String gameId = ((map.get("gameId") != null && map.get("gameId") != "") ? map.get("gameId").toString() : "0");
        String platformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String dataSource_url = (map.get("dataSource_url") != null ? map.get("dataSource_url").toString() : "");
        String isPage = (map.get("isPage") != null ? map.get("isPage").toString() : "");
        String StrPageNo = (map.get("pageNo") != null ? map.get("pageNo").toString() : "1");
        String StrPageSize = (map.get("pageSize") != null ? map.get("pageSize").toString() : "5");
        Result re;
        int pageNo = 1;
        int pageSize = 5;
        try {
            pageNo = Integer.parseInt(StrPageNo);
            pageSize = Integer.parseInt(StrPageSize);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        DataSource dataSource = new DataSource();
        dataSource.setPlatformId(Integer.parseInt(platformId));
        dataSource.setDataSource_url(dataSource_url);

        Map<String, Object> JsonMap = dataSourceDaoImpl.getDataSource(gameId, dataSource, isPage, pageNo, pageSize);
        if (Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(200, "通道列表为空", "");
        } else {
            re = new Result(200, "通道列表获取成功", JsonMap);
        }
        return re;
    }

    public Result addDataSource(Map map) {
        String gameId = ((map.get("gameId") != null && map.get("gameId") != "") ? map.get("gameId").toString() : "0");
        String platformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String id = ((map.get("id") != null && map.get("id") != "") ? map.get("id").toString() : "0");
        String addUser = ((map.get("addUser") != null && map.get("addUser") != "") ? map.get("addUser").toString() : "");
        String dataSource_id = ((map.get("dataSource_id") != null && map.get("dataSource_id") != "") ? map.get("dataSource_id").toString() : "");
        String dataSource_url = ((map.get("dataSource_url") != null && map.get("dataSource_url") != "") ? map.get("dataSource_url").toString() : "");
        String dataSource_name =
                ((map.get("dataSource_name") != null && map.get("dataSource_name") != "") ? map.get("dataSource_name").toString() : "");
        String dataSource_password =
                ((map.get("dataSource_password") != null && map.get("dataSource_password") != "") ? map.get("dataSource_password").toString() : "");

        DataSource dataSource = new DataSource();
        dataSource.setGameId(Integer.parseInt(gameId));
        dataSource.setPlatformId(Integer.parseInt(platformId));
        dataSource.setDataSource_id(Integer.parseInt(dataSource_id));
        dataSource.setDataSource_url(dataSource_url);
        dataSource.setDataSource_name(dataSource_name);
        dataSource.setDataSource_password(dataSource_password);
        dataSource.setAddUser(addUser);

        Result re;
        int temp = dataSourceDaoImpl.addDataSource(dataSource);
        if (temp > 0) {
            re = new Result(200, "数据源添加成功", temp);
        } else {
            re = new Result(400, "数据源添加失败", temp);
        }
        return re;
    }

    public Result editDataSource(Map map) {
        String gameId = ((map.get("gameId") != null && map.get("gameId") != "") ? map.get("gameId").toString() : "0");
        String platformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String id = ((map.get("id") != null && map.get("id") != "") ? map.get("id").toString() : "0");
        String addUser = ((map.get("addUser") != null && map.get("addUser") != "") ? map.get("addUser").toString() : "");
        String dataSource_id = ((map.get("dataSource_id") != null && map.get("dataSource_id") != "") ? map.get("dataSource_id").toString() : "");
        String dataSource_url = ((map.get("dataSource_url") != null && map.get("dataSource_url") != "") ? map.get("dataSource_url").toString() : "");
        String dataSource_name =
                ((map.get("dataSource_name") != null && map.get("dataSource_name") != "") ? map.get("dataSource_name").toString() : "");
        String dataSource_password =
                ((map.get("dataSource_password") != null && map.get("dataSource_password") != "") ? map.get("dataSource_password").toString() : "");

        DataSource dataSource = new DataSource();
        dataSource.setGameId(Integer.parseInt(gameId));
        dataSource.setId(Integer.parseInt(id));
        dataSource.setPlatformId(Integer.parseInt(platformId));
        dataSource.setDataSource_id(Integer.parseInt(dataSource_id));
        dataSource.setDataSource_url(dataSource_url);
        dataSource.setDataSource_name(dataSource_name);
        dataSource.setDataSource_password(dataSource_password);
        dataSource.setAddUser(addUser);

        Result re;
        int temp = dataSourceDaoImpl.editDataSource(dataSource);
        if (temp > 0) {
            re = new Result(200, "数据源添加成功", temp);
        } else {
            re = new Result(400, "数据源添加失败", temp);
        }
        return re;
    }


    public Result deleteDataSource(Map map) {
        String id = ((map.get("id") != null && map.get("id") != "") ? map.get("id").toString() : "0");
        Result re;
        int temp = dataSourceDaoImpl.deleteDataSource(id);
        if (temp > 0) {
            re = new Result(200, "数据源删除成功", temp);
        } else {
            re = new Result(400, "数据源删除失败", temp);
        }
        return re;
    }


    public Result deleteAllDataSource(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        if (Objects.equals(id, "")) {
            logger.debug("无任何批量删除操作");
            return new Result(200, "无任何批量删除操作", null);
        }
        String[] ObjectArry = id.split(",");
        Result re;
        int[] temp = dataSourceDaoImpl.deleteAllDataSource(ObjectArry);

        if (temp.length != 0) {
            logger.debug("数据源批量删除成功");
            re = new Result(200, "数据源批量删除成功", null);
        } else if (ObjectArry.length == 0) {
            logger.debug("无任何删除操作");
            re = new Result(200, "无任何删除操作", null);
        } else {
            logger.debug("数据源批量删除失败");
            re = new Result(400, "数据源批量删除失败", null);
        }
        return re;
    }

    public Result testDataSource(Map map) {
        String gameId = ((map.get("gameId") != null && map.get("gameId") != "") ? map.get("gameId").toString() : "0");
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");

        List<Map<String, Object>> dblist = utilsDaoImpl.getDataSourceForPlatformId(Integer.parseInt(strPlatformId), gameId);

        if (dblist.size() == 0) {
            return new Result(400, "数据源获取失败", "");
        }

        String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        String DB_URL = dblist.get(0).get("dataSource_url").toString();
        String USER = dblist.get(0).get("dataSource_name").toString();
        String PASS = dblist.get(0).get("dataSource_password").toString();
        Connection conn = null;
        Statement stmt = null;
        Result re;
        try {
            Class.forName(JDBC_DRIVER);
            DriverManager.setLoginTimeout(10);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            re = new Result(400, "数据源测试失败", "");
            return re;
        } catch (SQLException e) {
            e.printStackTrace();
            re = new Result(400, "数据源测试失败", "");
            return re;
        }

        logger.debug("数据源测试成功");
        re = new Result(200, "数据源测试成功", "");
        return re;
    }
}
