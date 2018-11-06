package com.cdk.entity;

import java.util.Date;

public class Notice {
    public Notice() {

    }

    public Notice(int id, int platformId, String serverList, int sendType, int noticeType, int timeInterval, int cycleTime, Date startDatetime,
            Date endDatetime, Date sendDatetime, String noticeContent, int sendState, String addUser, Date addDatetime, int isDelete) {
        this.id = id;
        this.platformId = platformId;
        this.serverList = serverList;
        this.sendType = sendType;
        this.noticeType = noticeType;
        this.timeInterval = timeInterval;
        this.cycleTime = cycleTime;
        this.startDatetime = startDatetime;
        this.endDatetime = endDatetime;
        this.sendDatetime = sendDatetime;
        this.noticeContent = noticeContent;
        this.sendState = sendState;
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
     * 服务器列表
     */
    private String serverList;

    /**
     * 公告发送类别
     */
    private int sendType;

    /**
     * 公告消息类别
     */
    private int noticeType;

    /**
     * 时间间隔
     */
    private int timeInterval;

    /**
     * 循环次数
     */
    private int cycleTime;

    /**
     * 开始时间
     */
    private Date startDatetime;

    /**
     * 结束时间
     */
    private Date endDatetime;

    /**
     * 发送时间
     */
    private Date sendDatetime;

    /**
     * 公告内容
     */
    private String noticeContent;

    /**
     * 发送状态
     */
    private int sendState;

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

    public String getServerList() {
        return serverList;
    }

    public void setServerList(String serverList) {
        this.serverList = serverList;
    }

    public int getSendType() {
        return sendType;
    }

    public void setSendType(int sendType) {
        this.sendType = sendType;
    }

    public int getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(int noticeType) {
        this.noticeType = noticeType;
    }

    public int getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(int timeInterval) {
        this.timeInterval = timeInterval;
    }

    public int getCycleTime() {
        return cycleTime;
    }

    public void setCycleTime(int cycleTime) {
        this.cycleTime = cycleTime;
    }

    public Date getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(Date startDatetime) {
        this.startDatetime = startDatetime;
    }

    public Date getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(Date endDatetime) {
        this.endDatetime = endDatetime;
    }

    public Date getSendDatetime() {
        return sendDatetime;
    }

    public void setSendDatetime(Date sendDatetime) {
        this.sendDatetime = sendDatetime;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
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
}
