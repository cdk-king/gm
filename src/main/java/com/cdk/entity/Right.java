package com.cdk.entity;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//@Table配置需要操作的数据库
@Entity
@Table(name = "t_right")
public class Right implements Serializable {
    private static final long serialVersionUID = -6249397911566315813L;

    public Right() {
        //无参构造器
    }

    public Right(int id, String rightName, String right_describe, int rightParentId, String rightTag, int rightSort, int state, Date addDatetime,
            String addUser, int isDelete) {
        //有参构造器
        this.id = id;
        this.rightName = rightName;
        this.right_describe = right_describe;
        this.rightParentId = rightParentId;
        this.rightTag = rightTag;
        this.rightSort = rightSort;
        this.state = state;
        this.addDatetime = addDatetime;
        this.addUser = addUser;
        this.isDelete = isDelete;
    }


    /**
     * 权限-编号
     */
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    /**
     * 权限名
     */
    @Column(name = "rightName")
    private String rightName;
    /**
     * 权限描述
     */
    @Column(name = "right_describe")
    private String right_describe;
    /**
     * 权限父节点编号
     */
    @Column(name = "rightParentId")
    private int rightParentId;
    /**
     * 权限标识
     */
    @Column(name = "rightTag")
    private String rightTag;
    /**
     * 权限排序
     */
    @Column(name = "rightSort")
    private int rightSort;
    /**
     * 权限状态
     */
    @Column(name = "state")
    private int state;
    /**
     * 权限添加时间
     */
    @Column(name = "addDatetime")
    private Date addDatetime;
    /**
     * 权限添加人
     */
    @Column(name = "addUser")
    private String addUser;
    /**
     * 权限删除标识
     */
    @Column(name = "isDelete")
    private int isDelete;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public String getRightName() {
        return rightName;
    }

    public String getRight_describe() {
        return right_describe;
    }

    public int getRightParentId() {
        return rightParentId;
    }

    public String getRightTag() {
        return rightTag;
    }

    public int getRightSort() {
        return rightSort;
    }

    public int getState() {
        return state;
    }

    public Date getAddDatetime() {
        return addDatetime;
    }

    public String getAddUser() {
        return addUser;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRightName(String rightName) {
        this.rightName = rightName;
    }

    public void setRight_describe(String right_describe) {
        this.right_describe = right_describe;
    }

    public void setRightParentId(int rightParentId) {
        this.rightParentId = rightParentId;
    }

    public void setRightTag(String rightTag) {
        this.rightTag = rightTag;
    }

    public void setRightSort(int rightSort) {
        this.rightSort = rightSort;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setAddDatetime(Date addDatetime) {
        this.addDatetime = addDatetime;
    }

    public void setAddUser(String addUser) {
        this.addUser = addUser;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

}
