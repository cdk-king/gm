package com.cdk.service.impl;


import com.google.common.io.BaseEncoding;

import com.cdk.dao.impl.CDK_DaoImpl;
import com.cdk.entity.CDK;
import com.cdk.result.Result;
import com.cdk.util.BufferUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class CDK_ServiceImpl {

    public static final String Divider = "############################";
    public static final String Split = "----------------";

    @Autowired
    public CDK_DaoImpl cdkDaoImpl;

    public Result getCDK(Map map) {
        String couponId = ((map.get("couponId") != null && map.get("couponId") != "") ? map.get("couponId").toString() : "0");
        String sequenceId = ((map.get("sequenceId") != null && map.get("sequenceId") != "") ? map.get("sequenceId").toString() : "0");
        String cdk = (map.get("cdk") != null ? map.get("cdk").toString() : "");
        String platformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String isPage = (map.get("isPage") != null ? map.get("isPage").toString() : "");
        String StrPageNo = (map.get("pageNo") != null ? map.get("pageNo").toString() : "1");
        String StrPageSize = (map.get("pageSize") != null ? map.get("pageSize").toString() : "5");
        String strPlatform = (map.get("strPlatform") != null ? map.get("strPlatform").toString() : "");
        int pageNo = 1;
        int pageSize = 5;
        try {
            pageNo = Integer.parseInt(StrPageNo);
            pageSize = Integer.parseInt(StrPageSize);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        Result re;
        CDK cdkUse = new CDK();
        cdkUse.setCouponId(Integer.parseInt(couponId));
        cdkUse.setSequenceId(Integer.parseInt(sequenceId));
        cdkUse.setPlatformId(Integer.parseInt(platformId));
        cdkUse.setCdk(cdk);

        Map<String, Object> JsonMap = cdkDaoImpl.getCDK(cdkUse, isPage, pageNo, pageSize, strPlatform);
        System.out.println(JsonMap.get("list"));
        if (Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(400, "礼包列表获取失败", "");
        } else {
            re = new Result(200, "礼包列表获取成功", JsonMap);
        }
        return re;
    }

    public Result exchangeCDK(Map map) {
        Result re;
        String cdk = (map.get("cdk") != null ? map.get("cdk").toString() : "");
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        int platformId = Integer.parseInt(strPlatformId);
        Map<String, Integer> temp = analyse(cdk);
        if (temp.get("couponID") > 0) {
            int a = cdkDaoImpl.exchangeCDK(temp, platformId, cdk);
            if (a > 0) {
                re = new Result(200, "CDK解析成功", temp);
            } else {
                re = new Result(400, "CDK解析失败", "");
            }
        } else {
            re = new Result(400, "CDK解析失败", "");
        }
        return re;
    }

    public Map<String, Integer> analyse(String cdk) {
        System.out.println(cdk);
        Map<String, Integer> map = new HashMap();
        //base32解码
        byte[] data = BaseEncoding.base32().decode(cdk);
        try {
            char[] strChar = cdk.toCharArray();
            String result = "";
            for (int i = 0; i < strChar.length; i++) {
                result += Integer.toBinaryString(strChar[i]) + " ";
            }
            System.out.println(result);
            int a = BufferUtil.readVarInt32(data, 2);
            int length = BufferUtil.computeVarInt32Size(a) + 2;
            System.out.println(a);
            long b = BufferUtil.readVarInt64(data, length);
            System.out.println(b);

            map.put("couponID", a);
            map.put("sequenceID", (int) b);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return map;
    }

}
