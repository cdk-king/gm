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
        String strServerList = (map.get("serverList") != null ? map.get("serverList").toString() : "");
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
            System.out.println("公告添加成功");
            re = new Result(200, "公告添加成功", null);
        } else {
            System.out.println("公告添加失败");
            re = new Result(400, "公告添加失败", null);
        }
        return re;
    }

    public Result getNotice(Map map) {
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String serverName = (map.get("serverName") != null ? map.get("serverName").toString() : "");
        String strSendType = (map.get("sendType") != null ? map.get("sendType").toString() : "0");
        String strNoticeType = (map.get("noticeType") != null ? map.get("noticeType").toString() : "0");
        String strTimeInterval = (map.get("timeInterval") != null ? map.get("timeInterval").toString() : "60");
        String strCycleTime = (map.get("cycleTime") != null ? map.get("cycleTime").toString() : "1");
        String noticeContent = (map.get("noticeContent") != null ? map.get("noticeContent").toString() : "");
        String addUser = (map.get("addUser") != null ? map.get("addUser").toString() : "");
        String isPage = (map.get("isPage") != null ? map.get("isPage").toString() : "");
        String StrPageNo = (map.get("pageNo") != null ? map.get("pageNo").toString() : "1");
        String StrPageSize = (map.get("pageSize") != null ? map.get("pageSize").toString() : "5");
        int pageNo = Integer.parseInt(StrPageNo);
        int pageSize = Integer.parseInt(StrPageSize);
        int platformId = Integer.parseInt(strPlatformId);
        int sendType = Integer.parseInt(strSendType);
        int noticeType = Integer.parseInt(strNoticeType);
        int timeInterval = Integer.parseInt(strTimeInterval);
        int cycleTime = Integer.parseInt(strCycleTime);
        Result re;
        Notice notice = new Notice();
        notice.setPlatformId(platformId);
        notice.setServerList(serverName);
        notice.setSendType(sendType);
        notice.setNoticeType(noticeType);
        notice.setTimeInterval(timeInterval);
        notice.setCycleTime(cycleTime);
        notice.setNoticeContent(noticeContent);
        notice.setAddUser(addUser);
        Map<String, Object> JsonMap = sendNoticeDaoImpl.getNotice(notice, isPage, pageNo, pageSize);
        if (Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(400, "公告列表获取失败", "");
        } else {
            re = new Result(200, "公告列表获取成功", JsonMap);
        }
        return re;
    }


    public Result deleteAllNotice(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        System.out.println("id：" + id);
        if (Objects.equals(id, "")) {
            System.out.println("无任何批量删除操作");
            return new Result(400, "无任何批量删除操作", null);
        }

        String[] objectArry = id.split(",");
        System.out.println("ObjectArry：" + objectArry);
        Result re;
        String sql[] = new String[objectArry.length];
        int[] temp = sendNoticeDaoImpl.deleteAllNotice(objectArry);
        if (temp.length != 0) {
            System.out.println("公告批量删除成功");
            re = new Result(200, "公告批量删除成功", null);
        } else if (objectArry.length == 0) {
            System.out.println("无任何删除操作");
            re = new Result(400, "无任何删除操作", null);
        } else {
            System.out.println("公告批量删除失败");
            re = new Result(400, "公告批量删除失败", null);
        }
        return re;
    }

    public Result sendNotice(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "0");

        Result re;
        int temp = sendNoticeDaoImpl.sendNotice(id);
        if (temp > 0) {
            System.out.println("公告发送成功");
            re = new Result(200, "公告发送成功", null);
        } else {
            System.out.println("公告发送失败");
            re = new Result(400, "公告发送失败", null);
        }
        return re;
    }
}
