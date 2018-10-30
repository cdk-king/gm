package com.cdk.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//@Table配置需要操作的数据库
@Entity
@Table(name = "t_gameserver")
public class Server {

    public Server() {
        //无参构造器
    }

    public Server(int id, String server, String serverIp, int platformId, String server_describe, int state, int sort, String addUser,
            Date addDatetime, int isDelete) {
        //有参构造器
        this.id = id;
        this.server = server;
        this.serverIp = serverIp;
        this.platformId = platformId;
        this.server_describe = server_describe;
        this.state = state;
        this.sort = sort;
        this.addUser = addUser;
        this.addDatetime = addDatetime;
        this.isDelete = isDelete;
    }

    /**
     * 服务器-编号
     */
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    /**
     * 服务器名
     */
    @Column(name = "server")
    private String server;

    /**
     * 服务器IP
     */
    @Column(name = "serverIp")
    private String serverIp;

    /**
     * 平台ID
     */
    @Column(name = "platformId")
    private int platformId;

    /**
     * 服务器描述
     */
    @Column(name = "server_describe")
    private String server_describe;

    /**
     * 服务器状态
     */
    @Column(name = "state")
    private int state;

    /**
     * 服务器排序
     */
    @Column(name = "sort")
    private int sort;

    /**
     * 服务器添加人
     */
    @Column(name = "addUser")
    private String addUser;

    /**
     * 服务器添加时间
     */
    @Column(name = "addDatetime")
    private Date addDatetime;

    /**
     * 服务器删除标识
     */
    @Column(name = "isDelete")
    private int isDelete;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public int getPlatformId() {
        return platformId;
    }

    public void setPlatformId(int platformId) {
        this.platformId = platformId;
    }

    public String getServer_describe() {
        return server_describe;
    }

    public void setServer_describe(String server_describe) {
        this.server_describe = server_describe;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getAddUser() {
        return addUser;
    }

    public void setAddUser(String addUser) {
        this.addUser = addUser;
    }

    public Date getAddDatetime() {
        return addDatetime;
    }

    public void setAddDatetime(Date addDatetime) {
        this.addDatetime = addDatetime;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
}
