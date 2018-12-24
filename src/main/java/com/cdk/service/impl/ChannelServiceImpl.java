package com.cdk.service.impl;

import com.cdk.dao.impl.ChannelDaoImpl;
import com.cdk.entity.Channel;
import com.cdk.result.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

@Service
public class ChannelServiceImpl {
    private static Logger logger = Logger.getLogger(String.valueOf(ChannelServiceImpl.class));
    public static final String Divider = "############################";
    public static final String Split = "----------------";

    @Autowired
    public ChannelDaoImpl channelDaoImpl;

    public Result getAllChannelFormPlatform(Map map) {
        String platformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        Result re;
        Map<String, Object> JsonMap = channelDaoImpl.getAllChannelFormPlatform(platformId);
        logger.info(JsonMap.get("list").toString());
        if (Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(200, "通道列表为空", "");
        } else {
            re = new Result(200, "通道列表获取成功", JsonMap);
        }
        return re;
    }

    public Result getAllChannel(Map map) {
        Result re;
        Map<String, Object> JsonMap = channelDaoImpl.getAllChannel();
        logger.info(JsonMap.get("list").toString());
        if (Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(200, "通道列表为空", "");
        } else {
            re = new Result(200, "通道列表获取成功", JsonMap);
        }
        return re;
    }

    public Result getChannel(Map map) {
        String id = ((map.get("id") != null && map.get("id") != "") ? map.get("id").toString() : "0");
        Result re;
        Map<String, Object> JsonMap = channelDaoImpl.getChannel(id);
        logger.info(JsonMap.get("list").toString());
        if (Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(200, "通道列表为空", "");
        } else {
            re = new Result(200, "通道列表获取成功", JsonMap);
        }
        return re;
    }

    public Result getChannelTable(Map map) {
        String channelId = ((map.get("channelId") != null && map.get("channelId") != "") ? map.get("channelId").toString() : "0");
        String channelTag = ((map.get("channelTag") != null && map.get("channelTag") != "") ? map.get("channelTag").toString() : "");
        String channelName = ((map.get("channelName") != null && map.get("channelName") != "") ? map.get("channelName").toString() : "");
        String channel_describe =
                ((map.get("channel_describe") != null && map.get("channel_describe") != "") ? map.get("channel_describe").toString() : "");
        String platformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String isPage = (map.get("isPage") != null ? map.get("isPage").toString() : "");
        String StrPageNo = (map.get("pageNo") != null ? map.get("pageNo").toString() : "1");
        String StrPageSize = (map.get("pageSize") != null ? map.get("pageSize").toString() : "5");
        int pageNo = 1;
        int pageSize = 5;
        try {
            pageNo = Integer.parseInt(StrPageNo);
            pageSize = Integer.parseInt(StrPageSize);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        Result re;

        Channel channel = new Channel();
        channel.setChannelId(Integer.parseInt(channelId));
        channel.setChannelName(channelName);
        channel.setPlatformId(Integer.parseInt(platformId));
        channel.setChannelTag(channelTag);
        channel.setChannel_describe(channel_describe);

        Map<String, Object> JsonMap = channelDaoImpl.getChannelTable(channel, isPage, pageNo, pageSize);
        logger.info(JsonMap.get("list").toString());
        if (Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(200, "通道列表为空", "");
        } else {
            re = new Result(200, "通道列表获取成功", JsonMap);
        }
        return re;
    }

    public Result addChannel(Map map) {
        String channelId = ((map.get("channelId") != null && map.get("channelId") != "") ? map.get("channelId").toString() : "0");
        String channelName = ((map.get("channelName") != null && map.get("channelName") != "") ? map.get("channelName").toString() : "");
        String channelTag = ((map.get("channelTag") != null && map.get("channelTag") != "") ? map.get("channelTag").toString() : "0");
        String channel_describe =
                ((map.get("channel_describe") != null && map.get("channel_describe") != "") ? map.get("channel_describe").toString() : "0");
        String addUser = ((map.get("addUser") != null && map.get("addUser") != "") ? map.get("addUser").toString() : "0");
        String platformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");

        Channel channel = new Channel();
        channel.setChannelId(Integer.parseInt(channelId));
        channel.setChannelName(channelName);
        channel.setPlatformId(Integer.parseInt(platformId));
        channel.setAddUser(addUser);
        channel.setChannelTag(channelTag);
        channel.setChannel_describe(channel_describe);

        Result re;
        int temp = channelDaoImpl.addChannel(channel);
        if (temp > 0) {
            re = new Result(200, "通道添加成功", temp);
        } else {
            re = new Result(400, "通道添加失败", temp);
        }
        return re;
    }

    public Result editChannel(Map map) {
        String id = ((map.get("id") != null && map.get("id") != "") ? map.get("id").toString() : "0");
        String channelId = ((map.get("channelId") != null && map.get("channelId") != "") ? map.get("channelId").toString() : "0");
        String channelName = ((map.get("channelName") != null && map.get("channelName") != "") ? map.get("channelName").toString() : "");
        String channelTag = ((map.get("channelTag") != null && map.get("channelTag") != "") ? map.get("channelTag").toString() : "0");
        String channel_describe =
                ((map.get("channel_describe") != null && map.get("channel_describe") != "") ? map.get("channel_describe").toString() : "0");
        String addUser = ((map.get("addUser") != null && map.get("addUser") != "") ? map.get("addUser").toString() : "0");
        String platformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        logger.info(platformId);
        Channel channel = new Channel();
        channel.setId(Integer.parseInt(id));
        channel.setChannelId(Integer.parseInt(channelId));
        channel.setChannelName(channelName);
        channel.setPlatformId(Integer.parseInt(platformId));
        channel.setAddUser(addUser);
        channel.setChannelTag(channelTag);
        channel.setChannel_describe(channel_describe);

        Result re;
        int temp = channelDaoImpl.editChannel(channel);
        if (temp > 0) {
            re = new Result(200, "通道修改成功", temp);
        } else {
            re = new Result(400, "通道修改失败", temp);
        }
        return re;
    }


    public Result deleteChannel(Map map) {
        String id = ((map.get("id") != null && map.get("id") != "") ? map.get("id").toString() : "0");
        Result re;
        int temp = channelDaoImpl.deleteChannel(id);
        logger.info(temp + "");
        if (temp > 0) {
            re = new Result(200, "通道删除成功", temp);
        } else {
            re = new Result(400, "通道删除失败", temp);
        }
        return re;
    }

    public Result saveCheckChannel(Map map) {
        String id = ((map.get("id") != null && map.get("id") != "") ? map.get("id").toString() : "0");
        String channel = ((map.get("channel") != null && map.get("channel") != "") ? map.get("channel").toString() : "");
        Result re;
        int temp = channelDaoImpl.saveCheckChannel(id, channel);
        logger.info(temp + "");
        if (temp > 0) {
            re = new Result(200, "通道更新成功", temp);
        } else {
            re = new Result(400, "通道更新失败", temp);
        }
        return re;
    }

    public Result saveAllCheckChannel(Map map) {
        String ids = ((map.get("ids") != null && map.get("ids") != "") ? map.get("ids").toString() : "");
        String channel = ((map.get("channel") != null && map.get("channel") != "") ? map.get("channel").toString() : "");
        String[] array = ids.split(",");
        logger.info(ids);
        logger.info(channel);
        Result re;
        int temp = 0;
        for (int i = 0; i < array.length; i++) {
            temp = channelDaoImpl.saveCheckChannel(array[i], channel);
            logger.info(temp + "");
        }
        if (temp > 0) {
            re = new Result(200, "通道批量处理完成", temp);
        } else {
            re = new Result(400, "通道批量处理失败", temp);
        }
        return re;
    }
}
