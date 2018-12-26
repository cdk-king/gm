package com.cdk.entity;

public class DataSource {

    public DataSource() {

    }

    public DataSource(int id, int platformId, int dataSource_id, String dataSource_url, String dataSource_name, String dataSource_password,
            String addDatetime, String addUser, int isDelete) {
        this.id = id;
        this.platformId = platformId;
        this.dataSource_id = dataSource_id;
        this.dataSource_url = dataSource_url;
        this.dataSource_name = dataSource_name;
        this.dataSource_password = dataSource_password;
        this.addDatetime = addDatetime;
        this.addUser = addUser;
        this.isDelete = isDelete;
    }

    /**
     * 表-编号
     */
    private int id;

    /**
     * 平台-编号
     */
    private int platformId;

    /**
     * 数据源-编号
     */
    private int dataSource_id;

    /**
     * 数据源-地址
     */
    private String dataSource_url;

    /**
     * 数据源-用户名
     */
    private String dataSource_name;

    /**
     * 数据源-密码
     */
    private String dataSource_password;


    /**
     * 添加时间
     */
    private String addDatetime;

    /**
     * 添加人
     */
    private String addUser;

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

    public int getDataSource_id() {
        return dataSource_id;
    }

    public void setDataSource_id(int dataSource_id) {
        this.dataSource_id = dataSource_id;
    }

    public String getDataSource_url() {
        return dataSource_url;
    }

    public void setDataSource_url(String dataSource_url) {
        this.dataSource_url = dataSource_url;
    }

    public String getDataSource_name() {
        return dataSource_name;
    }

    public void setDataSource_name(String dataSource_name) {
        this.dataSource_name = dataSource_name;
    }

    public String getDataSource_password() {
        return dataSource_password;
    }

    public void setDataSource_password(String dataSource_password) {
        this.dataSource_password = dataSource_password;
    }

    public String getAddDatetime() {
        return addDatetime;
    }

    public void setAddDatetime(String addDatetime) {
        this.addDatetime = addDatetime;
    }

    public String getAddUser() {
        return addUser;
    }

    public void setAddUser(String addUser) {
        this.addUser = addUser;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
}
