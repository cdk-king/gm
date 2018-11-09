package com.cdk.service.impl;


import com.cdk.dao.impl.PlatformEmailDaoImpl;
import com.cdk.entity.PlatformEmail;
import com.cdk.result.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

@Service
public class PlatformEmailServiceImpl {

    public static final String Divider = "############################";
    public static final String Split = "----------------";

    @Autowired
    public PlatformEmailDaoImpl platformEmailDaoImpl;

    public Result getPlatformEmail(Map map) {
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String serverName = (map.get("serverName") != null ? map.get("serverName").toString() : "");
        String emailContent = (map.get("emailContent") != null ? map.get("emailContent").toString() : "");
        String addUser = (map.get("addUser") != null ? map.get("addUser").toString() : "");
        String isPage = (map.get("isPage") != null ? map.get("isPage").toString() : "");
        String StrPageNo = (map.get("pageNo") != null ? map.get("pageNo").toString() : "1");
        String StrPageSize = (map.get("pageSize") != null ? map.get("pageSize").toString() : "5");
        int pageNo = Integer.parseInt(StrPageNo);
        int pageSize = Integer.parseInt(StrPageSize);
        int platformId = Integer.parseInt(strPlatformId);
        Result re;
        PlatformEmail platformEmail = new PlatformEmail();
        platformEmail.setPlatformId(platformId);
        platformEmail.setServerList(serverName);
        platformEmail.setEmailContent(emailContent);
        platformEmail.setAddUser(addUser);
        Map<String, Object> JsonMap = platformEmailDaoImpl.getPlatformEmail(platformEmail, isPage, pageNo, pageSize);
        if (Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(400, "全服邮件列表获取失败", "");
        } else {
            re = new Result(200, "全服邮件列表获取成功", JsonMap);
        }
        return re;
    }

    public Result editPlatformEmail(Map map) {
        String strId = (map.get("id") != null ? map.get("id").toString() : "0");
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String strServerList = (map.get("serverList") != null ? map.get("serverList").toString() : "");
        String strStartDatetime = (map.get("startDatetime") != null ? map.get("startDatetime").toString() : "");
        String strEndDatetime = (map.get("endDatetime") != null ? map.get("endDatetime").toString() : "");
        String emailTitle = (map.get("emailTitle") != null ? map.get("emailTitle").toString() : "");
        String emailContent = (map.get("emailContent") != null ? map.get("emailContent").toString() : "");
        String sendReason = (map.get("sendReason") != null ? map.get("sendReason").toString() : "");
        String addUser = (map.get("addUser") != null ? map.get("addUser").toString() : "");
        int platformId = Integer.parseInt(strPlatformId);
        int id = Integer.parseInt(strId);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date startDatetime = formatter.parse(strStartDatetime, pos);
        //必须重新创建实例
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        pos = new ParsePosition(0);
        Date endDatetime = formatter.parse(strEndDatetime, pos);

        Result re;
        PlatformEmail platformEmail = new PlatformEmail();
        platformEmail.setId(id);
        platformEmail.setPlatformId(platformId);
        platformEmail.setServerList(strServerList);
        platformEmail.setEmailTitle(emailTitle);
        platformEmail.setEmailContent(emailContent);
        platformEmail.setSendReason(sendReason);
        platformEmail.setAddUser(addUser);
        platformEmail.setStartDatetime(startDatetime);
        platformEmail.setEndDatetime(endDatetime);


        int temp = platformEmailDaoImpl.editPlatformEmail(platformEmail);
        if (temp > 0) {
            System.out.println("全服公告编辑成功");
            re = new Result(200, "全服公告编辑成功", null);
        } else {
            System.out.println("全服公告编辑失败");
            re = new Result(400, "全服公告编辑失败", null);
        }
        return re;
    }


    public Result addPlatformEmail(Map map) {
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String strServerList = (map.get("serverList") != null ? map.get("serverList").toString() : "");
        String strStartDatetime = (map.get("startDatetime") != null ? map.get("startDatetime").toString() : "");
        String strEndDatetime = (map.get("endDatetime") != null ? map.get("endDatetime").toString() : "");
        String emailTitle = (map.get("emailTitle") != null ? map.get("emailTitle").toString() : "");
        String emailContent = (map.get("emailContent") != null ? map.get("emailContent").toString() : "");
        String sendReason = (map.get("sendReason") != null ? map.get("sendReason").toString() : "");
        String addUser = (map.get("addUser") != null ? map.get("addUser").toString() : "");
        int platformId = Integer.parseInt(strPlatformId);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date startDatetime = formatter.parse(strStartDatetime, pos);
        //必须重新创建实例
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        pos = new ParsePosition(0);
        Date endDatetime = formatter.parse(strEndDatetime, pos);

        Result re;
        PlatformEmail platformEmail = new PlatformEmail();
        platformEmail.setPlatformId(platformId);
        platformEmail.setServerList(strServerList);
        platformEmail.setEmailTitle(emailTitle);
        platformEmail.setEmailContent(emailContent);
        platformEmail.setSendReason(sendReason);
        platformEmail.setAddUser(addUser);
        platformEmail.setStartDatetime(startDatetime);
        platformEmail.setEndDatetime(endDatetime);


        int temp = platformEmailDaoImpl.addPlatformEmail(platformEmail);
        if (temp > 0) {
            System.out.println("全服邮件添加成功");
            re = new Result(200, "全服邮件添加成功", null);
        } else {
            System.out.println("全服邮件添加失败");
            re = new Result(400, "全服邮件添加失败", null);
        }
        return re;
    }

    public Result deletePlatformEmail(Map map) {
        String strId = (map.get("id") != null ? map.get("id").toString() : "0");
        int id = Integer.parseInt(strId);

        Result re;
        PlatformEmail platformEmail = new PlatformEmail();
        platformEmail.setId(id);

        int temp = platformEmailDaoImpl.deletePlatformEmail(platformEmail);
        if (temp > 0) {
            System.out.println("全服邮件删除成功");
            re = new Result(200, "全服邮件删除成功", null);
        } else {
            System.out.println("全服公告删除失败");
            re = new Result(400, "全服公告删除失败", null);
        }
        return re;
    }

    public Result sendPlatformEmail(Map map) {
        String strId = (map.get("id") != null ? map.get("id").toString() : "0");
        int id = Integer.parseInt(strId);

        Result re;
        PlatformEmail platformEmail = new PlatformEmail();
        platformEmail.setId(id);

        int temp = platformEmailDaoImpl.sendPlatformEmail(platformEmail);
        if (temp > 0) {
            System.out.println("全服邮件发送成功");
            re = new Result(200, "全服邮件发送成功", null);
        } else {
            System.out.println("全服邮件发送失败");
            re = new Result(400, "全服邮件发送失败", null);
        }
        return re;
    }

    public Result deleteAllPlatformEmail(Map map) {
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
        int[] temp = platformEmailDaoImpl.deleteAllPlatformEmail(objectArry);
        if (temp.length != 0) {
            System.out.println("全服邮件批量删除成功");
            re = new Result(200, "全服邮件批量删除成功", null);
        } else if (objectArry.length == 0) {
            System.out.println("无任何删除操作");
            re = new Result(400, "无任何删除操作", null);
        } else {
            System.out.println("全服邮件批量删除失败");
            re = new Result(400, "全服邮件批量删除失败", null);
        }
        return re;
    }
}
