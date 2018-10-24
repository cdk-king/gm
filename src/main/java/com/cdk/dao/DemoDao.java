package com.cdk.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


import com.cdk.entity.Demo;

public interface DemoDao extends JpaRepository<Demo, Long>, DemoInterface{



}

