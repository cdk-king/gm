package com.cdk.dao.impl;

import com.cdk.entity.Coupon;
import com.cdk.util.BufferUtil;
import com.cdk.util.CDKUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.zip.Adler32;

@Repository
public class CouponDaoImpl {

    public static final String Divider = "############################";
    public static final String Split = "----------------";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //@Override
    public int generateCDK(Coupon coupon) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        System.out.println("giftId" + coupon.getGiftId());
        System.out.println("couponId" + coupon.getCouponId());
        System.out.println("couponCount" + coupon.getCouponCount());
        System.out.println("couponTitle" + coupon.getCouponTitle());
        System.out.println("coupon_describe" + coupon.getCoupon_describe());
        System.out.println("platformId" + coupon.getPlatformId());
        System.out.println("start_sequence" + coupon.getCouponId());


        int max_end_sequence = getMaxEnd_sequence(coupon);
        System.out.println(max_end_sequence);
        int start_sequence = max_end_sequence + 1;
        System.out.println(start_sequence);
        int end_sequence = start_sequence + coupon.getCouponCount();
        System.out.println(end_sequence);
        // 随机salt
        //java7在所有情形下都更推荐使用ThreadLocalRandom，它向下兼容已有的代码且运营成本更低
        int salt = ThreadLocalRandom.current().nextInt();
        System.out.println(salt);
        System.out.println("starDatetime" + coupon.getStartDatetime());
        System.out.println("endDatetime" + coupon.getEndDatetime());
        System.out.println("addDatetime" + addDatetime);


        String sql = "insert into t_coupon (giftId,couponId,couponCount,couponTitle,coupon_describe,platformId," +
                "start_sequence,end_sequence,salt,addUser,addDatetime) " + " values ('" + coupon.getGiftId() + "','" + coupon.getCouponId() + "','" +
                coupon.getCouponCount() + "','" + coupon.getCouponTitle() + "','" + coupon.getCoupon_describe() + "','" + coupon.getPlatformId() +
                "','" + start_sequence + "','" + end_sequence + "','" + salt + "','" + coupon.getAddUser() + "','" + addDatetime + "')";
        System.out.println("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        if (temp > 0) {
            long int2Long = CDKUtil.int2Long(start_sequence, salt);
            generateCDK(start_sequence, salt, int2Long, coupon.getCouponCount(), coupon.getCouponId(), coupon.getPlatformId(), coupon.getGiftId());
        } else {
            return 0;
        }

        return 1;
    }

    public int getMaxEnd_sequence(Coupon coupon) {
        String sql = "select max(end_sequence) as max_end_sequence from t_coupon ";
        //+" where id=" + coupon.getCouponId() + " and platformId=" + coupon.getPlatformId();
        System.out.println("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        System.out.println(list);
        //结果为空时size为1
        System.out.println(list.size());
        int maxEnd_sequence = 0;
        if (list.size() != 0) {
            maxEnd_sequence = Integer.parseInt(list.get(0).get("max_end_sequence").toString());
        }
        return maxEnd_sequence;
    }

    public void generateCDK(int start_sequence, int salt, long int2Long, int count, int CouponId, int platformId, int giftId) {
        String fileName = "平台" + platformId + "_礼包id" + giftId + "_个数" + count + "_序号" + start_sequence + ".txt";

        File file = new File(fileName);
        ;
        if (file.exists()) {
            throw new RuntimeException("文件存在??? " + fileName);
        }
        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < count; i++) {
                String s = generate(0 + giftId * 10000, start_sequence + i, salt);
                writer.append(s);
                writer.append("\r\n");
                writer.write(s);

            }
            writer.flush();
            writer.close();
            // 创建 FileReader 对象
            FileReader fr = new FileReader(file);
            char[] a = new char[50];
            fr.read(a); // 从数组中读取内容
            for (char c : a) {
                System.out.print(c); // 一个个打印字符
            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //private static final BaseEncoding encoding = BaseEncoding.base32().upperCase().omitPadding();

    public static String generate(int couponID, long sequenceID, int salt) {
        int len = BufferUtil.computeVarInt32Size(couponID) + BufferUtil.computeVarInt64Size(sequenceID) + 2;

        byte[] data = new byte[len];

        data[0] = (byte) (salt >>> 8);
        data[1] = (byte) salt;

        int index = BufferUtil.writeVarInt32(data, 2, couponID);
        index = BufferUtil.writeVarInt64(data, index, sequenceID);

        Adler32 adler = new Adler32();

        adler.update(data);

        long value = adler.getValue();

        data[0] = (byte) (value >>> 16);
        data[1] = (byte) value;

        return new String(data);
    }
}
