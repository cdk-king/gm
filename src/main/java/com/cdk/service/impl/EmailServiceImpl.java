package com.cdk.service.impl;

import com.cdk.dao.impl.EmailDaoImpl;
import com.cdk.entity.Email;
import com.cdk.result.Result;
import com.cdk.util.ApiHandeler;
import com.cdk.util.HttpRequestUtil;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

@Service
public class EmailServiceImpl extends ApiHandeler {
    private static Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    public EmailDaoImpl emailDaoImpl;

    public Result getEmail(Map map) {
        String gameId = ((map.get("gameId") != null && map.get("gameId") != "") ? map.get("gameId").toString() : "0");
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String emailContent = (map.get("noticeContent") != null ? map.get("noticeContent").toString() : "");
        String strPlatform = (map.get("strPlatform") != null ? map.get("strPlatform").toString() : "");
        String addUser = (map.get("addUser") != null ? map.get("addUser").toString() : "");
        String isPage = (map.get("isPage") != null ? map.get("isPage").toString() : "");
        String StrPageNo = (map.get("pageNo") != null ? map.get("pageNo").toString() : "1");
        String StrPageSize = (map.get("pageSize") != null ? map.get("pageSize").toString() : "5");
        int pageNo = Integer.parseInt(StrPageNo);
        int pageSize = Integer.parseInt(StrPageSize);
        int platformId = Integer.parseInt(strPlatformId);
        int serverId = Integer.parseInt(strPlatformId);
        Result re;
        Email email = new Email();
        email.setPlatformId(platformId);
        email.setServerId(serverId);
        email.setEmailContent(emailContent);
        email.setAddUser(addUser);
        Map<String, Object> JsonMap = emailDaoImpl.getEmail(email, isPage, pageNo, pageSize, strPlatform, gameId);
        if (Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(400, "邮件列表获取失败", "");
        } else {
            re = new Result(200, "邮件列表获取成功", JsonMap);
        }
        return re;
    }

    public Result addEmail(Map map) {
        String gameId = ((map.get("gameId") != null && map.get("gameId") != "") ? map.get("gameId").toString() : "0");
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String strServerId = ((map.get("serverId") != null && map.get("serverId") != "") ? map.get("serverId").toString() : "0");
        String emailTitle = (map.get("emailTitle") != null ? map.get("emailTitle").toString() : "");
        String emailContent = (map.get("emailContent") != null ? map.get("emailContent").toString() : "");
        String sendReason = (map.get("sendReason") != null ? map.get("sendReason").toString() : "");
        String strSendType = (map.get("sendType") != null ? map.get("sendType").toString() : "1");
        String strMinLevel = (map.get("minLevel") != null ? map.get("minLevel").toString() : "0");
        String strMaxLevel = (map.get("maxLevel") != null ? map.get("maxLevel").toString() : "0");
        String strMinVipLevel = (map.get("minVipLevel") != null ? map.get("minVipLevel").toString() : "0");
        String strMaxVipLevel = (map.get("maxVipLevel") != null ? map.get("maxVipLevel").toString() : "0");
        String strMinRegistrationTime = (map.get("minRegistrationTime") != null ? map.get("minRegistrationTime").toString() : "");
        String strMaxRegistrationTime = (map.get("maxRegistrationTime") != null ? map.get("maxRegistrationTime").toString() : "");
        String strIsOnline = (map.get("isOnline") != null ? map.get("isOnline").toString() : "0");
        String strSex = (map.get("sex") != null ? map.get("sex").toString() : "0");
        String playerNameList = (map.get("playerNameList") != null ? map.get("playerNameList").toString() : "");
        String playerAccountList = (map.get("playerAccountList") != null ? map.get("playerAccountList").toString() : "");
        String playerIdList = (map.get("playerIdList") != null ? map.get("playerIdList").toString() : "");
        String addUser = (map.get("addUser") != null ? map.get("addUser").toString() : "");
        int platformId = Integer.parseInt(strPlatformId);
        int serverId = Integer.parseInt(strServerId);
        int sendType = Integer.parseInt(strSendType);
        int minLevel = Integer.parseInt(strMinLevel);
        int maxLevel = Integer.parseInt(strMaxLevel);
        int minVipLevel = Integer.parseInt(strMinVipLevel);
        int maxVipLevel = Integer.parseInt(strMaxVipLevel);
        int isOnline = Integer.parseInt(strIsOnline);
        int sex = Integer.parseInt(strSex);

        Result re;
        Email email = new Email();
        email.setGameId(Integer.parseInt(gameId));
        email.setPlatformId(platformId);
        email.setServerId(serverId);
        email.setEmailTitle(emailTitle);
        email.setEmailContent(emailContent);
        email.setSendReason(sendReason);
        email.setSendType(sendType);
        email.setMinLevel(minLevel);
        email.setMaxLevel(maxLevel);
        email.setMinVipLevel(minVipLevel);
        email.setMaxVipLevel(maxVipLevel);
        email.setIsOnline(isOnline);
        email.setSex(sex);
        email.setPlayerNameList(playerNameList);
        email.setPlayerAccountList(playerAccountList);
        email.setPlayerIdList(playerIdList);
        email.setAddUser(addUser);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        if (!Objects.equals(strMinRegistrationTime, "")) {
            Date minRegistrationTime = formatter.parse(strMinRegistrationTime, pos);
            email.setMinRegistrationTime(minRegistrationTime);
        }
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        pos = new ParsePosition(0);
        if (!Objects.equals(strMaxRegistrationTime, "")) {
            Date maxRegistrationTime = formatter.parse(strMaxRegistrationTime, pos);
            email.setMaxRegistrationTime(maxRegistrationTime);
        }
        int temp = emailDaoImpl.addEmail(email);
        if (temp > 0) {
            logger.debug("邮件添加成功");
            re = new Result(200, "邮件添加成功", null);
        } else {
            logger.debug("邮件添加失败");
            re = new Result(400, "邮件添加失败", null);
        }
        return re;
    }


    public Result editEmail(Map map) {
        String strId = (map.get("id") != null ? map.get("id").toString() : "0");
        String gameId = ((map.get("gameId") != null && map.get("gameId") != "") ? map.get("gameId").toString() : "0");
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String strServerId = ((map.get("serverId") != null && map.get("serverId") != "") ? map.get("serverId").toString() : "0");
        String emailTitle = (map.get("emailTitle") != null ? map.get("emailTitle").toString() : "");
        String emailContent = (map.get("emailContent") != null ? map.get("emailContent").toString() : "");
        String sendReason = (map.get("sendReason") != null ? map.get("sendReason").toString() : "");
        String strSendType = (map.get("sendType") != null ? map.get("sendType").toString() : "1");
        String strMinLevel = (map.get("minLevel") != null ? map.get("minLevel").toString() : "0");
        String strMaxLevel = (map.get("maxLevel") != null ? map.get("maxLevel").toString() : "0");
        String strMinVipLevel = (map.get("minVipLevel") != null ? map.get("minVipLevel").toString() : "0");
        String strMaxVipLevel = (map.get("maxVipLevel") != null ? map.get("maxVipLevel").toString() : "0");
        String strMinRegistrationTime = (map.get("minRegistrationTime") != null ? map.get("minRegistrationTime").toString() : "");
        String strMaxRegistrationTime = (map.get("maxRegistrationTime") != null ? map.get("maxRegistrationTime").toString() : "");
        String strIsOnline = (map.get("isOnline") != null ? map.get("isOnline").toString() : "0");
        String strSex = (map.get("sex") != null ? map.get("sex").toString() : "0");
        String playerNameList = (map.get("playerNameList") != null ? map.get("playerNameList").toString() : "");
        String playerAccountList = (map.get("playerAccountList") != null ? map.get("playerAccountList").toString() : "");
        String playerIdList = (map.get("playerIdList") != null ? map.get("playerIdList").toString() : "");

        String addUser = (map.get("addUser") != null ? map.get("addUser").toString() : "");
        int id = Integer.parseInt(strId);
        int platformId = Integer.parseInt(strPlatformId);
        int serverId = Integer.parseInt(strServerId);
        int sendType = Integer.parseInt(strSendType);
        int minLevel = Integer.parseInt(strMinLevel);
        int maxLevel = Integer.parseInt(strMaxLevel);
        int minVipLevel = Integer.parseInt(strMinVipLevel);
        int maxVipLevel = Integer.parseInt(strMaxVipLevel);
        int isOnline = Integer.parseInt(strIsOnline);
        int sex = Integer.parseInt(strSex);

        Result re;
        Email email = new Email();
        email.setId(id);
        email.setGameId(Integer.parseInt(gameId));
        email.setPlatformId(platformId);
        email.setServerId(serverId);
        email.setEmailTitle(emailTitle);
        email.setEmailContent(emailContent);
        email.setSendReason(sendReason);
        email.setSendType(sendType);
        email.setMinLevel(minLevel);
        email.setMaxLevel(maxLevel);
        email.setMinVipLevel(minVipLevel);
        email.setMaxVipLevel(maxVipLevel);
        email.setIsOnline(isOnline);
        email.setSex(sex);
        email.setPlayerNameList(playerNameList);
        email.setPlayerAccountList(playerAccountList);
        email.setPlayerIdList(playerIdList);
        email.setAddUser(addUser);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        if (!Objects.equals(strMinRegistrationTime, "")) {
            Date minRegistrationTime = formatter.parse(strMinRegistrationTime, pos);
            email.setMinRegistrationTime(minRegistrationTime);
        }
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        pos = new ParsePosition(0);
        if (!Objects.equals(strMaxRegistrationTime, "")) {
            Date maxRegistrationTime = formatter.parse(strMaxRegistrationTime, pos);
            email.setMaxRegistrationTime(maxRegistrationTime);
        }
        int temp = emailDaoImpl.editEmail(email);
        if (temp > 0) {
            logger.debug("邮件编辑成功");
            re = new Result(200, "邮件编辑成功", null);
        } else {
            logger.debug("邮件编辑失败");
            re = new Result(400, "邮件编辑失败", null);
        }
        return re;
    }

    public Result sendEmail(Map map) {
        String strId = (map.get("id") != null ? map.get("id").toString() : "0");
        int id = Integer.parseInt(strId);
        Result re;
        Email email = new Email();
        email.setId(id);
        String gameId = ((map.get("gameId") != null && map.get("gameId") != "") ? map.get("gameId").toString() : "0");
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String strserverId = (map.get("serverId") != null ? map.get("serverId").toString() : "0");
        String emailContent = (map.get("emailContent") != null ? map.get("emailContent").toString() : "");
        String emailTitle = (map.get("emailTitle") != null ? map.get("emailTitle").toString() : "");
        String sendType = (map.get("sendType") != null ? map.get("sendType").toString() : "");
        String playerNameList = (map.get("playerNameList") != null ? map.get("playerNameList").toString() : "");
        String playerIdList = (map.get("playerIdList") != null ? map.get("playerIdList").toString() : "");
        try {
            playerNameList = URLEncoder.encode(playerNameList, "UTF-8");
            emailTitle = URLEncoder.encode(emailTitle, "UTF-8");
            emailContent = URLEncoder.encode(emailContent, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String param = getParam(strPlatformId);
        param += "&Content=" + emailContent;
        if (!Objects.equals(emailTitle, "")) {
            param += "&Title=" + emailTitle;
        }
        if (!Objects.equals(strserverId, "")) {
            param += "&WorldID=" + strserverId;
        }
        if (!Objects.equals(playerNameList, "")) {
            param += "&PlayerName=" + playerNameList;
        }
        if (!Objects.equals(playerIdList, "")) {
            param += "&PlayerID=" + playerIdList;
        }
        if (Objects.equals(sendType, "1")) {
            param += "&IsAllPlayer=0";
        }
        if (Objects.equals(sendType, "2")) {
            param += "&IsAllPlayer=0";
        }
        if (Objects.equals(sendType, "3")) {
            param += "&IsAllPlayer=1";
        }
        String url = "";
        HttpRequestUtil httpRequestUtil = new HttpRequestUtil();
        apiUrl = getApiUrl(gameId, strPlatformId, strserverId);

        if (Objects.equals(apiUrl, "")) {
            return new Result(400, "操作失败,接口不存在", "");
        }

        url = apiUrl + "/UpdatePlayer/Mail";
        String data = httpRequestUtil.sendGet(url, param);

        JSONObject jb = JSONObject.fromObject(data);
        Map resultMap = (Map) jb;
        if (!Objects.equals(resultMap.get("Result"), 1)) {
            int temp = emailDaoImpl.sendEmail(email, 2);
            re = new Result(400, "邮件发送失败", data);
        } else {
            int temp = emailDaoImpl.sendEmail(email, 1);
            if (temp > 0) {
                re = new Result(200, "邮件发送成功", data);
            } else {
                re = new Result(200, "邮件发送成功，信息录入失败", data);
            }
        }
        return re;
    }

    public Result deleteEmail(Map map) {
        String strId = (map.get("id") != null ? map.get("id").toString() : "0");
        int id = Integer.parseInt(strId);
        Result re;
        Email email = new Email();
        email.setId(id);

        int temp = emailDaoImpl.deleteEmail(email);
        if (temp > 0) {
            logger.debug("邮件删除成功");
            re = new Result(200, "邮件删除成功", null);
        } else {
            logger.debug("邮件删除失败");
            re = new Result(400, "邮件删除失败", null);
        }
        return re;
    }
}
