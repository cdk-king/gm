package com.cdk.service.impl;

import com.cdk.dao.impl.ApplyPropDaoImpl;
import com.cdk.entity.ApplyProp;
import com.cdk.result.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class ApplyPropServiceImpl {
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

    public Result getApplyProp(Map map) {
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String strServerId = ((map.get("serverId") != null && map.get("serverId") != "") ? map.get("serverId").toString() : "0");
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
        //int applyType = Integer.parseInt(strApplyType);
        //int sendState = Integer.parseInt(strSendState);

        Result re;
        ApplyProp applyProp = new ApplyProp();
        applyProp.setPlatformId(platformId);
        applyProp.setServerId(serverId);
        //applyProp.setApplyType(applyType);
        //applyProp.setSendState(sendState);

        Map<String, Object> JsonMap = applyPropDaoImpl.getApplyProp(applyProp, isPage, pageNo, pageSize);
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
            System.out.println("道具申请通过成功");
            re = new Result(200, "道具申请通过成功", null);
        } else {
            System.out.println("道具申请通过失败");
            re = new Result(400, "道具申请通过失败", null);
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
            System.out.println("道具申请不通过成功");
            re = new Result(200, "道具申请不通过成功", null);
        } else {
            System.out.println("道具申请不通过失败");
            re = new Result(400, "道具申请不通过失败", null);
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

}
