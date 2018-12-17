package com.cdk;

import com.cdk.cache.CacheListener;
import com.cdk.cache.CacheManagerImpl;
import com.cdk.classLoader.ClassLoaderTest;
import com.cdk.configLoader.ConfigLoader_cdk;
import com.cdk.util.HttpRequestUtil;
import com.cdk.util.annotation.AnnotationTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication = (默认属性)@Configuration + @EnableAutoConfiguration + @ComponentScan。
//@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.cdk")// 扫描该包路径下的所有spring组件
@EnableJpaRepositories("com.cdk") // JPA扫描该包路径下的Repositorie
@EntityScan("com.cdk.entity") // 扫描实体类
public class Main {
    /**
     * 无法注入
     */

    public static void main(String[] args) {
        CacheManagerImpl cacheManagerImpl = new CacheManagerImpl();
        CacheListener cacheListener = new CacheListener(cacheManagerImpl);
        cacheListener.startAllListenUseThread();
        cacheListener.cacheManagerImpl.putCache("CDK", "CDK", 30000);


        SpringApplication.run(Main.class, args);

        /**
         * HttpRequestUtil测试
         */
        HttpRequestUtil httpRequestUtil = new HttpRequestUtil();
        //httpRequestUtil.testBaiduTranslate();

        /**
         * 反射调用外部Class文件
         */
        ClassLoaderTest classLoaderTest = new ClassLoaderTest();
        //classLoaderTest.LoaderTest();

        /**
         * 自定义注解测试
         */
        AnnotationTest annotationTest = new AnnotationTest();
        try {
            annotationTest.test();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        /**
         *
         */
        ConfigLoader_cdk configLoader = new ConfigLoader_cdk();
        configLoader.loadTxt("info.txt");
    }
}
