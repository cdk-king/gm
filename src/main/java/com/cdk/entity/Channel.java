package com.cdk.entity;

public class Channel {

    public Channel() {

    }

    public Channel(int channelId, String channelName, String channelTag, String channel_describe, String addUser, int gameId, int platformId,
            int id) {

        this.channelId = channelId;
        this.channelName = channelName;
        this.channelTag = channelTag;
        this.channel_describe = channel_describe;
        this.addUser = addUser;
        this.gameId = gameId;
        this.platformId = platformId;
        this.id = id;
    }

    /**
     * 表-编号
     */

    private int id;

    /**
     * 通道-编号
     */

    private int channelId;

    /**
     * 通道名称
     */

    private String channelName;

    /**
     * 通道标识
     */

    private String channelTag;

    /**
     * 通道描述
     */

    private String channel_describe;

    /**
     * 添加人
     */

    private String addUser;

    /**
     * 游戏标识
     */

    private int gameId;

    /**
     * 平台标识
     */

    private int platformId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelTag() {
        return channelTag;
    }

    public void setChannelTag(String channelTag) {
        this.channelTag = channelTag;
    }

    public String getChannel_describe() {
        return channel_describe;
    }

    public void setChannel_describe(String channel_describe) {
        this.channel_describe = channel_describe;
    }

    public String getAddUser() {
        return addUser;
    }

    public void setAddUser(String addUser) {
        this.addUser = addUser;
    }

    public int getPlatformId() {
        return platformId;
    }

    public void setPlatformId(int platformId) {
        this.platformId = platformId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
}
