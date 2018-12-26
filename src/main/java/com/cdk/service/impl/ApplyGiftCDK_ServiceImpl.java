package com.cdk.service.impl;

import com.google.common.io.BaseEncoding;

import com.cdk.dao.impl.CouponDaoImpl;
import com.cdk.dao.impl.GiftDaoImpl;
import com.cdk.entity.Coupon;
import com.cdk.entity.Gift;
import com.cdk.result.Result;
import com.cdk.util.BufferUtil;
import com.twmacinta.util.MD5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

@Service
public class ApplyGiftCDK_ServiceImpl {
    private static Logger logger = Logger.getLogger(String.valueOf(ApplyGiftCDK_ServiceImpl.class));
    public static final int GIFTID_OFFSET = 1;
    private static final String SIGN_KEY = "cdk";
    private static final byte[] SIGN_KEY_BYTES = SIGN_KEY.getBytes(Charset.forName("UTF-8"));

    @Autowired
    public CouponDaoImpl couponDaoImpl;
    @Autowired
    public GiftDaoImpl giftDaoImpl;

    public Result generateCDK(Map map) {
        String sign = (map.get("sign") != null ? map.get("sign").toString() : "");
        String platformId = (map.get("platformId") != null ? map.get("platformId").toString() : "");
        String giftId = (map.get("giftId") != null ? map.get("giftId").toString() : "");
        String couponTitle = (map.get("couponTitle") != null ? map.get("couponTitle").toString() : "");
        String coupon_describe = (map.get("coupon_describe") != null ? map.get("coupon_describe").toString() : "");
        String couponCount = (map.get("couponCount") != null ? map.get("couponCount").toString() : "");
        String startDatetime = (map.get("startDatetime") != null ? map.get("startDatetime").toString() : "");
        String endDatetime = (map.get("endDatetime") != null ? map.get("endDatetime").toString() : "");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Coupon coupon = new Coupon();
        coupon.setPlatformId(Integer.parseInt(platformId));
        coupon.setGiftId(Integer.parseInt(giftId));
        coupon.setCouponTitle(couponTitle);
        coupon.setCoupon_describe(coupon_describe);
        coupon.setCouponCount(Integer.parseInt(couponCount));

        try {
            coupon.setStartDatetime(sdf.parse(startDatetime));
            coupon.setEndDatetime(sdf.parse(endDatetime));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int type = 0;
        String expectedSign = getSign(type, coupon.getGiftId(), coupon.getPlatformId(), coupon.getCouponCount());
        if (!expectedSign.equals(sign)) {
            return new Result(400, "CDK生成失败", "");
        }
        coupon.setCouponId(type + coupon.getGiftId() * GIFTID_OFFSET);
        String[] temp = couponDaoImpl.generateCDK(coupon);
        Result re;
        if (!Objects.equals(temp, null)) {
            re = new Result(200, "CDK生成成功", temp);
        } else {
            re = new Result(400, "CDK生成失败", "");
        }
        return re;
    }

    public Result analyseCDK(Map map) {
        String analyseCDK = (map.get("analyseCDK") != null ? map.get("analyseCDK").toString() : "");
        Map<String, Integer> temp = analyse(analyseCDK);
        Result re;
        if (temp.get("couponID") > 0) {
            re = new Result(200, "CDK解析成功", temp);
        } else {
            re = new Result(400, "CDK解析失败", "");
        }
        return re;
    }

    public Map<String, Integer> analyse(String cdk) {
        Map<String, Integer> map = new HashMap();
        try {
            //base32解码
            byte[] data = BaseEncoding.base32().decode(cdk);
            char[] strChar = cdk.toCharArray();
            String result = "";
            for (int i = 0; i < strChar.length; i++) {
                result += Integer.toBinaryString(strChar[i]) + " ";
            }
            int a = BufferUtil.readVarInt32(data, 2);
            int length = BufferUtil.computeVarInt32Size(a) + 2;
            long b = BufferUtil.readVarInt64(data, length);
            map.put("couponID", a);
            map.put("sequenceID", (int) b);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
        return map;
    }

    public String getSign(int type, int giftId, int platformId, int count) {
        int CouponId = type + giftId * GIFTID_OFFSET;
        String strCouponID = String.valueOf(CouponId);
        String strOperator = String.valueOf(platformId);
        String strCount = String.valueOf(count);
        logger.info(SIGN_KEY_BYTES + "");
        MD5 md5 = new MD5();
        md5.Update(strCouponID);
        md5.Update(strOperator);
        md5.Update(strCount);
        md5.Update(SIGN_KEY_BYTES);
        String sign = md5.asHex();
        return sign;
    }

    public Result getGiftListForPlatformId(Map map) {
        String platformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        Gift gift = new Gift();
        gift.setPlatformId(Integer.parseInt(platformId));
        Map<String, Object> JsonMap = giftDaoImpl.getGiftListForPlatformId(gift);
        Result re;
        if (!Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(200, "礼包列表获取成功", JsonMap);
        } else {
            re = new Result(400, "礼包列表获取失败", "");
        }
        return re;
    }

    public Result getNewGiftListForPlatformId(Map map) {
        String platformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        Gift gift = new Gift();
        gift.setPlatformId(Integer.parseInt(platformId));
        Map<String, Object> JsonMap = giftDaoImpl.getNewGiftListForPlatformId(gift);
        Result re;
        if (!Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(200, "礼包列表获取成功", JsonMap);
        } else {
            re = new Result(400, "礼包列表获取失败", "");
        }
        return re;
    }

    public Result getCoupon(Map map) {
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
        Coupon coupon = new Coupon();
        coupon.setPlatformId(Integer.parseInt(platformId));
        Map<String, Object> JsonMap = couponDaoImpl.getCoupon(coupon, isPage, pageNo, pageSize, strPlatform);
        if (Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(400, "列表获取失败", "");
        } else {
            re = new Result(200, "列表获取成功", JsonMap);
        }
        return re;
    }
}
