package com.cdk.entity;

import java.io.Serializable;
import java.util.Date;


public class Role implements Serializable {
    private static final long serialVersionUID = -6249397911566315813L;

    public Role() {
        //无参构造器
    }

    //有参构造器
    public Role(int id, String role, String role_describe, String addUser, Date addDatetime, int state, int isDelete) {
        this.id = id;
        this.role = role;
        this.role_describe = role_describe;
        this.addUser = addUser;
        this.state = state;
        this.isDelete = isDelete;
    }

    /**
     * 角色-编号
     */

    private int id;
    /**
     * 角色名
     */

    private String role;
    /**
     * 角色描述
     */

    private String role_describe;
    /**
     * 添加人
     */

    private String addUser;
    /**
     * 添加时间
     */

    private Date addDatetime;
    /**
     * 状态
     */

    private int state;
    /**
     * 删除标识
     */
    private int isDelete;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getRole_describe() {
        return role_describe;
    }

    public String getAddUser() {
        return addUser;
    }

    public Date getAddDatetime() {
        return addDatetime;
    }

    public int getState() {
        return state;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setRole_describe(String role_describe) {
        this.role_describe = role_describe;
    }

    public void setAddUser(String addUser) {
        this.addUser = addUser;
    }

    public void setAddDatetime(Date addDatetime) {
        this.addDatetime = addDatetime;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

}
