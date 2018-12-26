package com.cdk.service.impl;

import com.cdk.dao.impl.DataSourceDaoImpl;
import com.cdk.entity.DataSource;
import com.cdk.result.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

@Service
public class DataSourceServiceImpl {
    private static Logger logger = Logger.getLogger(String.valueOf(DataSourceServiceImpl.class));

    @Autowired
    private DataSourceDaoImpl dataSourceDaoImpl;

    public Result getDataSource(Map map) {
        String platformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String id = ((map.get("id") != null && map.get("id") != "") ? map.get("id").toString() : "0");
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

        Map<String, Object> JsonMap = dataSourceDaoImpl.getDataSource(dataSource, isPage, pageNo, pageSize);
        if (Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(200, "通道列表为空", "");
        } else {
            re = new Result(200, "通道列表获取成功", JsonMap);
        }
        return re;
    }

    public Result addDataSource(Map map) {
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
            logger.info("无任何批量删除操作");
            return new Result(200, "无任何批量删除操作", null);
        }
        String[] ObjectArry = id.split(",");
        Result re;
        int[] temp = dataSourceDaoImpl.deleteAllDataSource(ObjectArry);

        if (temp.length != 0) {
            logger.info("数据源批量删除成功");
            re = new Result(200, "数据源批量删除成功", null);
        } else if (ObjectArry.length == 0) {
            logger.info("无任何删除操作");
            re = new Result(200, "无任何删除操作", null);
        } else {
            logger.info("数据源批量删除失败");
            re = new Result(400, "数据源批量删除失败", null);
        }
        return re;
    }

}
