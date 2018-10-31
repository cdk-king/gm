package com.cdk.entity;

import java.util.Date;


public class Platform {
    public Platform() {
        //无参构造器
    }

    public Platform(int id, String platform, int gameId, int roleId, String platformTag, String platform_describe, int parentId, int state, int sort,
            String addUser, Date addDatetime, int isDelete) {
        this.id = id;
        this.platform = platform;
        this.gameId = gameId;
        this.roleId = roleId;
        this.platformTag = platformTag;
        this.platform_describe = platform_describe;
        this.parentId = parentId;
        this.state = state;
        this.sort = sort;
        this.addUser = addUser;
        this.addDatetime = addDatetime;
        this.isDelete = isDelete;
    }

    /**
     * 平台-编号
     */

    private int id;

    /**
     * 平台名
     */
    private String platform;

    /**
     * 所属游戏编号
     */
    private int gameId;

    /**
     * 对应角色编号
     */
    private int roleId;

    /**
     * 平台标识
     */
    private String platformTag;

    /**
     * 平台描述
     */
    private String platform_describe;

    /**
     * 父平台编号
     */
    private int parentId;

    /**
     * 平台状态
     */

    private int state;

    /**
     * 平台排序
     */
    private int sort;

    /**
     * 平台添加人
     */
    private String addUser;

    /**
     * 平台添加人
     */
    private Date addDatetime;

    /**
     * 平台删除标识
     */
    private int isDelete;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getPlatformTag() {
        return platformTag;
    }

    public void setPlatformTag(String platformTag) {
        this.platformTag = platformTag;
    }

    public String getPlatform_describe() {
        return platform_describe;
    }

    public void setPlatform_describe(String platform_describe) {
        this.platform_describe = platform_describe;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
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
