package com.cdk.entity;

public class NewGift {
    public NewGift() {

    }

    public NewGift(int id, String giftName, String giftTag, String giftDescribe, String giftValue, int platformId) {
        this.id = id;
        this.giftName = giftName;
        this.giftTag = giftTag;
        this.giftDescribe = giftDescribe;
        this.giftValue = giftValue;
        this.platformId = platformId;
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

    private String giftDescribe;

    /**
     * 礼包标识
     */

    private String giftTag;

    /**
     * 礼包价值
     */

    private String giftValue;


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

    public String getGiftDescribe() {
        return giftDescribe;
    }

    public void setGiftDescribe(String giftDescribe) {
        this.giftDescribe = giftDescribe;
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
}
