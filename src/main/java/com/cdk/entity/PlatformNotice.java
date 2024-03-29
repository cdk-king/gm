package com.cdk.entity;

import java.util.Date;

public class PlatformNotice {
    public PlatformNotice() {

    }


    public PlatformNotice(int id, int gameId, int platformId, String serverList, Date startDatetime, Date endDatetime, String noticeTitle,
            String noticeContent, int sendState, String addUser, Date addDatetime, int isDelete, String propList, String moneyList,
            String errorList) {
        this.id = id;
        this.gameId = gameId;
        this.platformId = platformId;
        this.serverList = serverList;
        this.startDatetime = startDatetime;
        this.endDatetime = endDatetime;
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.sendState = sendState;
        this.addUser = addUser;
        this.addDatetime = addDatetime;
        this.isDelete = isDelete;
        this.propList = propList;
        this.moneyList = moneyList;
        this.errorList = errorList;

    }

    /**
     * 公告-编号
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
     * 公告标题
     */
    private String noticeTitle;

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
     * 道具列表
     */
    private String propList;

    /**
     * 货币列表
     */
    private String moneyList;
    /**
     * 错误列表
     */
    private String errorList;

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

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
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

    public String getPropList() {
        return propList;
    }

    public void setPropList(String propList) {
        this.propList = propList;
    }

    public String getMoneyList() {
        return moneyList;
    }

    public void setMoneyList(String moneyList) {
        this.moneyList = moneyList;
    }

    public String getErrorList() {
        return errorList;
    }

    public void setErrorList(String errorList) {
        this.errorList = errorList;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
}
