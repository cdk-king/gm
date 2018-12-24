package com.cdk.service.impl;


import com.cdk.dao.impl.BanIpDaoImpl;
import com.cdk.entity.BanIp;
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
import java.util.logging.Logger;

@Service
public class BanIpServiceImpl extends ApiHandeler {
    private static Logger logger = Logger.getLogger(String.valueOf(BanIpServiceImpl.class));
    public static final String Divider = "############################";
    public static final String Split = "----------------";

    @Autowired
    public BanIpDaoImpl banIpDaoImpl;

    public Result getBanIp(Map map) {
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String strServerId = ((map.get("serverId") != null && map.get("serverId") != "") ? map.get("serverId").toString() : "0");
        String isPage = (map.get("isPage") != null ? map.get("isPage").toString() : "");
        String StrPageNo = (map.get("pageNo") != null ? map.get("pageNo").toString() : "1");
        String StrPageSize = (map.get("pageSize") != null ? map.get("pageSize").toString() : "5");
        String strPlatform = (map.get("strPlatform") != null ? map.get("strPlatform").toString() : "");
        logger.info("strPlatform：" + strPlatform);

        int pageNo = 1;
        int pageSize = 5;
        try {
            pageNo = Integer.parseInt(StrPageNo);
            pageSize = Integer.parseInt(StrPageSize);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        int platformId = Integer.parseInt(strPlatformId);
        int serverId = Integer.parseInt(strServerId);
        BanIp banIp = new BanIp();
        banIp.setPlatformId(platformId);
        banIp.setServerId(serverId);

        Result re;
        Map<String, Object> JsonMap = banIpDaoImpl.getBanIp(banIp, isPage, pageNo, pageSize, strPlatform);
        if (Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(400, "禁封IP列表获取失败", "");
        } else {
            re = new Result(200, "禁封IP列表获取成功", JsonMap);
        }

        return re;
    }

    public Result addBanIp(Map map) {
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String strServerId = ((map.get("serverId") != null && map.get("serverId") != "") ? map.get("serverId").toString() : "0");
        String ip = (map.get("ip") != null ? map.get("ip").toString() : "");
        String banLong = (map.get("banLong") != null ? map.get("banLong").toString() : "");
        String note = (map.get("note") != null ? map.get("note").toString() : "");
        String addUser = (map.get("addUser") != null ? map.get("addUser").toString() : "");

        int platformId = Integer.parseInt(strPlatformId);
        int serverId = Integer.parseInt(strServerId);
        BanIp banIp = new BanIp();
        banIp.setPlatformId(platformId);
        banIp.setServerId(serverId);
        banIp.setIp(ip);
        banIp.setBanLong(banLong);
        banIp.setNote(note);
        banIp.setAddUser(addUser);

        Result re;
        int temp = banIpDaoImpl.addBanIp(banIp);
        if (temp > 0) {
            re = new Result(200, "IP禁封申请添加成功", "");
        } else {
            re = new Result(400, "IP禁封申请添加失败", "");
        }
        return re;
    }

    public Result banIp(Map map) {
        String strid = ((map.get("id") != null && map.get("id") != "") ? map.get("id").toString() : "0");
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String strServerId = ((map.get("serverId") != null && map.get("serverId") != "") ? map.get("serverId").toString() : "0");
        String remove = ((map.get("remove") != null && map.get("remove") != "") ? map.get("remove").toString() : "0");
        String ip = ((map.get("ip") != null && map.get("ip") != "") ? map.get("ip").toString() : "");
        String banLong = ((map.get("banLong") != null && map.get("banLong") != "") ? map.get("banLong").toString() : "0");
        String note = ((map.get("note") != null && map.get("note") != "") ? map.get("note").toString() : "");

        int id = Integer.parseInt(strid);

        long time = Math.abs(System.currentTimeMillis() / 1000);
        String strTime = time + "";
        String operator = strPlatformId;
        String key = MANAGEMENT_KEY;
        String sign = getSign(strTime, operator, key);

        String param = TIME_KEY + time + OPERATOR_KEY + operator + "&sign=" + sign;
        if (!Objects.equals(remove, "")) {
            param += "&Remove=" + remove;
        }

        param += "&HowLong=" + banLong;

        try {
            //中文转码
            note = URLEncoder.encode(note, "UTF-8");
            ip = URLEncoder.encode(ip, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        param += "&IP=" + ip;
        if (!Objects.equals(note, "")) {
            param += "&Describle=" + note;
        }

        List<Map<String, String>> serverUrl = utilsServiceImpl.getServerUrl(strServerId, strPlatformId);
        apiUrl = http + serverUrl.get(0).get("url").split(":")[0];

        String url = apiUrl + "/UpdateAccount/FreezeIP";
        logger.info(url);

        logger.info(param);
        HttpRequestUtil httpRequestUtil = new HttpRequestUtil();
        String data = httpRequestUtil.sendGet(url, param);
        logger.info(data);
        Result re;
        JSONObject jb = JSONObject.fromObject(data);
        Map resultMap = (Map) jb;
        if (Objects.equals(resultMap.get("Result"), 1)) {
            if (Objects.equals(remove, "1")) {
                int temp = banIpDaoImpl.changeBanState(id, 0);
                if (temp > 0) {
                    re = new Result(200, "IP解除禁封成功", data);
                } else {
                    re = new Result(200, "IP解除禁封成功,状态修改失败", data);
                }
            } else {
                int temp = banIpDaoImpl.changeBanState(id, 1);
                if (temp > 0) {
                    re = new Result(200, "IP禁封成功", data);
                } else {
                    re = new Result(200, "IP禁封成功,状态修改失败", data);
                }
            }

        } else {
            if (Objects.equals(remove, "1")) {
                re = new Result(400, "IP解除禁封失败", data);
            } else {
                re = new Result(400, "IP禁封失败", data);
            }
        }
        return re;
    }

    public Result deleteBanIp(Map map) {
        String strid = ((map.get("id") != null && map.get("id") != "") ? map.get("id").toString() : "0");
        int id = Integer.parseInt(strid);
        Result re;
        int temp = banIpDaoImpl.deleteBanIp(id);
        if (temp > 0) {
            re = new Result(200, "删除成功", "");
        } else {
            re = new Result(400, "删除失败", "");
        }
        return re;
    }

    public Result deleteAllBanIp(Map map) {
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
        int[] temp = banIpDaoImpl.deleteAllBanIp(objectArry);
        if (temp.length != 0) {
            logger.info("批量删除成功");
            re = new Result(200, "批量删除成功", null);
        } else if (objectArry.length == 0) {
            logger.info("无任何删除操作");
            re = new Result(400, "无任何删除操作", null);
        } else {
            logger.info("批量删除失败");
            re = new Result(400, "批量删除失败", null);
        }
        return re;
    }
}
