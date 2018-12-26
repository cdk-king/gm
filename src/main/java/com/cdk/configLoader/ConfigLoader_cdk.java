package com.cdk.configLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class ConfigLoader_cdk {
    private static Logger logger = LoggerFactory.getLogger(ConfigLoader_cdk.class);
    private String dbUrl = "";
    private String dbName = "";
    private String dbPwd = "";

    public void loadTxt(String name) {
        String fileName = name;
        String results = new String();
        File file = new File(fileName);

        if (file.exists()) {
            if (file.getName().indexOf(".txt") != 1) {
                FileReader fr = null;
                try {
                    fr = new FileReader(file);
                    //                    char[] a = new char[50];
                    //                    fr.read(a); // 从数组中读取内容
                    //                    StringBuilder result = new StringBuilder();
                    //                    for (char c : a) {
                    //                        System.out.print(c); // 一个个打印字符
                    //                    }
                    String str = null;
                    //字符流
                    BufferedReader br = new BufferedReader(new FileReader(fileName));
                    while ((str = br.readLine()) != null) {
                        AnalysisStrDB(str);
                    }
                    fr.close();
                    logger.debug("----------------------------------");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                throw new RuntimeException("文件不是txt??? " + fileName);
            }
        } else {
            throw new RuntimeException("文件不存在??? " + fileName);
        }
    }

    public void AnalysisStrDB(String str) {
        int len = str.length();
        //过滤
        if (Objects.equals(str, "") || Objects.equals(str, null)) {
            return;
        }
        if (!Objects.equals(str.substring(0, 1), "#")) {
            String key = "";
            key = "spring.datasource.url=";
            if (str.indexOf(key) != -1) {
                int index = str.indexOf(key) + key.length();
                logger.debug(str.substring(index, len));
                dbUrl = str.substring(index, len);
            }
            key = "spring.datasource.username=";
            if (str.indexOf(key) != -1) {
                int index = str.indexOf(key) + key.length();
                logger.debug(str.substring(index, len));
                dbName = str.substring(index, len);
            }
            key = "spring.datasource.password=";
            if (str.indexOf(key) != -1) {
                int index = str.indexOf(key) + key.length();
                logger.debug(str.substring(index, len));
                dbPwd = str.substring(index, len);
            }
            if (!Objects.equals(dbUrl, "") && !Objects.equals(dbName, "") && !Objects.equals(dbPwd, "")) {
                configDb(dbUrl, dbName, dbPwd);
            }

        }
    }

    public void configDb(String url, String name, String pwd) {
        // TODO: 2018/12/17 硬编码配置,application.properties文件其实默认可以配置在jar外边，无需硬编码

    }

    public void jnditest() {
        // TODO Auto-generated method stub
        // JDBC 驱动名及数据库 URL
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB";

        // 数据库的用户名与密码，需要根据自己的设置
        String USER = "root";
        String PASS = "123456";
        Connection conn = null;
        Statement stmt = null;
        try {
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 打开链接
            logger.debug("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行查询
            logger.debug(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, name, url FROM websites";
            ResultSet rs = stmt.executeQuery(sql);
            // 展开结果集数据库
            while (rs.next()) {
                // 通过字段检索
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String url = rs.getString("url");
                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", 站点名称: " + name);
                System.out.print(", 站点 URL: " + url);
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
