package com.cdk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdk.dao.DemoDao;
import com.cdk.entity.Demo;
import com.cdk.service.DemoService;

@Service
public class DemoServiceImpl  implements DemoService{
    @Autowired
    private DemoDao demoDao;

    public void save(Demo demo){

        demoDao.save(demo);
    }
}
