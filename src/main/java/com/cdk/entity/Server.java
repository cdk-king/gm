package com.cdk.entity;

import java.util.Date;


public class Server {

    public Server() {
        //无参构造器
    }

    public Server(int id, int serverId, String server, String serverIp, String serverPort, int gameId, int platformId, String server_describe,
            int state, int sort, String addUser, Date addDatetime, int isDelete, int isDefault, String platformTag, String area,
            String openServiceTime) {
        //有参构造器
        this.id = id;
        this.serverId = serverId;
        this.server = server;
        this.serverIp = serverIp;
        this.serverPort = serverPort;
        this.gameId = gameId;
        this.platformId = platformId;
        this.server_describe = server_describe;
        this.state = state;
        this.sort = sort;
        this.addUser = addUser;
        this.addDatetime = addDatetime;
        this.isDelete = isDelete;
        this.isDefault = isDefault;
        this.platformTag = platformTag;
        this.area = area;
        this.openServiceTime = openServiceTime;

    }

    /**
     * 表-编号
     */

    private int id;

    /**
     * 游戏-编号
     */

    private int gameId;

    /**
     * 服务器-编号
     */

    private int serverId;

    /**
     * 服务器名
     */

    private String server;

    /**
     * 服务器IP
     */

    private String serverIp;

    /**
     * 服务器端口
     */

    private String serverPort;

    /**
     * 平台ID
     */

    private int platformId;

    /**
     * 服务器描述
     */

    private String server_describe;

    /**
     * 服务器状态
     */

    private int state;

    /**
     * 服务器排序
     */

    private int sort;

    /**
     * 服务器添加人
     */

    private String addUser;

    /**
     * 服务器添加时间
     */

    private Date addDatetime;

    /**
     * 服务器删除标识
     */

    private int isDelete;

    /**
     * 是否默认服务器
     */

    private int isDefault;

    /**
     * 服务器标识
     */

    private String platformTag;


    /**
     * 区域
     */

    private String area;

    /**
     * 开服时间
     */

    private String openServiceTime;

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

    public String getServerPort() {
        return serverPort;
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    public String getPlatformTag() {
        return platformTag;
    }

    public void setPlatformTag(String platformTag) {
        this.platformTag = platformTag;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getOpenServiceTime() {
        return openServiceTime;
    }

    public void setOpenServiceTime(String openServiceTime) {
        this.openServiceTime = openServiceTime;
    }

    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
}
