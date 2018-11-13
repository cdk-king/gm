package com.cdk.entity;

import java.io.Serializable;
import java.util.Date;


public class Game implements Serializable {
    private static final long serialVersionUID = -6249397911566315813L;

    public Game() {
        //无参构造器
    }

    public Game(int id, String gameName, String game_describe, String gameTag, String gameEncryptSign, int state, Date addDatetime, String addUser,
            int sort, int isDelete) {
        //有参构造器
        this.id = id;
        this.gameName = gameName;
        this.game_describe = game_describe;
        this.gameTag = gameTag;
        this.state = state;
        this.addDatetime = addDatetime;
        this.addUser = addUser;
        this.sort = sort;
        this.isDelete = isDelete;
        this.gameEncryptSign = gameEncryptSign;
    }

    /**
     * 游戏-编号
     */

    private int id;

    /**
     * 游戏名
     */

    private String gameName;

    /**
     * 游戏描述
     */

    private String game_describe;

    /**
     * 游戏标识
     */

    private String gameTag;

    /**
     * 游戏加密标识
     */

    private String gameEncryptSign;

    /**
     * 游戏状态
     */

    private int state;

    /**
     * 游戏添加时间
     */

    private Date addDatetime;

    /**
     * 游戏添加人
     */

    private String addUser;

    /**
     * 游戏排序
     */

    private int sort;

    /**
     * 游戏删除标识
     */

    private int isDelete;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGame_describe() {
        return game_describe;
    }

    public void setGame_describe(String game_describe) {
        this.game_describe = game_describe;
    }

    public String getGameTag() {
        return gameTag;
    }

    public void setGameTag(String gameTag) {
        this.gameTag = gameTag;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getAddDatetime() {
        return addDatetime;
    }

    public void setAddDatetime(Date addDatetime) {
        this.addDatetime = addDatetime;
    }

    public String getAddUser() {
        return addUser;
    }

    public void setAddUser(String addUser) {
        this.addUser = addUser;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public String getGameEncryptSign() {
        return gameEncryptSign;
    }

    public void setGameEncryptSign(String gameEncryptSign) {
        this.gameEncryptSign = gameEncryptSign;
    }
}
