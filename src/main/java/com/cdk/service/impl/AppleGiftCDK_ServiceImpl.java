package com.cdk.service.impl;

import com.cdk.dao.impl.CouponDaoImpl;
import com.cdk.entity.Coupon;
import com.cdk.result.Result;
import com.cdk.service.AppleGiftCDK_Service;
import com.twmacinta.util.MD5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Objects;

@Service
public class AppleGiftCDK_ServiceImpl implements AppleGiftCDK_Service {

    public static final String Divider = "############################";
    public static final String Split = "----------------";
    private static String URL = "http://127.0.0.1/new_gen?";
    public static final int GIFTID_OFFSET = 10000;
    private static final String SIGN_KEY = "cdk";
    private static final byte[] SIGN_KEY_BYTES = SIGN_KEY.getBytes(Charset.forName("UTF-8"));

    @Autowired
    public CouponDaoImpl couponDaoImpl;

    public Result generateCDK(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        String sign = (map.get("sign") != null ? map.get("sign").toString() : "");
        String platformId = (map.get("platformId") != null ? map.get("platformId").toString() : "");
        String giftId = (map.get("giftId") != null ? map.get("giftId").toString() : "");
        String couponTitle = (map.get("couponTitle") != null ? map.get("couponTitle").toString() : "");
        String coupon_describe = (map.get("coupon_describe") != null ? map.get("coupon_describe").toString() : "");
        String couponCount = (map.get("couponCount") != null ? map.get("couponCount").toString() : "");
        String startDatetime = (map.get("startDatetime") != null ? map.get("startDatetime").toString() : "");
        String endDatetime = (map.get("endDatetime") != null ? map.get("endDatetime").toString() : "");
        String addUser = (map.get("addUser") != null ? map.get("addUser").toString() : "");
        String addDatetime = (map.get("addDatetime") != null ? map.get("addDatetime").toString() : "");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(startDatetime);
        System.out.println(endDatetime);
        //startDatetime = sdf.format(startDatetime);


        Coupon coupon = new Coupon();
        coupon.setPlatformId(Integer.parseInt(platformId));
        coupon.setGiftId(Integer.parseInt(giftId));
        coupon.setCouponTitle(couponTitle);
        coupon.setCoupon_describe(coupon_describe);
        coupon.setCouponCount(Integer.parseInt(couponCount));

        try {
            //转换格式需要添加异常捕获
            coupon.setStartDatetime(sdf.parse(startDatetime));
            coupon.setEndDatetime(sdf.parse(endDatetime));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //int type = giftDaoImpl.getGiftType(coupon.getGiftId());
        int type = 0;
        String expectedSign = getSign(type, coupon.getGiftId(), coupon.getPlatformId(), coupon.getCouponCount());
        System.out.println(sign);
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

    public String getSign(int type, int giftId, int platformId, int count) {
        int CouponId = type + giftId * GIFTID_OFFSET;
        String strCouponID = String.valueOf(CouponId);
        String strOperator = String.valueOf(platformId);
        String strCount = String.valueOf(count);
        System.out.println(SIGN_KEY_BYTES);
        //输出的是byte对象的内存地址
        //都是英文，所以结果相同

        //        for (int i = 0; i < SIGN_KEY_BYTES.length; i++) {
        //            System.out.println(SIGN_KEY_BYTES[i]);
        //        }
        //        for (int i = 0; i < cdk.getBytes().length; i++) {
        //            System.out.println(cdk.getBytes()[i]);
        //        }
        MD5 md5 = new MD5();
        md5.Update(strCouponID);
        md5.Update(strOperator);
        md5.Update(strCount);
        md5.Update(SIGN_KEY_BYTES);
        //32位
        //http://127.0.0.1/new_gen?coupon_id=20000&operator_id=2&count=2&sign=7ce2e9a150208521446c576f7592d7bc
        String sign = md5.asHex();
        System.out.println(sign);
        //        String request = new StringBuilder(128).append(URL).append("coupon_id=").append(strCouponID).append("&operator_id=").append(strOperator)
        //                .append("&count=").append(strCount).append("&sign=").append(sign).toString();
        //        System.out.println(request);
        return sign;
    }
}
