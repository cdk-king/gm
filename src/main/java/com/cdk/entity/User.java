package com.cdk.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

//@Table配置需要操作的数据库
@Entity
@Table(name="t_user")
public class User implements Serializable {

    private static final long serialVersionUID = -6249397911566315813L;

    public User() {
        //无参构造器
    }
    //有参构造器
    public User(int  id, String name, String account, String password, String nick, int age,
                int sex, Date date, String address,String phone,String email,String type,
                int state,Date addDatetime,Date lastDatetime,int isDelete) {
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
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    /**
     * 用户名
     */
    @Column(name="name")
    private String name;
    /**
     * 账号
     */
    @Column(name="account")
    private String account;
    /**
     * 密码
     */
    @Column(name="password")
    private String password;
    /**
     * 昵称
     */
    @Column(name="nick")
    private String nick;
    /**
     * 年龄
     */
    @Column(name="age")
    private int age;
    /**
     * 性别
     */
    @Column(name="sex")
    private int sex;
    /**
     * 日期
     */
    @Column(name="date")
    private Date date;
    /**
     * 地址
     */
    @Column(name="address")
    private String address;
    /**
     * 手机
     */
    @Column(name="phone")
    private String phone;
    /**
     * 邮箱
     */
    @Column(name="email")
    private String email;
    /**
     * 类别
     */
    @Column(name="type")
    private String type;
    /**
     * 状态
     */
    @Column(name="state")
    private int state;
    /**
     * 添加时间
     */
    @Column(name="addDatetime")
    private Date addDatetime;
    /**
     * 更新时间
     */
    @Column(name="lastDatetime")
    private Date lastDatetime;
    /**
     * 删除标识
     */
    @Column(name="isDelete")
    private int isDelete;

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
