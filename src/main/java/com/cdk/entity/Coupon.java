package com.cdk.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//@Table配置需要操作的数据库
@Entity
@Table(name = "t_coupon")
public class Coupon {

    public Coupon() {
        //无参构造器
    }

    public Coupon(int id, int platformId, int couponId, int giftId, String couponTitle, String coupon_describe, int couponCount, Date startDatetime,
            Date endDatetime, String addUser, Date addDatetime) {
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

    }

    /**
     * 优惠券-编号
     */
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    /**
     * 平台编号
     */
    @Column(name = "platformId")
    private int platformId;

    /**
     * 优惠券标识
     */
    @Column(name = "couponId")
    private int couponId;

    /**
     * 礼包编号
     */
    @Column(name = "giftId")
    private int giftId;

    /**
     * 优惠券标题
     */
    @Column(name = "couponTitle")
    private String couponTitle;

    /**
     * 优惠券说明
     */
    @Column(name = "coupon_describe")
    private String coupon_describe;

    /**
     * 优惠券激活码数量
     */
    @Column(name = "couponCount")
    private int couponCount;

    /**
     * 优惠券开始时间
     */
    @Column(name = "startDatetime")
    private Date startDatetime;

    /**
     * 优惠券结束时间
     */
    @Column(name = "endDatetime")
    private Date endDatetime;

    /**
     * 申请人
     */
    @Column(name = "addUser")
    private String addUser;

    /**
     * 添加时间
     */
    @Column(name = "addDatetime")
    private Date addDatetime;

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
}
