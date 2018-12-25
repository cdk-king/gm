package com.cdk;

import com.cdk.util.DomAnalysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication = (默认属性)@Configuration + @EnableAutoConfiguration + @ComponentScan。
@EnableAutoConfiguration
//引入@ServletComponentScan时会扫描标注@WebFilter的自定义filter, 并且加载到项目中
@ServletComponentScan
@ComponentScan(basePackages = "com.cdk")// 扫描该包路径下的所有spring组件
@EnableJpaRepositories("com.cdk") // JPA扫描该包路径下的Repositorie
@EntityScan("com.cdk.entity") // 扫描实体类
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

        DomAnalysis domAnalysis = new DomAnalysis();
        domAnalysis.Analysis("tlog.xml");

    }


}
