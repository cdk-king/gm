package com.cdk.entity;

import java.util.Date;

public class Coupon {

    public Coupon() {
        //无参构造器
    }

    public Coupon(int id, int platformId, int couponId, int giftId, String couponTitle, String coupon_describe, int couponCount, Date startDatetime,
            Date endDatetime, String addUser, Date addDatetime, int isDelete, int isCommonCDK) {
        this.id = id;
        this.platformId = platformId;
        this.couponId = couponId;
        this.giftId = giftId;
        this.couponTitle = couponTitle;
        this.coupon_describe = coupon_describe;
        this.couponCount = couponCount;
        this.startDatetime = startDatetime;
        this.endDatetime = endDatetime;
        this.addUser = addUser;
        this.addDatetime = addDatetime;
        this.isDelete = isDelete;
        this.isCommonCDK = isCommonCDK;
    }

    /**
     * 优惠券-编号
     */

    private int id;

    /**
     * 平台编号
     */

    private int platformId;

    /**
     * 优惠券标识
     */

    private int couponId;

    /**
     * 礼包编号
     */

    private int giftId;

    /**
     * 优惠券标题
     */

    private String couponTitle;

    /**
     * 优惠券说明
     */

    private String coupon_describe;

    /**
     * 优惠券激活码数量
     */

    private int couponCount;

    /**
     * 优惠券开始时间
     */

    private Date startDatetime;

    /**
     * 优惠券结束时间
     */

    private Date endDatetime;

    /**
     * 申请人
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

    /**
     * 是否通用
     */

    private int isCommonCDK;

    /**
     * 通用激活码
     */

    private String commonCDK;

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

    public int getGiftId() {
        return giftId;
    }

    public void setGiftId(int giftId) {
        this.giftId = giftId;
    }

    public String getCouponTitle() {
        return couponTitle;
    }

    public void setCouponTitle(String couponTitle) {
        this.couponTitle = couponTitle;
    }

    public String getCoupon_describe() {
        return coupon_describe;
    }

    public void setCoupon_describe(String coupon_describe) {
        this.coupon_describe = coupon_describe;
    }

    public int getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(int couponCount) {
        this.couponCount = couponCount;
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

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public int getIsCommonCDK() {
        return isCommonCDK;
    }

    public void setIsCommonCDK(int isCommonCDK) {
        this.isCommonCDK = isCommonCDK;
    }
}
