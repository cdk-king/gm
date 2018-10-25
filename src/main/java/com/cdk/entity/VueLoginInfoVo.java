package com.cdk.entity;

//使用@NotNull该注解的字段的值不能为null，否则验证无法通过。

/**
 * @description Vue登录页面demo信息对象实体
 * @author
 * @date 2018年月日
 * @Copyright 版权所有 (c)
 * @memo
 */

public class VueLoginInfoVo {

    //@NotNull(message="用户名不允许为空")
    private String username;

    //@NotNull(message="密码不允许为空")
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
