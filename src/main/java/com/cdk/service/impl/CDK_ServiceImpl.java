package com.cdk.service.impl;


import com.google.common.io.BaseEncoding;

import com.cdk.dao.impl.CDK_DaoImpl;
import com.cdk.dao.impl.UtilsDaoImpl;
import com.cdk.entity.CDK;
import com.cdk.result.Result;
import com.cdk.util.BufferUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

@Service
public class CDK_ServiceImpl {
    static Logger logger = Logger.getLogger("CDK_ServiceImpl");
    public static final String Divider = "############################";
    public static final String Split = "----------------";

    @Autowired
    public CDK_DaoImpl cdkDaoImpl;

    @Autowired
    public UtilsDaoImpl utilDaoImpl;

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

    /***
     *
     * @param map
     * @return
     * 1 成功 -1 非法码, 校验不通过 -2 服务器忙 -3 内部错误  -4 码用过了
     */
    public Result exchangeCDK_External(Map map) {
        Result re;
        String cdk = (map.get("cdk") != null ? map.get("cdk").toString() : "");
        String strCouponId = (map.get("couponId") != null ? map.get("couponId").toString() : "");
        String strSequenceId = (map.get("sequenceId") != null ? map.get("sequenceId").toString() : "");
        String strPlatformId = (map.get("platformId") != null ? map.get("platformId").toString() : "");
        int platformId = Integer.parseInt(strPlatformId);
        int couponId = Integer.parseInt(strCouponId);
        int sequenceId = Integer.parseInt(strSequenceId);
        if (couponId > 0) {
            int checkIsUsed = checkIsUsedCDK(couponId, sequenceId, cdk, platformId);
            int check = checkCDK(couponId, sequenceId, cdk, platformId);
            logger.info("checkIsUsed" + checkIsUsed + "|check:" + check);
            if (checkIsUsed == 1) {
                re = new Result(400, "当前CDK已被使用或者激活", "-4");
                return re;
            }
            if (check == 0) {
                re = new Result(400, "CDK校验不通过", "-1");
                return re;
            }
            int a = cdkDaoImpl.exchangeCDK(couponId, sequenceId, platformId, cdk);
            if (a > 0) {
                re = new Result(200, "CDK兑换成功", "1");
            } else {
                re = new Result(400, "CDK有效，记录失败，内部错误", "-3");
            }
        } else {
            re = new Result(400, "CDK校验不通过", "-1");
        }
        return re;
    }

    /***
     *
     * @param map
     * @return
     */
    public Result exchangeCDK(Map map) {
        Result re;
        String cdk = (map.get("cdk") != null ? map.get("cdk").toString() : "");
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        int platformId = Integer.parseInt(strPlatformId);

        Map<String, Integer> temp = analyse(cdk);
        int couponId = Integer.parseInt(temp.get("couponId").toString());
        int sequenceId = Integer.parseInt(temp.get("sequenceId").toString());
        if (couponId > 0) {
            int checkIsUsed = checkIsUsedCDK(couponId, sequenceId, cdk, platformId);
            int check = checkCDK(couponId, sequenceId, cdk, platformId);
            logger.info("checkIsUsed" + checkIsUsed + "|check:" + check);
            if (checkIsUsed == 1) {
                re = new Result(400, "当前CDK已被使用或者激活", "-4");
                return re;
            }
            if (check == 0) {
                re = new Result(400, "CDK校验不通过", "-1");
                return re;
            }
            int a = cdkDaoImpl.exchangeCDK(couponId, sequenceId, platformId, cdk);
            if (a > 0) {
                re = new Result(200, "CDK解析成功", temp);
            } else {
                re = new Result(400, "记录失败，内部错误", "-3");
            }
        } else {
            re = new Result(400, "CDK校验不通过", "-1");
        }
        return re;
    }

    public int checkIsUsedCDK(int couponId, int sequenceId, String cdk, int platformId) {
        List<Map<String, Object>> list = cdkDaoImpl.checkIsUsedCDK(couponId, sequenceId, cdk, platformId);
        System.out.println(list);
        System.out.println(list.size());
        if (null == list || list.size() == 0) {
            return 0;
        }
        return 1;
    }

    public int checkIsUsedCDK_External(int couponId, int sequenceId, String cdk, int platformId) {
        List<Map<String, Object>> list = cdkDaoImpl.checkIsUsedCDK(couponId, sequenceId, cdk, platformId);
        System.out.println(list);
        System.out.println(list.size());
        if (null == list || list.size() == 0) {
            return 0;
        }
        return 1;
    }

    public int checkCDK(int couponId, int sequenceId, String cdk, int platformId) {

        List<Map<String, Object>> list = cdkDaoImpl.checkCDK(couponId, sequenceId, cdk, platformId);
        System.out.println(list);
        if (null == list || list.size() == 0) {
            //失败
            return 0;
        }
        return 1;
    }

    public int checkCDK_External(int couponId, int sequenceId, String cdk, int platformId) {

        List<Map<String, Object>> list = cdkDaoImpl.checkCDK(couponId, sequenceId, cdk, platformId);
        System.out.println(list);
        if (null == list || list.size() == 0) {
            //失败
            return 0;
        }
        return 1;
    }

    public int checkCDKByServerId(Map map, String cdk, int serverId) {
        List<Map<String, Object>> list = cdkDaoImpl.checkCDKByServerId(map, cdk, serverId);
        System.out.println(list);
        System.out.println(list.size());
        if (null == list || list.size() == 0) {
            return 0;
        }
        return 1;
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

            map.put("couponId", a);
            map.put("sequenceId", (int) b);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
        return map;
    }

}
