package com.cdk.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//@Table配置需要操作的数据库
@Entity
@Table(name = "t_gift")
public class Gift {
    public Gift() {
        //无参构造器
    }

    public Gift(int id, int platformId, String giftName, String gift_describe, String giftTag, String giftValue, int propId, String addUser,
            Date addDatetime, int state, int sort, int isDelete) {
        this.id = id;
        this.platformId = platformId;
        this.giftName = giftName;
        this.gift_describe = gift_describe;
        this.giftTag = giftTag;
        this.giftValue = giftValue;
        this.propId = propId;
        this.addUser = addUser;
        this.addDatetime = addDatetime;
        this.state = state;
        this.sort = sort;
        this.isDelete = isDelete;

    }

    /**
     * 礼包-编号
     */
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    /**
     * 平台-编号
     */
    @Column(name = "platformId")
    private int platformId;

    /**
     * 礼包名字
     */
    @Column(name = "giftName")
    private String giftName;

    /**
     * 礼包说明
     */
    @Column(name = "gift_describe")
    private String gift_describe;

    /**
     * 礼包标识
     */
    @Column(name = "giftTag")
    private String giftTag;

    /**
     * 礼包价值
     */
    @Column(name = "giftValue")
    private String giftValue;

    /**
     * 道具编号
     */
    @Column(name = "propId")
    private int propId;

    /**
     * 添加人
     */
    @Column(name = "addUser")
    private String addUser;

    /**
     * 添加时间
     */
    @Column(name = "addDatetime")
    private Date addDatetime;

    /**
     * 状态
     */
    @Column(name = "state")
    private int state;

    /**
     * 分类
     */
    @Column(name = "sort")
    private int sort;

    /**
     * 删除标识
     */
    @Column(name = "isDelete")
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

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public String getGift_describe() {
        return gift_describe;
    }

    public void setGift_describe(String gift_describe) {
        this.gift_describe = gift_describe;
    }

    public String getGiftTag() {
        return giftTag;
    }

    public void setGiftTag(String giftTag) {
        this.giftTag = giftTag;
    }

    public String getGiftValue() {
        return giftValue;
    }

    public void setGiftValue(String giftValue) {
        this.giftValue = giftValue;
    }

    public int getPropId() {
        return propId;
    }

    public void setPropId(int propId) {
        this.propId = propId;
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

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
}
