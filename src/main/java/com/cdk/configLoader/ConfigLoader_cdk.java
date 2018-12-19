package com.cdk.configLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class ConfigLoader_cdk {

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
                    System.out.println("----------------------------------");
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
                System.out.println(str.substring(index, len));
                dbUrl = str.substring(index, len);
            }
            key = "spring.datasource.username=";
            if (str.indexOf(key) != -1) {
                int index = str.indexOf(key) + key.length();
                System.out.println(str.substring(index, len));
                dbName = str.substring(index, len);
            }
            key = "spring.datasource.password=";
            if (str.indexOf(key) != -1) {
                int index = str.indexOf(key) + key.length();
                System.out.println(str.substring(index, len));
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
}
