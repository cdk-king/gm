package com.cdk.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@ComponentScan(basePackages = "com.cdk")// 扫描该包路径下的所有spring组件
@EnableJpaRepositories("com.cdk.dao") // JPA扫描该包路径下的Repositorie
@EntityScan("com.cdk.entity") // 扫描实体类
public class hello {
    public static void main(String[] args){
        SpringApplication.run(hello.class,args);
    }
}
