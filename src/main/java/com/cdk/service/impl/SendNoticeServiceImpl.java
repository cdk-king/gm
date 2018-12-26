package com.cdk.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.cdk.dao.impl.SendNoticeDaoImpl;
import com.cdk.entity.Notice;
import com.cdk.result.Result;
import com.cdk.util.ApiHandeler;
import com.cdk.util.HttpRequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

@Service
public class SendNoticeServiceImpl extends ApiHandeler {
    private static Logger logger = Logger.getLogger(String.valueOf(SendNoticeServiceImpl.class));

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
            logger.info("公告添加成功");
            re = new Result(200, "公告添加成功", null);
        } else {
            logger.info("公告添加失败");
            re = new Result(400, "公告添加失败", null);
        }
        return re;
    }

    public Result getNotice(Map map) {
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String strPlatform = (map.get("strPlatform") != null ? map.get("strPlatform").toString() : "");
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
        Map<String, Object> JsonMap = sendNoticeDaoImpl.getNotice(notice, isPage, pageNo, pageSize, strPlatform);
        if (Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(400, "公告列表获取失败", "");
        } else {
            re = new Result(200, "公告列表获取成功", JsonMap);
        }
        return re;
    }


    public Result deleteAllNotice(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        if (Objects.equals(id, "")) {
            logger.info("无任何批量删除操作");
            return new Result(400, "无任何批量删除操作", null);
        }

        String[] objectArry = id.split(",");
        Result re;
        String sql[] = new String[objectArry.length];
        int[] temp = sendNoticeDaoImpl.deleteAllNotice(objectArry);
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

    public Result sendNotice(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "0");
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String strServerList = (map.get("serverList") != null ? map.get("serverList").toString() : "");
        String Content = (map.get("Content") != null ? map.get("Content").toString() : "");
        String sendType = (map.get("sendType") != null ? map.get("sendType").toString() : "");
        String noticeType = (map.get("noticeType") != null ? map.get("noticeType").toString() : "");
        String[] serverArray = strServerList.split(",");

        try {
            //中文转码
            Content = URLEncoder.encode(Content, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String param = getParam(strPlatformId);
        param += "&Content=" + Content;

        if (!Objects.equals(sendType, "")) {
            param += "&sendType=" + sendType;
        }
        if (!Objects.equals(noticeType, "")) {
            param += "&noticeType=" + noticeType;
        }
        List<Map<String, String>> urlList = utilsServiceImpl.getServerUrl(strServerList, strPlatformId);
        String url = "";
        HttpRequestUtil httpRequestUtil = new HttpRequestUtil();
        String datas = "";
        String error = "";
        //循环服务器列表
        for (int i = 0; i < urlList.size(); i++) {
            if (!Objects.equals(serverArray[i], "")) {
                param += "&WorldID=" + serverArray[i];
            } else {
                return new Result(400, "广播发送失败，无效的服务器", "");
            }
            apiUrl = getApiUrl(urlList.get(i));
            url = apiUrl + "/notice/sendBroadcast";
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

        if (datas.length() > 0) {
            datas = datas.substring(0, datas.length() - 1);

        }
        if (error.length() > 0) {
            error = error.substring(0, error.length() - 1);

        }
        logger.info("error:" + error);
        Result re;

        if (error.length() > 0 || datas.length() == 0) {
            int temp = sendNoticeDaoImpl.sendNoticeToError(id);
            logger.info("广播发送失败");
            re = new Result(400, "广播发送失败", error);

        } else {
            int temp = sendNoticeDaoImpl.sendNotice(id);
            logger.info("广播发送成功");
            re = new Result(200, "广播发送成功", datas);
        }
        return re;
    }


}
