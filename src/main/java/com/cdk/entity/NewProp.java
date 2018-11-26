package com.cdk.entity;

public class NewProp {
    public NewProp() {

    }

    public NewProp(int id, String propName, String propType, String propDescribe, int platformId) {
        this.id = id;
        this.propName = propName;
        this.propType = propType;
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
     * 道具类别
     */

    private String propType;

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

    public String getPropType() {
        return propType;
    }

    public void setPropType(String propType) {
        this.propType = propType;
    }

    public String getPropDescribe() {
        return propDescribe;
    }

    public void setPropDescribe(String propDescribe) {
        this.propDescribe = propDescribe;
    }
}
