package com.cdk.dao.impl;

import com.google.common.io.BaseEncoding;

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
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;
import java.util.zip.Adler32;

@Repository
public class CouponDaoImpl {
    private static Logger logger = Logger.getLogger(String.valueOf(CouponDaoImpl.class));

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String[] generateCDK(Coupon coupon) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        int max_end_sequence = getMaxEnd_sequence(coupon);
        int start_sequence = max_end_sequence + 1;
        int end_sequence = start_sequence + coupon.getCouponCount() - 1;
        // 随机salt
        //java7在所有情形下都更推荐使用ThreadLocalRandom，它向下兼容已有的代码且运营成本更低
        int salt = ThreadLocalRandom.current().nextInt();
        long int2Long = CDKUtil.int2Long(start_sequence, salt);
        String fileUrl =
                "cdk/平台" + coupon.getPlatformId() + "_礼包id" + coupon.getGiftId() + "_个数" + coupon.getCouponCount() + "_序号" + start_sequence + ".txt";
        String sql = "insert into t_coupon (giftId,couponId,couponCount,couponTitle,coupon_describe,platformId," +
                "start_sequence,end_sequence,salt,addUser,addDatetime,fileUrl) " + " values ('" + coupon.getGiftId() + "','" + coupon.getCouponId() +
                "','" + coupon.getCouponCount() + "','" + coupon.getCouponTitle() + "','" + coupon.getCoupon_describe() + "','" +
                coupon.getPlatformId() + "','" + start_sequence + "','" + end_sequence + "','" + salt + "','" + coupon.getAddUser() + "','" +
                addDatetime + "','" + fileUrl + "' )";
        logger.info("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        String[] resultList = new String[coupon.getCouponCount()];
        if (temp > 0) {
            resultList = generateCDK(start_sequence, salt, int2Long, coupon.getCouponCount(), coupon.getCouponId(), coupon.getPlatformId(),
                    coupon.getGiftId());
        } else {
            return null;
        }
        return resultList;
    }

    public int exchangeCDK(String cdk) {
        int temp = 1;
        return temp;
    }

    public int getMaxEnd_sequence(Coupon coupon) {
        String sql = "select max(end_sequence) as max_end_sequence from t_coupon ";
        logger.info("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        int maxEnd_sequence = 0;
        if (list.size() != 0) {
            if (Objects.equals(list.get(0).get("max_end_sequence"), null)) {
                maxEnd_sequence = 0;
            } else {
                maxEnd_sequence = Integer.parseInt(list.get(0).get("max_end_sequence").toString());
            }
        }
        return maxEnd_sequence;
    }

    public String[] generateCDK(int start_sequence, int salt, long int2Long, int count, int CouponId, int platformId, int giftId) {
        File f1 = new File("cdk");
        if (!f1.exists()) {
            //生成所有目录
            f1.mkdirs();
            logger.info("文件夹已创建");
        }
        String fileName = "cdk/平台" + platformId + "_礼包id" + giftId + "_个数" + count + "_序号" + start_sequence + ".txt";
        String results = new String();
        File file = new File(fileName);

        if (file.exists()) {
            throw new RuntimeException("文件存在??? " + fileName);
        }

        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            // 创建 FileReader 对象
            logger.info((0 + giftId * 10000) + "");
            logger.info(start_sequence + "");
            logger.info(salt + "");
            for (int i = 0; i < count; i++) {
                String s = generate(0 + giftId * 1, start_sequence + i, salt);
                logger.info(s);
                //writer.append(s);
                writer.write(s);
                results = results + s + ";";
                writer.append("\r\n");
            }
            writer.flush();
            writer.close();
            FileReader fr = new FileReader(file);
            char[] a = new char[50];
            fr.read(a); // 从数组中读取内容
            StringBuilder result = new StringBuilder();
            for (char c : a) {
                System.out.print(c); // 一个个打印字符
            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results.split(";");
    }

    private static final BaseEncoding encoding = BaseEncoding.base32().upperCase().omitPadding();

    public static String generate(int couponID, long sequenceID, int salt) {
        int len = BufferUtil.computeVarInt32Size(couponID) + BufferUtil.computeVarInt64Size(sequenceID) + 2;

        byte[] data = new byte[len];
        //英文数字为一字节（byte），8位（bit），汉字两字节
        //int 32位 4字节
        data[0] = (byte) (salt >>> 8);
        data[1] = (byte) salt;

        int index = BufferUtil.writeVarInt32(data, 2, couponID);
        index = BufferUtil.writeVarInt64(data, index, sequenceID);

        Adler32 adler = new Adler32();

        adler.update(data);

        long value = adler.getValue();

        data[0] = (byte) (value >>> 16);
        data[1] = (byte) value;

        String encoded = Base64.getEncoder().encodeToString(data);
        System.out.print("Base64:");
        logger.info(encoded);
        return encoding.encode(data);
    }

    public Map<String, Object> getCoupon(Coupon coupon, String isPage, int pageNo, int pageSize, String strPlatform) {
        String sql = "select a.*, b.platform from t_coupon as a join  t_gameplatform as b on a.platformId = b.platformId where a.platformId IN (" +
                strPlatform + ")  and b.isDelete != 1  ";
        if (coupon.getPlatformId() != 0) {
            sql += " and a.platformId ='" + coupon.getPlatformId() + "' ";
        }

        logger.info("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        int total = list.size();
        if (!Objects.equals(isPage, "")) {
            sql += " limit " + (pageNo - 1) * pageSize + ", " + pageSize;
        }
        list = jdbcTemplate.queryForList(sql);
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        JsonMap.put("total", total);
        return JsonMap;
    }
}
