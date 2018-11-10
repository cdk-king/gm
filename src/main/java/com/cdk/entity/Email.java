package com.cdk.entity;

import java.util.Date;

public class Email {
    public Email() {

    }

    public Email(int id, int platformId, int serverId, String emailTitle, String emailContent, String sendReason, int sendType, int minLevel,
            int maxLevel, int minVipLevel, int maxVipLevel, Date minRegistrationTime, Date maxRegistrationTime, int isOnline, int sex,
            String playerNameList, String playerIdList, String playerAccountList, int sendState, Date sendDatetime, String addUser, Date addDatetime,
            int isDelete) {
        this.id = id;
        this.platformId = platformId;
        this.serverId = serverId;
        this.emailTitle = emailTitle;
        this.emailContent = emailContent;
        this.sendReason = sendReason;
        this.sendType = sendType;
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
        this.minVipLevel = minVipLevel;
        this.maxVipLevel = maxVipLevel;
        this.minRegistrationTime = minRegistrationTime;
        this.maxRegistrationTime = maxRegistrationTime;
        this.isOnline = isOnline;
        this.sex = sex;
        this.playerNameList = playerNameList;
        this.playerIdList = playerIdList;
        this.playerAccountList = playerAccountList;
        this.sendState = sendState;
        this.sendDatetime = sendDatetime;
        this.addUser = addUser;
        this.addDatetime = addDatetime;
        this.isDelete = isDelete;

    }

    /**
     * 公告-编号
     */
    private int id;

    /**
     * 平台-编号
     */
    private int platformId;

    /**
     * 服务器编号
     */
    private int serverId;

    /**
     * 邮件标题
     */
    private String emailTitle;

    /**
     * 邮件内容
     */
    private String emailContent;

    /**
     * 发送原因
     */
    private String sendReason;

    /**
     * 公告发送类别
     */
    private int sendType;


    /**
     * 最小等级
     */
    private int minLevel;

    /**
     * 最大等级
     */
    private int maxLevel;

    /**
     * 最小vip等级
     */
    private int minVipLevel;

    /**
     * 最大vip等级
     */
    private int maxVipLevel;

    /**
     * 最小注册时间
     */
    private Date minRegistrationTime;

    /**
     * 最大注册时间
     */
    private Date maxRegistrationTime;

    /**
     * 是否在线
     */
    private int isOnline;

    /**
     * 性别
     */
    private int sex;

    /**
     * 玩家名字列表
     */
    private String playerNameList;
    /**
     * 玩家id列表
     */
    private String playerIdList;
    /**
     * 玩家账号列表
     */
    private String playerAccountList;

    /**
     * 发送状态
     */
    private int sendState;

    /**
     * 发送时间
     */
    private Date sendDatetime;

    /**
     * 添加人
     */
    private String addUser;

    /**
     * 添加时间
     */
    private Date addDatetime;

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

    public String getEmailTitle() {
        return emailTitle;
    }

    public void setEmailTitle(String emailTitle) {
        this.emailTitle = emailTitle;
    }

    public String getEmailContent() {
        return emailContent;
    }

    public void setEmailContent(String emailContent) {
        this.emailContent = emailContent;
    }

    public int getSendType() {
        return sendType;
    }

    public void setSendType(int sendType) {
        this.sendType = sendType;
    }

    public int getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(int minLevel) {
        this.minLevel = minLevel;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public int getMinVipLevel() {
        return minVipLevel;
    }

    public void setMinVipLevel(int minVipLevel) {
        this.minVipLevel = minVipLevel;
    }

    public int getMaxVipLevel() {
        return maxVipLevel;
    }

    public void setMaxVipLevel(int maxVipLevel) {
        this.maxVipLevel = maxVipLevel;
    }

    public Date getMinRegistrationTime() {
        return minRegistrationTime;
    }

    public void setMinRegistrationTime(Date minRegistrationTime) {
        this.minRegistrationTime = minRegistrationTime;
    }

    public Date getMaxRegistrationTime() {
        return maxRegistrationTime;
    }

    public void setMaxRegistrationTime(Date maxRegistrationTime) {
        this.maxRegistrationTime = maxRegistrationTime;
    }

    public int getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(int isOnline) {
        this.isOnline = isOnline;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPlayerNameList() {
        return playerNameList;
    }

    public void setPlayerNameList(String playerNameList) {
        this.playerNameList = playerNameList;
    }

    public String getPlayerIdList() {
        return playerIdList;
    }

    public void setPlayerIdList(String playerIdList) {
        this.playerIdList = playerIdList;
    }

    public String getPlayerAccountList() {
        return playerAccountList;
    }

    public void setPlayerAccountList(String playerAccountList) {
        this.playerAccountList = playerAccountList;
    }

    public int getSendState() {
        return sendState;
    }

    public void setSendState(int sendState) {
        this.sendState = sendState;
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

    public Date getSendDatetime() {
        return sendDatetime;
    }

    public void setSendDatetime(Date sendDatetime) {
        this.sendDatetime = sendDatetime;
    }

    public String getSendReason() {
        return sendReason;
    }

    public void setSendReason(String sendReason) {
        this.sendReason = sendReason;
    }
}
