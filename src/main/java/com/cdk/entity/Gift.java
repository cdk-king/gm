package com.cdk.entity;

import java.util.Date;


public class Gift {
    public Gift() {
        //无参构造器
    }

    public Gift(int id, int platformId, String giftName, String gift_describe, String giftTag, int giftType, String giftValue, int propId,
            String addUser, Date addDatetime, int state, int sort, int isDelete) {
        this.id = id;
        this.platformId = platformId;
        this.giftName = giftName;
        this.gift_describe = gift_describe;
        this.giftTag = giftTag;
        this.giftType = giftType;
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

    private int id;

    /**
     * 平台-编号
     */
    private int platformId;

    /**
     * 礼包名字
     */

    private String giftName;

    /**
     * 礼包说明
     */

    private String gift_describe;

    /**
     * 礼包标识
     */

    private String giftTag;

    /**
     * 礼包类型
     */

    private int giftType;

    /**
     * 礼包价值
     */

    private String giftValue;

    /**
     * 道具编号
     */

    private int propId;

    /**
     * 添加人
     */

    private String addUser;

    /**
     * 添加时间
     */

    private Date addDatetime;

    /**
     * 状态
     */
    private int state;

    /**
     * 分类
     */
    private int sort;

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

    public int getGiftType() {
        return giftType;
    }

    public void setGiftType(int giftType) {
        this.giftType = giftType;
    }
}
