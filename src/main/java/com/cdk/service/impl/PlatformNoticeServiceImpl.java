package com.cdk.service.impl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import com.alibaba.fastjson.JSONObject;
import com.cdk.dao.impl.PlatformNoticeDaoImpl;
import com.cdk.entity.PlatformNotice;
import com.cdk.result.Result;
import com.cdk.util.ApiHandeler;
import com.cdk.util.HttpRequestUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import javax.annotation.PostConstruct;

@Service
public class PlatformNoticeServiceImpl extends ApiHandeler {
    private static Logger logger = LoggerFactory.getLogger(PlatformNoticeServiceImpl.class);

    @Autowired
    public PlatformNoticeDaoImpl platformNoticeDaoImpl;
    @Autowired
    public UtilsServiceImpl utilsServiceImpl;

    private LoadingCache<String, Map<String, String>> noticeCache;

    @PostConstruct
    public void initCache() {
        noticeCache = CacheBuilder.newBuilder().concurrencyLevel(4).maximumSize(4096).build(new CacheLoader<String, Map<String, String>>() {
            @Override
            public Map<String, String> load(String s) {
                return platformNoticeDaoImpl.getLastNotice(s);
            }
        });
    }

    public Result getPlatformNotice(Map map) {
        String gameId = ((map.get("gameId") != null && map.get("gameId") != "") ? map.get("gameId").toString() : "0");
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
        Map<String, Object> JsonMap = platformNoticeDaoImpl.getPlatformNotice(platformNotice, isPage, pageNo, pageSize, strPlatform, gameId);
        if (Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(400, "全服公告列表获取失败", "");
        } else {
            re = new Result(200, "全服公告列表获取成功", JsonMap);
        }
        return re;
    }

    public Result addPlatformNotice(Map map) {
        String gameId = ((map.get("gameId") != null && map.get("gameId") != "") ? map.get("gameId").toString() : "0");
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
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        pos = new ParsePosition(0);
        Date endDatetime = formatter.parse(strEndDatetime, pos);

        Result re;
        PlatformNotice platformNotice = new PlatformNotice();
        platformNotice.setGameId(Integer.parseInt(gameId));
        platformNotice.setPlatformId(platformId);
        platformNotice.setServerList(strServerList);
        platformNotice.setNoticeTitle(noticeTitle);
        platformNotice.setNoticeContent(noticeContent);
        platformNotice.setAddUser(addUser);
        platformNotice.setStartDatetime(startDatetime);
        platformNotice.setEndDatetime(endDatetime);
        platformNotice.setPropList(propList);
        platformNotice.setMoneyList(moneyList);
        int temp = platformNoticeDaoImpl.addPlatformNotice(platformNotice);
        if (temp > 0) {
            logger.debug("全服公告添加成功");
            re = new Result(200, "全服公告添加成功", null);
            noticeCache.invalidate(strPlatformId);
        } else {
            logger.debug("全服公告添加失败");
            re = new Result(400, "全服公告添加失败", null);
        }
        return re;
    }

    public Result editPlatformNotice(Map map) {
        String strId = (map.get("id") != null ? map.get("id").toString() : "0");
        String gameId = ((map.get("gameId") != null && map.get("gameId") != "") ? map.get("gameId").toString() : "0");
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
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        pos = new ParsePosition(0);
        Date endDatetime = formatter.parse(strEndDatetime, pos);

        Result re;
        PlatformNotice platformNotice = new PlatformNotice();
        platformNotice.setId(id);
        platformNotice.setGameId(Integer.parseInt(gameId));
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
            logger.debug("全服公告编辑成功");
            re = new Result(200, "全服公告编辑成功", null);
            noticeCache.invalidate(strPlatformId);
        } else {
            logger.debug("全服公告编辑失败");
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
            logger.debug("全服公告删除成功");
            re = new Result(200, "全服公告删除成功", null);
            noticeCache.invalidateAll();
        } else {
            logger.debug("全服公告删除失败");
            re = new Result(400, "全服公告删除失败", null);
        }
        return re;
    }

    //todo
    public Result reSendNotice(Map map) {
        String gameId = ((map.get("gameId") != null && map.get("gameId") != "") ? map.get("gameId").toString() : "0");
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String strServerList = (map.get("serverList") != null ? map.get("serverList").toString() : "");
        String noticeTitle = (map.get("noticeTitle") != null ? map.get("noticeTitle").toString() : "");
        String noticeContent = (map.get("noticeContent") != null ? map.get("noticeContent").toString() : "");
        String propList = (map.get("propList") != null ? map.get("propList").toString() : "");
        String moneyList = (map.get("moneyList") != null ? map.get("moneyList").toString() : "");
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
        List<Map<String, String>> urlList = utilsServiceImpl.getServerUrl(strServerList, strPlatformId, gameId);
        String url = "";
        HttpRequestUtil httpRequestUtil = new HttpRequestUtil();
        String datas = "";
        String error = "";
        for (int i = 0; i < urlList.size(); i++) {
            apiUrl = getApiUrl(urlList.get(i));
            url = apiUrl + "/notice/sendAllNotice";
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
        Map<String, String> remap = new HashMap<>();
        remap.put("codes", datas);
        remap.put("error", error);
        re = new Result(200, "公告已发送", remap);
        return re;
    }

    public Result sendPlatformNotice(Map map) {
        String gameId = ((map.get("gameId") != null && map.get("gameId") != "") ? map.get("gameId").toString() : "0");
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
        List<Map<String, String>> urlList = utilsServiceImpl.getServerUrl(strServerList, strPlatformId, gameId);
        String url = "";
        HttpRequestUtil httpRequestUtil = new HttpRequestUtil();
        String datas = "";
        String error = "";
        for (int i = 0; i < urlList.size(); i++) {
            if (!Objects.equals(serverArray[i], "")) {
                param += "&WorldID=" + serverArray[i];
            } else {
                int temp = platformNoticeDaoImpl.changeNoticeSendState(strId, error, 2, time);
                return new Result(400, "公告发送失败，无效的服务器", "");
            }
            apiUrl = getApiUrl(urlList.get(i));

            if (Objects.equals(apiUrl, "")) {
                int temp = platformNoticeDaoImpl.changeNoticeSendState(strId, error, 2, time);
                return new Result(400, "操作失败,接口不存在", "");
            }

            url = apiUrl + "/notice/sendAllNotice";
            logger.debug(url);
            String data = httpRequestUtil.sendGet(url, param);
            //返回结果判空处理
            if (!Objects.equals(data, "") && !Objects.equals(data, null)) {
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
        if (urlList.size() == 0) {
            re = new Result(400, "公告发送失败", "");
            return re;
        } else {
            if (datas.length() > 0) {
                datas = datas.substring(0, datas.length() - 1);
            }
            if (error.length() > 0) {
                error = error.substring(0, error.length() - 1);
                int temp = platformNoticeDaoImpl.changeNoticeSendState(strId, error, 2, time);

            } else {
                int temp = platformNoticeDaoImpl.changeNoticeSendState(strId, "", 1, time);
            }
        }

        Map<String, String> remap = new HashMap<>();
        remap.put("codes", datas);
        remap.put("error", error);
        re = new Result(200, "公告已发送", remap);
        return re;
    }

    public Result deleteAllPlatformNotice(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        if (Objects.equals(id, "")) {
            logger.debug("无任何批量删除操作");
            return new Result(400, "无任何批量删除操作", null);
        }
        String[] objectArry = id.split(",");
        Result re;
        int[] temp = platformNoticeDaoImpl.deleteAllPlatformNotice(objectArry);
        if (temp.length != 0) {
            logger.debug("公告批量删除成功");
            re = new Result(200, "公告批量删除成功", null);
            noticeCache.invalidateAll();
        } else if (objectArry.length == 0) {
            logger.debug("无任何删除操作");
            re = new Result(400, "无任何删除操作", null);
        } else {
            logger.debug("公告批量删除失败");
            re = new Result(400, "公告批量删除失败", null);
        }
        return re;
    }

    public Result getLastNotice(String str) {
        Result re;
        Map<String, String> lastGonggao = noticeCache.getUnchecked(str);
        if (lastGonggao.size() != 0) {
            re = new Result(200, "最新的公告获取成功", lastGonggao);
        } else {
            re = new Result(400, "最新的公告获取失败", lastGonggao);
        }
        return re;
    }
}
