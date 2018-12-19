package com.cdk.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cdk.cache.CacheListener;
import com.cdk.cache.CacheManagerImpl;
import com.cdk.dao.impl.PlatformNoticeDaoImpl;
import com.cdk.entity.PlatformNotice;
import com.cdk.result.Result;
import com.cdk.util.ApiHandeler;
import com.cdk.util.HttpRequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

@Service
public class PlatformNoticeServiceImpl extends ApiHandeler {
    private static Logger logger = Logger.getLogger(String.valueOf(PlatformNoticeServiceImpl.class));
    public static final String Divider = "############################";
    public static final String Split = "----------------";

    @Autowired
    public PlatformNoticeDaoImpl platformNoticeDaoImpl;
    @Autowired
    public UtilsServiceImpl utilsServiceImpl;
    //@Autowired   @Service实现自动装填
    //public CacheManagerImpl cacheManagerImpl;
    public CacheManagerImpl cacheManagerImpl = new CacheManagerImpl();

    public CacheListener cacheListener;

    public Result getPlatformNotice(Map map) {
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String strPlatform = (map.get("strPlatform") != null ? map.get("strPlatform").toString() : "");
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
        Map<String, Object> JsonMap = platformNoticeDaoImpl.getPlatformNotice(platformNotice, isPage, pageNo, pageSize, strPlatform);
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
        String propList = (map.get("propList") != null ? map.get("propList").toString() : "");
        String moneyList = (map.get("moneyList") != null ? map.get("moneyList").toString() : "");
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
        platformNotice.setPropList(propList);
        platformNotice.setMoneyList(moneyList);

        Object a = new Object();


        int temp = platformNoticeDaoImpl.addPlatformNotice(platformNotice);
        if (temp > 0) {
            logger.info("全服公告添加成功");
            PlatformNotice data = (PlatformNotice) cacheManagerImpl.getCacheDataByKey("Notice");
            String content = data.getNoticeContent();
            re = new Result(200, "全服公告添加成功", null);
        } else {
            logger.info("全服公告添加失败");
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
        String propList = (map.get("propList") != null ? map.get("propList").toString() : "");
        String moneyList = (map.get("moneyList") != null ? map.get("moneyList").toString() : "");
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
        platformNotice.setPropList(propList);
        platformNotice.setMoneyList(moneyList);


        int temp = platformNoticeDaoImpl.editPlatformNotice(platformNotice);
        if (temp > 0) {
            logger.info("全服公告编辑成功");
            re = new Result(200, "全服公告编辑成功", null);
        } else {
            logger.info("全服公告编辑失败");
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
            logger.info("全服公告删除成功");
            re = new Result(200, "全服公告删除成功", null);
        } else {
            logger.info("全服公告删除失败");
            re = new Result(400, "全服公告删除失败", null);
        }
        return re;
    }

    //todo
    public Result reSendNotice(Map map) {
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String strServerList = (map.get("serverList") != null ? map.get("serverList").toString() : "");
        String noticeTitle = (map.get("noticeTitle") != null ? map.get("noticeTitle").toString() : "");
        String noticeContent = (map.get("noticeContent") != null ? map.get("noticeContent").toString() : "");
        String propList = (map.get("propList") != null ? map.get("propList").toString() : "");
        String moneyList = (map.get("moneyList") != null ? map.get("moneyList").toString() : "");
        String startDatetime = (map.get("startDatetime") != null ? map.get("startDatetime").toString() : "");
        String strId = (map.get("id") != null ? map.get("id").toString() : "0");

        String[] serverArray = strServerList.split(",");
        Long time = Math.abs(new Date().getTime() / 1000L);
        String param = getParam(strPlatformId);

        try {
            //中文转码
            noticeTitle = URLEncoder.encode(noticeTitle, "UTF-8");
            noticeContent = URLEncoder.encode(noticeContent, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        param += "&Title=" + noticeTitle;
        param += "&Content=" + noticeContent;

        if (!Objects.equals(propList, "")) {
            param += "&ItemList=" + propList;
        }
        if (!Objects.equals(moneyList, "")) {
            param += "&Money=" + moneyList;
        }
        param += "&StartTime=" + time;
        List<Map<String, String>> urlList = utilsServiceImpl.getServerUrl(strServerList, strPlatformId);
        String url = "";
        HttpRequestUtil httpRequestUtil = new HttpRequestUtil();
        String datas = "";
        String error = "";
        for (int i = 0; i < urlList.size(); i++) {
            apiUrl = getApiUrl(urlList.get(i));
            url = apiUrl + "/notice/sendAllNotice";
            logger.info(url);
            logger.info(param);
            String data = httpRequestUtil.sendGet(url, param);
            JSONObject jo = JSONObject.parseObject(data);
            data = jo.getString("Result");
            //除了1，都是失败
            datas += data + ",";
            if (!Objects.equals(data, "1")) {
                error += serverArray[i] + ",";
            }
        }
        Result re;
        if (datas.length() > 0) {
            datas = datas.substring(0, datas.length() - 1);

        }
        if (error.length() > 0) {
            error = error.substring(0, error.length() - 1);
            int temp = platformNoticeDaoImpl.changeNoticeSendState(strId, error, 2, time);
        }
        logger.info("datas:" + datas);
        logger.info("error:" + error);
        Map<String, String> remap = new HashMap<>();
        remap.put("codes", datas);
        remap.put("error", error);

        re = new Result(200, "公告已发送", remap);

        return re;
    }

    public Result sendPlatformNotice(Map map) {
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String strServerList = (map.get("serverList") != null ? map.get("serverList").toString() : "");
        String noticeTitle = (map.get("noticeTitle") != null ? map.get("noticeTitle").toString() : "");
        String noticeContent = (map.get("noticeContent") != null ? map.get("noticeContent").toString() : "");
        String propList = (map.get("propList") != null ? map.get("propList").toString() : "");
        String moneyList = (map.get("moneyList") != null ? map.get("moneyList").toString() : "");
        String startDatetime = (map.get("startDatetime") != null ? map.get("startDatetime").toString() : "");
        String strId = (map.get("id") != null ? map.get("id").toString() : "0");
        logger.info(propList);
        logger.info(strServerList);
        logger.info(startDatetime);
        //        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        //        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        String[] serverArray = strServerList.split(",");
        Long time = Math.abs(new Date().getTime() / 1000L);
        String param = getParam(strPlatformId);

        try {
            //中文转码
            noticeTitle = URLEncoder.encode(noticeTitle, "UTF-8");
            noticeContent = URLEncoder.encode(noticeContent, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        param += "&Title=" + noticeTitle;
        param += "&Content=" + noticeContent;

        if (!Objects.equals(propList, "")) {
            param += "&ItemList=" + propList;
        }
        if (!Objects.equals(moneyList, "")) {
            param += "&Money=" + moneyList;
        }
        param += "&StartTime=" + time;
        List<Map<String, String>> urlList = utilsServiceImpl.getServerUrl(strServerList, strPlatformId);
        logger.info(urlList.toString());
        String url = "";
        HttpRequestUtil httpRequestUtil = new HttpRequestUtil();
        String datas = "";
        String error = "";
        //循环服务器列表
        for (int i = 0; i < urlList.size(); i++) {
            if (!Objects.equals(serverArray[i], "")) {
                param += "&WorldID=" + serverArray[i];
            } else {
                return new Result(400, "公告发送失败，无效的服务器", "");
            }
            apiUrl = getApiUrl(urlList.get(i));
            url = apiUrl + "/notice/sendAllNotice";
            logger.info(url);
            logger.info(param);

            String data = httpRequestUtil.sendGet(url, param);
            //返回结果判空处理
            if (!Objects.equals(data, null)) {
                JSONObject jo = JSONObject.parseObject(data);
                if (data.indexOf("Result") > 0) {
                    data = jo.getString("Result");
                } else {
                    data = "-1";
                }
            } else {
                data = "-1";
            }
            //除了1，都是失败
            datas += data + ",";
            if (!Objects.equals(data, "1")) {
                //记录错误服务器id
                error += serverArray[i] + ",";
            }
        }
        Result re;
        if (datas.length() > 0) {
            datas = datas.substring(0, datas.length() - 1);
        }
        if (error.length() > 0) {
            error = error.substring(0, error.length() - 1);
            int temp = platformNoticeDaoImpl.changeNoticeSendState(strId, error, 2, time);

        } else {
            int temp = platformNoticeDaoImpl.changeNoticeSendState(strId, "", 1, time);
        }
        logger.info("datas:" + datas);
        logger.info("error:" + error);
        Map<String, String> remap = new HashMap<>();
        remap.put("codes", datas);
        remap.put("error", error);

        re = new Result(200, "公告已发送", remap);

        return re;
    }

    public Result deleteAllPlatformNotice(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        logger.info("id：" + id);
        if (Objects.equals(id, "")) {
            logger.info("无任何批量删除操作");
            return new Result(400, "无任何批量删除操作", null);
        }

        String[] objectArry = id.split(",");
        logger.info("ObjectArry：" + objectArry);
        Result re;
        String sql[] = new String[objectArry.length];
        int[] temp = platformNoticeDaoImpl.deleteAllPlatformNotice(objectArry);
        if (temp.length != 0) {
            logger.info("公告批量删除成功");
            re = new Result(200, "公告批量删除成功", null);
        } else if (objectArry.length == 0) {
            logger.info("无任何删除操作");
            re = new Result(400, "无任何删除操作", null);
        } else {
            logger.info("公告批量删除失败");
            re = new Result(400, "公告批量删除失败", null);
        }
        return re;
    }

    public Result getLastNotice(Map map) {
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        Result re;
        Map<String, String> lastGonggao = platformNoticeDaoImpl.getLastNotice(strPlatformId);
        if (lastGonggao.size() != 0) {
            re = new Result(200, "最新的公告获取成功", lastGonggao);
        } else {
            re = new Result(200, "最新的公告获取失败", lastGonggao);
        }
        return re;
    }
}