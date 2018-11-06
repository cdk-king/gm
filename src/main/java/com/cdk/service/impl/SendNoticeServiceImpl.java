package com.cdk.service.impl;


import com.cdk.dao.impl.SendNoticeDaoImpl;
import com.cdk.entity.Notice;
import com.cdk.result.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class SendNoticeServiceImpl {

    public static final String Divider = "############################";
    public static final String Split = "----------------";

    @Autowired
    public SendNoticeDaoImpl sendNoticeDaoImpl;

    public Result getSendNoticeSendType() {
        Result re;
        Map<String, Object> JsonMap = sendNoticeDaoImpl.getSendNoticeSendType();
        if (Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(400, "公告发送类别获取失败", "");
        } else {
            re = new Result(200, "公告发送类别获取成功", JsonMap);
        }
        return re;
    }

    public Result getSendNoticeNoticeType() {
        Result re;
        Map<String, Object> JsonMap = sendNoticeDaoImpl.getSendNoticeNoticeType();
        if (Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(400, "公告消息类别获取失败", "");
        } else {
            re = new Result(200, "公告消息类别获取成功", JsonMap);
        }
        return re;
    }

    public Result addNotice(Map map) {
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String strServerList = (map.get("serverList") != null ? map.get("strServerList").toString() : "");
        String strSendType = (map.get("sendType") != null ? map.get("sendType").toString() : "0");
        String strNoticeType = (map.get("noticeType") != null ? map.get("noticeType").toString() : "0");
        String strTimeInterval = (map.get("timeInterval") != null ? map.get("timeInterval").toString() : "60");
        String strCycleTime = (map.get("cycleTime") != null ? map.get("cycleTime").toString() : "1");
        String noticeContent = (map.get("noticeContent") != null ? map.get("noticeContent").toString() : "");
        String addUser = (map.get("addUser") != null ? map.get("addUser").toString() : "");
        int platformId = Integer.parseInt(strPlatformId);
        int sendType = Integer.parseInt(strSendType);
        int noticeType = Integer.parseInt(strNoticeType);
        int timeInterval = Integer.parseInt(strTimeInterval);
        int cycleTime = Integer.parseInt(strCycleTime);
        Result re;
        Notice notice = new Notice();
        notice.setPlatformId(platformId);
        notice.setServerList(strServerList);
        notice.setSendType(sendType);
        notice.setNoticeType(noticeType);
        notice.setTimeInterval(timeInterval);
        notice.setCycleTime(cycleTime);
        notice.setNoticeContent(noticeContent);
        notice.setAddUser(addUser);

        int temp = sendNoticeDaoImpl.addNotice(notice);
        if (temp > 0) {
            System.out.println("游戏添加成功");
            re = new Result(200, "游戏添加成功", null);
        } else {
            System.out.println("游戏添加失败");
            re = new Result(400, "游戏添加失败", null);
        }
        return re;
    }

}
