package com.cdk.service.impl;

import com.cdk.dao.impl.ApplyPropDaoImpl;
import com.cdk.entity.ApplyProp;
import com.cdk.result.Result;
import com.cdk.util.ApiHandeler;
import com.cdk.util.HttpRequestUtil;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ApplyPropServiceImpl extends ApiHandeler {
    public static final String Divider = "############################";
    public static final String Split = "----------------";

    @Autowired
    public ApplyPropDaoImpl applyPropDaoImpl;

    public Result getPlayerTypeList() {
        Result re;
        Map<String, Object> JsonMap = applyPropDaoImpl.getPlayerTypeList();
        if (Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(400, "玩家类别列表获取失败", "");
        } else {
            re = new Result(200, "玩家类别列表获取成功", JsonMap);
        }
        return re;
    }

    public Result getMoneyTypeList(Map map) {
        String strGameId = ((map.get("gameId") != null && map.get("gameId") != "") ? map.get("gameId").toString() : "0");
        int gameId = Integer.parseInt(strGameId);
        Result re;
        Map<String, Object> JsonMap = applyPropDaoImpl.getMoneyTypeList(gameId);
        if (Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(400, "货币类别列表获取失败", "");
        } else {
            re = new Result(200, "货币类别列表获取成功", JsonMap);
        }
        return re;
    }

    public Result getPropQualityList(Map map) {
        String strGameId = ((map.get("gameId") != null && map.get("gameId") != "") ? map.get("gameId").toString() : "0");
        int gameId = Integer.parseInt(strGameId);
        Result re;
        Map<String, Object> JsonMap = applyPropDaoImpl.getPropQualityList(gameId);
        if (Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(400, "品质类别列表获取失败", "");
        } else {
            re = new Result(200, "品质类别列表获取成功", JsonMap);
        }
        return re;
    }


    public Result confirmApply(Map map) {
        String strId = ((map.get("id") != null && map.get("id") != "") ? map.get("id").toString() : "0");
        String platformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String serverId = ((map.get("serverId") != null && map.get("serverId") != "") ? map.get("serverId").toString() : "0");
        String IsAllPlayer = ((map.get("IsAllPlayer") != null && map.get("IsAllPlayer") != "") ? map.get("IsAllPlayer").toString() : "0");
        String PlayerID = ((map.get("PlayerID") != null) ? map.get("PlayerID").toString() : "");
        String PlayerName = ((map.get("PlayerName") != null) ? map.get("PlayerName").toString() : "");
        String Title = ((map.get("Title") != null) ? map.get("Title").toString() : "");
        String Content = ((map.get("Content") != null) ? map.get("Content").toString() : "");
        String ItemList = ((map.get("ItemList") != null) ? map.get("ItemList").toString() : "");
        String Money = ((map.get("Money") != null) ? map.get("Money").toString() : "");
        System.out.println(ItemList);
        System.out.println(Money);
        int id = Integer.parseInt(strId);

        long time = Math.abs(System.currentTimeMillis() / 1000);
        String strTime = time + "";
        String operator = platformId;
        String key = MANAGEMENT_KEY;
        String sign = getSign(strTime, operator, key);

        String param = TIME_KEY + time + OPERATOR_KEY + operator + "&sign=" + sign;
        if (!Objects.equals(serverId, "")) {
            param += "&WorldID=" + serverId;
        }
        param += "&IsAllPlayer=" + IsAllPlayer;

        if (!Objects.equals(PlayerID, "")) {
            param += "&PlayerID=" + PlayerID;
        } else {
            param += "&PlayerName=" + PlayerName;
        }
        try {
            //中文转码
            Title = URLEncoder.encode(Title, "UTF-8");
            Content = URLEncoder.encode(Content, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        param += "&Title=" + Title;
        param += "&Content=" + Content;

        if (!Objects.equals(ItemList, "")) {
            param += "&ItemList=" + ItemList;
        }
        if (!Objects.equals(Money, "")) {
            param += "&Money=" + Money;
        }

        List<Map<String, String>> serverUrl = utilsServiceImpl.getServerUrl(serverId, platformId);
        apiUrl = http + serverUrl.get(0).get("url").split(":")[0];

        String url = apiUrl + "/UpdatePlayer/Mail";
        System.out.println(url);

        System.out.println(param);
        HttpRequestUtil httpRequestUtil = new HttpRequestUtil();
        String data = httpRequestUtil.sendGet(url, param);
        System.out.println(data);

        Result re;
        JSONObject jb = JSONObject.fromObject(data);
        Map resultMap = (Map) jb;
        System.out.println(resultMap);
        if (!Objects.equals(resultMap.get("Result"), 1)) {
            int temp = applyPropDaoImpl.changeApplyState(id, 2);
            re = new Result(400, "道具申请邮件发送失败", data);
        } else {
            int temp = applyPropDaoImpl.changeApplyState(id, 1);
            if (temp > 0) {
                re = new Result(200, "道具申请邮件发送成功", data);
            } else {
                re = new Result(200, "道具申请邮件发送成功，信息录入失败", data);
            }
        }
        return re;
    }


    public Result getApplyProp(Map map) {
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String strServerId = ((map.get("serverId") != null && map.get("serverId") != "") ? map.get("serverId").toString() : "0");
        String strPlatform = (map.get("strPlatform") != null ? map.get("strPlatform").toString() : "");
        String strPropList = (map.get("propList") != null ? map.get("propList").toString() : "");
        String releaseTitle = (map.get("releaseTitle") != null ? map.get("releaseTitle").toString() : "");
        String releaseContent = (map.get("releaseContent") != null ? map.get("releaseContent").toString() : "");
        String strApplyType = (map.get("applyType") != null ? map.get("applyType").toString() : "0");
        String strPlayerType = (map.get("playerType") != null ? map.get("playerType").toString() : "0");
        String strSendState = (map.get("sendState") != null ? map.get("sendState").toString() : "0");
        String isPage = (map.get("isPage") != null ? map.get("isPage").toString() : "");
        String StrPageNo = (map.get("pageNo") != null ? map.get("pageNo").toString() : "1");
        String StrPageSize = (map.get("pageSize") != null ? map.get("pageSize").toString() : "5");
        int pageNo = Integer.parseInt(StrPageNo);
        int pageSize = Integer.parseInt(StrPageSize);
        int platformId = Integer.parseInt(strPlatformId);
        int serverId = Integer.parseInt(strServerId);

        Result re;
        ApplyProp applyProp = new ApplyProp();
        applyProp.setPlatformId(platformId);
        applyProp.setServerId(serverId);


        Map<String, Object> JsonMap = applyPropDaoImpl.getApplyProp(applyProp, isPage, pageNo, pageSize, strPlatform);
        if (Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(400, "道具申请列表获取失败", "");
        } else {
            re = new Result(200, "道具申请列表获取成功", JsonMap);
        }
        return re;
    }

    public Result addApplyProp(Map map) {
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String strServerId = (map.get("serverId") != null ? map.get("serverId").toString() : "");
        String strApplyType = (map.get("applyType") != null ? map.get("applyType").toString() : "0");
        String releaseTitle = (map.get("releaseTitle") != null ? map.get("releaseTitle").toString() : "");
        String releaseContent = (map.get("releaseContent") != null ? map.get("releaseContent").toString() : "");
        String propList = (map.get("propList") != null ? map.get("propList").toString() : "");

        String playerNameList = (map.get("playerNameList") != null ? map.get("playerNameList").toString() : "");
        String playerAccountList = (map.get("playerAccountList") != null ? map.get("playerAccountList").toString() : "");
        String playerIdList = (map.get("playerIdList") != null ? map.get("playerIdList").toString() : "");

        String strPlayerType = (map.get("playerType") != null ? map.get("playerType").toString() : "0");
        String strApplyUser = (map.get("applyUser") != null ? map.get("applyUser").toString() : "0");
        String applyReason = (map.get("applyReason") != null ? map.get("applyReason").toString() : "");
        String strAddUser = (map.get("addUser") != null ? map.get("addUser").toString() : "0");
        String strMoneyList = (map.get("moneyList") != null ? map.get("moneyList").toString() : "0");

        int platformId = Integer.parseInt(strPlatformId);
        int serverId = Integer.parseInt(strServerId);
        int applyType = Integer.parseInt(strApplyType);
        int playerType = Integer.parseInt(strPlayerType);
        int addUser = Integer.parseInt(strAddUser);

        Result re;
        ApplyProp applyProp = new ApplyProp();
        applyProp.setPlatformId(platformId);
        applyProp.setServerId(serverId);
        applyProp.setApplyType(applyType);
        applyProp.setPropList(propList);
        applyProp.setPlayerAccountList(playerAccountList);
        applyProp.setPlayerNameList(playerNameList);
        applyProp.setPlayerIdList(playerIdList);
        applyProp.setApplyReason(applyReason);
        applyProp.setReleaseTitle(releaseTitle);
        applyProp.setReleaseContent(releaseContent);
        applyProp.setAddUser(addUser);
        applyProp.setApplyUser(strAddUser);
        applyProp.setPlayerType(playerType);
        applyProp.setMoneyList(strMoneyList);


        int temp = applyPropDaoImpl.addApplyProp(applyProp);
        if (temp > 0) {
            System.out.println("道具申请添加成功");
            re = new Result(200, "道具申请添加成功", null);
        } else {
            System.out.println("道具申请添加失败");
            re = new Result(400, "道具申请添加失败", null);
        }
        return re;
    }

    public Result editApplyProp(Map map) {
        String strId = ((map.get("id") != null && map.get("id") != "") ? map.get("id").toString() : "0");
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String strServerId = (map.get("serverId") != null ? map.get("serverId").toString() : "");
        String strApplyType = (map.get("applyType") != null ? map.get("applyType").toString() : "0");
        String releaseTitle = (map.get("releaseTitle") != null ? map.get("releaseTitle").toString() : "");
        String releaseContent = (map.get("releaseContent") != null ? map.get("releaseContent").toString() : "");
        String propList = (map.get("propList") != null ? map.get("propList").toString() : "");

        String playerNameList = (map.get("playerNameList") != null ? map.get("playerNameList").toString() : "");
        String playerAccountList = (map.get("playerAccountList") != null ? map.get("playerAccountList").toString() : "");
        String playerIdList = (map.get("playerIdList") != null ? map.get("playerIdList").toString() : "");

        String strPlayerType = (map.get("playerType") != null ? map.get("playerType").toString() : "0");
        String strApplyUser = (map.get("applyUser") != null ? map.get("applyUser").toString() : "0");
        String applyReason = (map.get("applyReason") != null ? map.get("applyReason").toString() : "");
        String strAddUser = (map.get("addUser") != null ? map.get("addUser").toString() : "0");
        String strMoneyList = (map.get("moneyList") != null ? map.get("moneyList").toString() : "0");

        int id = Integer.parseInt(strId);
        int platformId = Integer.parseInt(strPlatformId);
        int serverId = Integer.parseInt(strServerId);
        int applyType = Integer.parseInt(strApplyType);
        int playerType = Integer.parseInt(strPlayerType);
        int addUser = Integer.parseInt(strAddUser);

        Result re;
        ApplyProp applyProp = new ApplyProp();
        applyProp.setId(id);
        applyProp.setPlatformId(platformId);
        applyProp.setServerId(serverId);
        applyProp.setApplyType(applyType);
        applyProp.setPropList(propList);
        applyProp.setPlayerAccountList(playerAccountList);
        applyProp.setPlayerNameList(playerNameList);
        applyProp.setPlayerIdList(playerIdList);
        applyProp.setApplyReason(applyReason);
        applyProp.setReleaseTitle(releaseTitle);
        applyProp.setReleaseContent(releaseContent);
        applyProp.setAddUser(addUser);
        applyProp.setApplyUser(strAddUser);
        applyProp.setPlayerType(playerType);
        applyProp.setMoneyList(strMoneyList);


        int temp = applyPropDaoImpl.editApplyProp(applyProp);
        if (temp > 0) {
            System.out.println("道具申请修改成功");
            re = new Result(200, "道具申请修改成功", null);
        } else {
            System.out.println("道具申请修改失败");
            re = new Result(400, "道具申请修改失败", null);
        }
        return re;
    }

    public Result confirmApplyProp(Map map) {
        String strId = ((map.get("id") != null && map.get("id") != "") ? map.get("id").toString() : "0");
        String strAddUser = (map.get("addUser") != null ? map.get("addUser").toString() : "0");

        int id = Integer.parseInt(strId);
        int addUser = Integer.parseInt(strAddUser);

        Result re;
        ApplyProp applyProp = new ApplyProp();
        applyProp.setId(id);
        applyProp.setAddUser(addUser);

        int temp = applyPropDaoImpl.confirmApplyProp(applyProp);
        if (temp > 0) {
            System.out.println("道具申请审核通过成功");
            re = new Result(200, "道具申请审核通过成功", null);
        } else {
            System.out.println("道具申请审核通过失败");
            re = new Result(400, "道具申请审核通过失败", null);
        }
        return re;
    }

    public Result notConfirmApplyProp(Map map) {
        String strId = ((map.get("id") != null && map.get("id") != "") ? map.get("id").toString() : "0");
        String strAddUser = (map.get("addUser") != null ? map.get("addUser").toString() : "0");

        int id = Integer.parseInt(strId);
        int addUser = Integer.parseInt(strAddUser);

        Result re;
        ApplyProp applyProp = new ApplyProp();
        applyProp.setId(id);
        applyProp.setAddUser(addUser);

        int temp = applyPropDaoImpl.notConfirmApplyProp(applyProp);
        if (temp > 0) {
            System.out.println("道具申请审核不通过成功");
            re = new Result(200, "道具申请审核不通过成功", null);
        } else {
            System.out.println("道具申请审核不通过失败");
            re = new Result(400, "道具申请审核不通过失败", null);
        }
        return re;
    }

    public Result deleteApplyProp(Map map) {
        String strId = ((map.get("id") != null && map.get("id") != "") ? map.get("id").toString() : "0");

        int id = Integer.parseInt(strId);

        Result re;
        ApplyProp applyProp = new ApplyProp();
        applyProp.setId(id);

        int temp = applyPropDaoImpl.deleteApplyProp(applyProp);
        if (temp > 0) {
            System.out.println("道具申请删除成功");
            re = new Result(200, "道具申请删除成功", null);
        } else {
            System.out.println("道具申请删除失败");
            re = new Result(400, "道具申请删除失败", null);
        }
        return re;
    }

    public Result deleteAllApplyProp(Map map) {
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
        int[] temp = applyPropDaoImpl.deleteAllApplyProp(objectArry);
        if (temp.length != 0) {
            System.out.println("道具申请批量删除成功");
            re = new Result(200, "道具申请批量删除成功", null);
        } else if (objectArry.length == 0) {
            System.out.println("无任何删除操作");
            re = new Result(400, "无任何删除操作", null);
        } else {
            System.out.println("道具申请批量删除失败");
            re = new Result(400, "道具申请批量删除失败", null);
        }
        return re;
    }

}
