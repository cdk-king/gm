package com.cdk.service.impl;

import com.google.common.io.BaseEncoding;

import com.cdk.dao.impl.CouponDaoImpl;
import com.cdk.dao.impl.GiftDaoImpl;
import com.cdk.entity.Coupon;
import com.cdk.entity.Gift;
import com.cdk.result.Result;
import com.cdk.service.AppleGiftCDK_Service;
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
    @Autowired
    public GiftDaoImpl giftDaoImpl;

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

    public Result analyseCDK(Map map) {
        String analyseCDK = (map.get("analyseCDK") != null ? map.get("analyseCDK").toString() : "");
        //int temp = couponDaoImpl.exchangeCDK(exchangeCDK);
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
        //英文数字为一字节（byte），8位（bit），汉字两字节
        //        data[0] = (byte) (salt >>> 8);
        //        data[1] = (byte) salt;
        //
        //        int index = BufferUtil.writeVarInt32(data, 2, couponID);
        //        index = BufferUtil.writeVarInt64(data, index, sequenceID);
        //
        //        Adler32 adler = new Adler32();
        //
        //        adler.update(data);
        //
        //        long value = adler.getValue();
        //
        //        data[0] = (byte) (value >>> 16);
        //        data[1] = (byte) value;
        //
        //        String encoded = Base64.getEncoder().encodeToString(data);
        //        System.out.print("Base64:");
        //        System.out.println(encoded);
        //        return encoding.encode(data);
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
}
