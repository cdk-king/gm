package com.cdk.entity;

import java.util.Date;

public class ApplyProp {
    public ApplyProp() {

    }

    public ApplyProp(int id, int gameId, int platformId, int serverId, String releaseTitle, String releaseContent, String propList, int applyType,
            int playerType, String applyUser, String applyReason, int confirmUserId, Date confirmDatetime, int addUser, Date addDatetime,
            int isDelete, int confirmState, String playerNameList, String playerAccountList, String playerIdList, String moneyList,
            Date applyDatetime) {
        this.id = id;
        this.gameId = gameId;
        this.platformId = platformId;
        this.serverId = serverId;
        this.releaseTitle = releaseTitle;
        this.releaseContent = releaseContent;
        this.propList = propList;
        this.applyType = applyType;
        this.playerType = playerType;
        this.applyUser = applyUser;
        this.applyReason = applyReason;
        this.confirmUserId = confirmUserId;
        this.confirmDatetime = confirmDatetime;
        this.confirmState = confirmState;
        this.addUser = addUser;
        this.addDatetime = addDatetime;
        this.isDelete = isDelete;
        this.playerNameList = playerNameList;
        this.playerAccountList = playerAccountList;
        this.playerIdList = playerIdList;
        this.moneyList = moneyList;
        this.applyDatetime = applyDatetime;
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
     * 发布标题
     */

    private String releaseTitle;

    /**
     * 发布内容
     */

    private String releaseContent;

    /**
     * 道具列表
     */

    private String propList;

    /**
     * 申请类型
     */

    private int applyType;

    /**
     * 玩家类型
     */

    private int playerType;

    /**
     * 申请人
     */

    private String applyUser;

    /**
     * 申请原因
     */

    private String applyReason;

    /**
     * 审核人Id
     */

    private int confirmUserId;

    /**
     * 审核时间
     */

    private Date confirmDatetime;


    /**
     * 添加人
     */

    private int addUser;

    /**
     * 添加时间
     */

    private Date addDatetime;

    /**
     * 删除标识
     */

    private int isDelete;

    /**
     * 审核状态
     */

    private int confirmState;

    /**
     * 发送状态
     */

    private int applyState;

    /**
     * 发送时间
     */

    private Date applyDatetime;

    /**
     * 玩家名字列表
     */

    private String playerNameList;

    /**
     * 玩家账号列表
     */

    private String playerAccountList;

    /**
     * 玩家ID列表
     */

    private String playerIdList;

    /**
     * 货币列表
     */

    private String moneyList;


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

    public String getReleaseTitle() {
        return releaseTitle;
    }

    public void setReleaseTitle(String releaseTitle) {
        this.releaseTitle = releaseTitle;
    }

    public String getReleaseContent() {
        return releaseContent;
    }

    public void setReleaseContent(String releaseContent) {
        this.releaseContent = releaseContent;
    }

    public String getPropList() {
        return propList;
    }

    public void setPropList(String propList) {
        this.propList = propList;
    }

    public int getApplyType() {
        return applyType;
    }

    public void setApplyType(int applyType) {
        this.applyType = applyType;
    }

    public int getPlayerType() {
        return playerType;
    }

    public void setPlayerType(int playerType) {
        this.playerType = playerType;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

    public int getConfirmUserId() {
        return confirmUserId;
    }

    public void setConfirmUserId(int confirmUserId) {
        this.confirmUserId = confirmUserId;
    }

    public Date getConfirmDatetime() {
        return confirmDatetime;
    }

    public void setConfirmDatetime(Date confirmDatetime) {
        this.confirmDatetime = confirmDatetime;
    }

    public int getAddUser() {
        return addUser;
    }

    public void setAddUser(int addUser) {
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

    public int getConfirmState() {
        return confirmState;
    }

    public void setConfirmState(int confirmState) {
        this.confirmState = confirmState;
    }

    public String getPlayerNameList() {
        return playerNameList;
    }

    public void setPlayerNameList(String playerNameList) {
        this.playerNameList = playerNameList;
    }

    public String getPlayerAccountList() {
        return playerAccountList;
    }

    public void setPlayerAccountList(String playerAccountList) {
        this.playerAccountList = playerAccountList;
    }

    public String getPlayerIdList() {
        return playerIdList;
    }

    public void setPlayerIdList(String playerIdList) {
        this.playerIdList = playerIdList;
    }

    public String getMoneyList() {
        return moneyList;
    }

    public void setMoneyList(String moneyList) {
        this.moneyList = moneyList;
    }

    public int getApplyState() {
        return applyState;
    }

    public void setApplyState(int applyState) {
        this.applyState = applyState;
    }

    public Date getApplyDatetime() {
        return applyDatetime;
    }

    public void setApplyDatetime(Date applyDatetime) {
        this.applyDatetime = applyDatetime;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
}
