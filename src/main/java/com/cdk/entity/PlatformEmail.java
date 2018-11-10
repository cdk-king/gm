package com.cdk.entity;

import java.util.Date;

public class PlatformEmail {
    public PlatformEmail() {

    }

    public PlatformEmail(int id, int platformId, String serverList, Date startDatetime, Date endDatetime, String emailTitle, String emailContent,
            String sendReason, int sendState, String addUser, Date addDatetime, int isDelete) {
        this.id = id;
        this.platformId = platformId;
        this.serverList = serverList;
        this.startDatetime = startDatetime;
        this.endDatetime = endDatetime;
        this.emailTitle = emailTitle;
        this.emailContent = emailContent;
        this.sendReason = sendReason;
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
     * 开始时间
     */
    private Date startDatetime;

    /**
     * 结束时间
     */
    private Date endDatetime;

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

    public String getSendReason() {
        return sendReason;
    }

    public void setSendReason(String sendReason) {
        this.sendReason = sendReason;
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
