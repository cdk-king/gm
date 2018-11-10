package com.cdk.entity;

import java.util.Date;

public class Player {
    public Player() {

    }

    public Player(int id, String playerName, String playerAccount, int playerId, int isOnline, String lastIp, int vipLevel, int diamond,
            int rechargeAmount, int level, Date registrationTime, int combatPower, Date addDateTime, int isProhibitSpeak, int isBan, int platformId,
            int serverId, int prohibitSpeakTime, int banTime) {
        this.id = id;
        this.playerName = playerName;
        this.playerAccount = playerAccount;
        this.playerId = playerId;
        this.isOnline = isOnline;
        this.lastIp = lastIp;
        this.vipLevel = vipLevel;
        this.diamond = diamond;
        this.rechargeAmount = rechargeAmount;
        this.level = level;
        this.registrationTime = registrationTime;
        this.combatPower = combatPower;
        this.addDateTime = addDateTime;
        this.isProhibitSpeak = isProhibitSpeak;
        this.isBan = isBan;
        this.platformId = platformId;
        this.serverId = serverId;
        this.prohibitSpeakTime = prohibitSpeakTime;
        this.banTime = banTime;
    }

    /**
     * 玩家-编号
     */
    private int id;

    /**
     * 玩家-用户名
     */
    private String playerName;

    /**
     * 玩家-账号
     */
    private String playerAccount;

    /**
     * 玩家-Id标识
     */
    private int playerId;


    /**
     * 玩家-是否在线
     */
    private int isOnline;


    /**
     * 玩家-最后登录IP
     */
    private String lastIp;


    /**
     * 玩家-VIP等级
     */
    private int vipLevel;

    /**
     * 玩家-钻石数量
     */
    private int diamond;

    /**
     * 玩家-充值金额
     */
    private int rechargeAmount;

    /**
     * 玩家-等级
     */
    private int level;

    /**
     * 玩家-注册时间
     */
    private Date registrationTime;


    /**
     * 玩家-战力
     */
    private int combatPower;

    /**
     * 添加时间
     */
    private Date addDateTime;

    /**
     * 玩家-是否禁言
     */
    private int isProhibitSpeak;

    /**
     * 玩家-是否封禁
     */
    private int isBan;

    /**
     * 玩家所在平台Id
     */
    private int platformId;

    /**
     * 玩家所在服务器Id
     */
    private int serverId;

    /**
     * 禁言时间
     */
    private int prohibitSpeakTime;

    /**
     * 禁封时间
     */
    private int banTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerAccount() {
        return playerAccount;
    }

    public void setPlayerAccount(String playerAccount) {
        this.playerAccount = playerAccount;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(int isOnline) {
        this.isOnline = isOnline;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public int getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(int vipLevel) {
        this.vipLevel = vipLevel;
    }

    public int getDiamond() {
        return diamond;
    }

    public void setDiamond(int diamond) {
        this.diamond = diamond;
    }

    public int getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(int rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Date getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime;
    }

    public int getCombatPower() {
        return combatPower;
    }

    public void setCombatPower(int combatPower) {
        this.combatPower = combatPower;
    }

    public Date getAddDateTime() {
        return addDateTime;
    }

    public void setAddDateTime(Date addDateTime) {
        this.addDateTime = addDateTime;
    }

    public int getIsProhibitSpeak() {
        return isProhibitSpeak;
    }

    public void setIsProhibitSpeak(int isProhibitSpeak) {
        this.isProhibitSpeak = isProhibitSpeak;
    }

    public int getIsBan() {
        return isBan;
    }

    public void setIsBan(int isBan) {
        this.isBan = isBan;
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

    public int getProhibitSpeakTime() {
        return prohibitSpeakTime;
    }

    public void setProhibitSpeakTime(int prohibitSpeakTime) {
        this.prohibitSpeakTime = prohibitSpeakTime;
    }

    public int getBanTime() {
        return banTime;
    }

    public void setBanTime(int banTime) {
        this.banTime = banTime;
    }
}
