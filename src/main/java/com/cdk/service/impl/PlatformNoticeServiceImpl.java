package com.cdk.service.impl;

import com.cdk.cache.CacheListener;
import com.cdk.cache.CacheManagerImpl;
import com.cdk.dao.impl.PlatformNoticeDaoImpl;
import com.cdk.entity.PlatformNotice;
import com.cdk.result.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

@Service
public class PlatformNoticeServiceImpl {

    public static final String Divider = "############################";
    public static final String Split = "----------------";

    @Autowired
    public PlatformNoticeDaoImpl platformNoticeDaoImpl;
    //@Autowired   @Service实现自动装填
    //public CacheManagerImpl cacheManagerImpl;
    public CacheManagerImpl cacheManagerImpl = new CacheManagerImpl();

    public CacheListener cacheListener;

    public Result getPlatformNotice(Map map) {
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String serverName = (map.get("serverName") != null ? map.get("serverName").toString() : "");
        String noticeContent = (map.get("noticeContent") != null ? map.get("noticeContent").toString() : "");
        String addUser = (map.get("addUser") != null ? map.get("addUser").toString() : "");
        String isPage = (map.get("isPage") != null ? map.get("isPage").toString() : "");
        String StrPageNo = (map.get("pageNo") != null ? map.get("pageNo").toString() : "1");
        String StrPageSize = (map.get("pageSize") != null ? map.get("pageSize").toString() : "5");
        int pageNo = Integer.parseInt(StrPageNo);
        int pageSize = Integer.parseInt(StrPageSize);
        int platformId = Integer.parseInt(strPlatformId);
        Result re;
        PlatformNotice platformNotice = new PlatformNotice();
        platformNotice.setPlatformId(platformId);
        platformNotice.setServerList(serverName);
        platformNotice.setNoticeContent(noticeContent);
        platformNotice.setAddUser(addUser);
        Map<String, Object> JsonMap = platformNoticeDaoImpl.getPlatformNotice(platformNotice, isPage, pageNo, pageSize);
        if (Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(400, "全服公告列表获取失败", "");
        } else {
            re = new Result(200, "全服公告列表获取成功", JsonMap);
        }
        return re;
    }

    public Result addPlatformNotice(Map map) {
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String strServerList = (map.get("serverList") != null ? map.get("serverList").toString() : "");
        String strStartDatetime = (map.get("startDatetime") != null ? map.get("startDatetime").toString() : "");
        String strEndDatetime = (map.get("endDatetime") != null ? map.get("endDatetime").toString() : "");
        String noticeTitle = (map.get("noticeTitle") != null ? map.get("noticeTitle").toString() : "");
        String noticeContent = (map.get("noticeContent") != null ? map.get("noticeContent").toString() : "");
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
        PlatformNotice platformNotice = new PlatformNotice();
        platformNotice.setPlatformId(platformId);
        platformNotice.setServerList(strServerList);
        platformNotice.setNoticeTitle(noticeTitle);
        platformNotice.setNoticeContent(noticeContent);
        platformNotice.setAddUser(addUser);
        platformNotice.setStartDatetime(startDatetime);
        platformNotice.setEndDatetime(endDatetime);

        Object a = new Object();


        int temp = platformNoticeDaoImpl.addPlatformNotice(platformNotice);
        if (temp > 0) {
            System.out.println("全服公告添加成功");
            long number = 20000L;
            cacheManagerImpl.putCache("Notice", platformNotice, 20000L);
            //cacheListener = new CacheListener(cacheManagerImpl);
            //cacheListener.startListen();
            Logger logger = Logger.getLogger("platformNoticeLog");
            logger.info("Notice缓存添加成功");
            PlatformNotice data = (PlatformNotice) cacheManagerImpl.getCacheDataByKey("Notice");
            String content = data.getNoticeContent();
            logger.info(content);
            re = new Result(200, "全服公告添加成功", null);
        } else {
            System.out.println("全服公告添加失败");
            re = new Result(400, "全服公告添加失败", null);
        }
        return re;
    }

    public Result editPlatformNotice(Map map) {
        String strId = (map.get("id") != null ? map.get("id").toString() : "0");
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String strServerList = (map.get("serverList") != null ? map.get("serverList").toString() : "");
        String strStartDatetime = (map.get("startDatetime") != null ? map.get("startDatetime").toString() : "");
        String strEndDatetime = (map.get("endDatetime") != null ? map.get("endDatetime").toString() : "");
        String noticeTitle = (map.get("noticeTitle") != null ? map.get("noticeTitle").toString() : "");
        String noticeContent = (map.get("noticeContent") != null ? map.get("noticeContent").toString() : "");
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
        PlatformNotice platformNotice = new PlatformNotice();
        platformNotice.setId(id);
        platformNotice.setPlatformId(platformId);
        platformNotice.setServerList(strServerList);
        platformNotice.setNoticeTitle(noticeTitle);
        platformNotice.setNoticeContent(noticeContent);
        platformNotice.setAddUser(addUser);
        platformNotice.setStartDatetime(startDatetime);
        platformNotice.setEndDatetime(endDatetime);


        int temp = platformNoticeDaoImpl.editPlatformNotice(platformNotice);
        if (temp > 0) {
            System.out.println("全服公告编辑成功");
            re = new Result(200, "全服公告编辑成功", null);
        } else {
            System.out.println("全服公告编辑失败");
            re = new Result(400, "全服公告编辑失败", null);
        }
        return re;
    }


    public Result deletePlatformNotice(Map map) {
        String strId = (map.get("id") != null ? map.get("id").toString() : "0");
        int id = Integer.parseInt(strId);

        Result re;
        PlatformNotice platformNotice = new PlatformNotice();
        platformNotice.setId(id);

        int temp = platformNoticeDaoImpl.deletePlatformNotice(platformNotice);
        if (temp > 0) {
            System.out.println("全服公告删除成功");
            re = new Result(200, "全服公告删除成功", null);
        } else {
            System.out.println("全服公告删除失败");
            re = new Result(400, "全服公告删除失败", null);
        }
        return re;
    }

    public Result sendPlatformNotice(Map map) {
        String strId = (map.get("id") != null ? map.get("id").toString() : "0");
        int id = Integer.parseInt(strId);

        Result re;
        PlatformNotice platformNotice = new PlatformNotice();
        platformNotice.setId(id);

        int temp = platformNoticeDaoImpl.sendPlatformNotice(platformNotice);
        if (temp > 0) {
            System.out.println("全服公告发送成功");
            re = new Result(200, "全服公告发送成功", null);
        } else {
            System.out.println("全服公告发送失败");
            re = new Result(400, "全服公告发送失败", null);
        }
        return re;
    }

    public Result deleteAllPlatformNotice(Map map) {
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
        int[] temp = platformNoticeDaoImpl.deleteAllPlatformNotice(objectArry);
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
}
