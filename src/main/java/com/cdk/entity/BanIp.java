package com.cdk.entity;

import java.util.Date;

public class BanIp {
    public BanIp() {

    }

    public BanIp(int id, int gameId, int platformId, int serverId, String ip, String note, String banLong, Date addDatetime, Date banDatetime,
            String banState, String addUser, int isDelete) {
        this.id = id;
        this.gameId = gameId;
        this.platformId = platformId;
        this.serverId = serverId;
        this.ip = ip;
        this.note = note;
        this.banLong = banLong;
        this.addDatetime = addDatetime;
        this.banDatetime = banDatetime;
        this.banState = banState;
        this.addUser = addUser;
        this.isDelete = isDelete;
    }

    /**
     * 申请-编号
     */

    private int id;

    /**
     * 游戏-编号
     */

    private int gameId;

    /**
     * 平台-编号
     */

    private int platformId;

    /**
     * 服务器-编号
     */

    private int serverId;

    /**
     * ip
     */

    private String ip;

    /**
     * 备注
     */

    private String note;

    /**
     * 时长
     */

    private String banLong;

    /**
     * 添加时间
     */

    private Date addDatetime;

    /**
     * 封禁时间
     */

    private Date banDatetime;


    /**
     * 封禁状态
     */

    private String banState;

    /**
     * 添加人
     */

    private String addUser;

    /**
     * 删除标识
     */

    private int isDelete;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlatformId() {
        return platformId;
    }

    public void setPlatformId(int platformId) {
        this.platformId = platformId;
    }

    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getBanLong() {
        return banLong;
    }

    public void setBanLong(String banLong) {
        this.banLong = banLong;
    }

    public Date getAddDatetime() {
        return addDatetime;
    }

    public void setAddDatetime(Date addDatetime) {
        this.addDatetime = addDatetime;
    }

    public Date getBanDatetime() {
        return banDatetime;
    }

    public void setBanDatetime(Date banDatetime) {
        this.banDatetime = banDatetime;
    }

    public String getBanState() {
        return banState;
    }

    public void setBanState(String banState) {
        this.banState = banState;
    }

    public String getAddUser() {
        return addUser;
    }

    public void setAddUser(String addUser) {
        this.addUser = addUser;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
}
