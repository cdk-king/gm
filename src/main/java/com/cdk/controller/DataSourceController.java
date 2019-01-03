package com.cdk.controller;

import com.cdk.result.Result;
import com.cdk.service.impl.DataSourceServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DataSourceController {
    private static Logger logger = LoggerFactory.getLogger(DataSourceController.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DataSourceServiceImpl dataSourceServiceImpl;

    @CrossOrigin
    @RequestMapping("/api/db/getDataSource")
    public Result getDataSource(@RequestBody Map map) {
        Result re = dataSourceServiceImpl.getDataSource(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/db/addDataSource")
    public Result addDataSource(@RequestBody Map map) {
        Result re = dataSourceServiceImpl.addDataSource(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/db/editDataSource")
    public Result editDataSource(@RequestBody Map map) {
        Result re = dataSourceServiceImpl.editDataSource(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/db/deleteDataSource")
    public Result deleteDataSource(@RequestBody Map map) {
        Result re = dataSourceServiceImpl.deleteDataSource(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/db/deleteAllDataSource")
    public Result deleteAllDataSource(@RequestBody Map map) {
        Result re = dataSourceServiceImpl.deleteAllDataSource(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/db/testDataSource")
    public Result testDataSource(@RequestBody Map map) {
        Result re = dataSourceServiceImpl.testDataSource(map);
        return re;
    }
}
