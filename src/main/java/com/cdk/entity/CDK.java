package com.cdk.entity;


public class CDK {
    public CDK() {

    }

    public CDK(int id, int couponId, int sequenceId, String cdk, int isUsed, int platformId) {
        this.id = id;
        this.couponId = couponId;
        this.sequenceId = sequenceId;
        this.cdk = cdk;
        this.isUsed = isUsed;
        this.platformId = platformId;
    }

    /**
     * 已使用激活码-编号
     */
    private int id;

    /**
     * 优惠券标识
     */
    private int couponId;

    /**
     * 优惠券序列号
     */
    private int sequenceId;

    /**
     * 激活码
     */
    private String cdk;

    /**
     * 激活码使用情况
     */
    private int isUsed;

    /**
     * 平台编号
     */
    private int platformId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    public int getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(int sequenceId) {
        this.sequenceId = sequenceId;
    }

    public String getCdk() {
        return cdk;
    }

    public void setCdk(String cdk) {
        this.cdk = cdk;
    }

    public int getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(int cdkUse) {
        this.isUsed = cdkUse;
    }

    public int getPlatformId() {
        return platformId;
    }

    public void setPlatformId(int platformId) {
        this.platformId = platformId;
    }
}
