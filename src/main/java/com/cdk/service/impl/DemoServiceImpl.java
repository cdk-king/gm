package com.cdk.service.impl;

import com.cdk.dao.DemoDao;
import com.cdk.entity.Demo;
import com.cdk.service.DemoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class DemoServiceImpl implements DemoService {
    private static Logger logger = Logger.getLogger(String.valueOf(DemoServiceImpl.class));
    @Autowired
    private DemoDao demoDao;

    public void save(Demo demo) {

        demoDao.save(demo);
    }
}
