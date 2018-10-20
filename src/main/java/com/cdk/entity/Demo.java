package com.cdk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//@Table配置需要操作的数据库
@Entity
@Table(name="user")
public class Demo {
    public Demo() {

    }

    public Demo(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @GeneratedValue
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="psw")
    private String psw;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPsw() {
        return psw;
    }

    public void setPsw(String name) {
        this.psw = psw;
    }

}
