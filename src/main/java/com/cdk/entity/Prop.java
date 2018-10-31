package com.cdk.entity;

import java.util.Date;

public class Prop {
    public Prop() {

    }

    public Prop(int id, int platformId, String propName, String propTag, String prop_describe, int propType, int state, String addUser,
            Date addDatetime, int isDelete) {
        this.id = id;
        this.platformId = platformId;
        this.propName = propName;
        this.propTag = propTag;
        this.propType = propType;
        this.state = state;
        this.addUser = addUser;
        this.addDatetime = addDatetime;
        this.isDelete = isDelete;
        this.prop_describe = prop_describe;

    }

    /**
     * 道具-编号
     */

    private int id;

    /**
     * 平台-编号
     */

    private int platformId;

    /**
     * 道具名字
     */

    private String propName;

    /**
     * 道具标识
     */

    private String propTag;

    /**
     * 道具描述
     */

    private String prop_describe;

    /**
     * 类别
     */

    private int propType;

    /**
     * 排序
     */
    private int sort;

    /**
     * 状态
     */

    private int state;

    /**
     *  添加人
     */

    private String addUser;

    /**
     *  添加时间
     */

    private Date addDatetime;

    /**
     *  删除标识
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

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public String getPropTag() {
        return propTag;
    }

    public void setPropTag(String propTag) {
        this.propTag = propTag;
    }

    public int getPropType() {
        return propType;
    }

    public void setPropType(int propType) {
        this.propType = propType;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
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

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getProp_describe() {
        return prop_describe;
    }

    public void setProp_describe(String prop_describe) {
        this.prop_describe = prop_describe;
    }
}
