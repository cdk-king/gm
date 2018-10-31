package com.cdk.entity;

import java.io.Serializable;
import java.util.Date;


public class User implements Serializable {

    private static final long serialVersionUID = -6249397911566315813L;

    public User() {
        //无参构造器
    }

    //有参构造器
    public User(int id, String name, String account, String password, String nick, int age, int sex, Date date, String address, String phone,
            String email, String type, int state, Date addDatetime, Date lastDatetime, int isDelete) {
        this.id = id;
        this.name = name;
        this.account = account;
        this.password = password;
        this.nick = nick;
        this.age = age;
        this.sex = sex;
        this.date = date;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.type = type;
        this.state = state;
        this.addDatetime = addDatetime;
        this.lastDatetime = lastDatetime;
        this.isDelete = isDelete;
    }

    /**
     * 用户-编号
     */

    private int id;
    /**
     * 用户名
     */

    private String name;
    /**
     * 账号
     */

    private String account;
    /**
     * 密码
     */

    private String password;
    /**
     * 昵称
     */

    private String nick;
    /**
     * 年龄
     */

    private int age;
    /**
     * 性别
     */

    private int sex;
    /**
     * 日期
     */
    private Date date;
    /**
     * 地址
     */

    private String address;
    /**
     * 手机
     */

    private String phone;
    /**
     * 邮箱
     */

    private String email;
    /**
     * 类别
     */

    private String type;
    /**
     * 状态
     */

    private int state;
    /**
     * 添加时间
     */

    private Date addDatetime;
    /**
     * 更新时间
     */

    private Date lastDatetime;
    /**
     * 删除标识
     */
    private int isDelete;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public String getNick() {
        return nick;
    }

    public int getAge() {
        return age;
    }

    public int getSex() {
        return sex;
    }

    public Date getDate() {
        return date;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getType() {
        return type;
    }

    public int getState() {
        return state;
    }

    public Date getAddDatetime() {
        return addDatetime;
    }

    public Date getLastDatetime() {
        return lastDatetime;
    }

    public int getIsDelete() {
        return isDelete;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setAddDatetime(Date addDatetime) {
        this.addDatetime = addDatetime;
    }

    public void setLastDatetime(Date lastDatetime) {
        this.lastDatetime = lastDatetime;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
}
