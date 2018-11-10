package com.cdk.entity;

public class NewProp {
    public NewProp() {

    }

    public NewProp(int id, String propName, String propTag, String propDescribe, int platformId) {
        this.id = id;
        this.propName = propName;
        this.propTag = propTag;
        this.propDescribe = propDescribe;
        this.platformId = platformId;
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

    private String propDescribe;

    /**
     * 类别
     */

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

    public String getPropDescribe() {
        return propDescribe;
    }

    public void setPropDescribe(String propDescribe) {
        this.propDescribe = propDescribe;
    }
}
