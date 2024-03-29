package com.cdk.dao.impl;

import com.google.common.io.BaseEncoding;

import com.cdk.entity.Coupon;
import com.cdk.util.BufferUtil;
import com.cdk.util.CDKUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.zip.Adler32;

@Repository
public class CouponDaoImpl {
    private static Logger logger = LoggerFactory.getLogger(CouponDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String[] generateCDK(Coupon coupon, String gameId) {
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
        String sql = "insert into t_coupon (gameId,giftId,couponId,couponCount,couponTitle,coupon_describe,platformId," +
                "start_sequence,end_sequence,salt,addUser,addDatetime,fileUrl,isDelete,isCommonCDK) " + " values ('" + gameId + "','" +
                coupon.getGiftId() + "','" + coupon.getCouponId() + "','" + coupon.getCouponCount() + "','" + coupon.getCouponTitle() + "','" +
                coupon.getCoupon_describe() + "','" + coupon.getPlatformId() + "','" + start_sequence + "','" + end_sequence + "','" + salt + "','" +
                coupon.getAddUser() + "','" + addDatetime + "','" + fileUrl + "',0 ,'" + coupon.getIsCommonCDK() + "')";
        logger.debug("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        String[] resultList = new String[coupon.getCouponCount()];
        if (temp > 0) {
            resultList = generateCDK(start_sequence, salt, int2Long, coupon.getCouponCount(), coupon.getCouponId(), coupon.getPlatformId(),
                    coupon.getGiftId(), coupon.getIsCommonCDK());
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
        logger.debug("sql：" + sql);
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

    public String[] generateCDK(int start_sequence, int salt, long int2Long, int count, int CouponId, int platformId, int giftId, int isCommonCDK) {
        File f1 = new File("cdk");
        if (!f1.exists()) {
            //生成所有目录
            f1.mkdirs();
            logger.debug("文件夹已创建");
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
            for (int i = 0; i < count; i++) {
                String s = "";
                s = generate(0 + giftId * 1, start_sequence + i, salt);
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
                //System.out.print(c); // 一个个打印字符
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
        return encoding.encode(data);
    }

    public int deleteCDK(String id) {
        String sql = "update t_coupon set isDelete = 1 where id='" + id + "' ";
        logger.debug("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int[] deleteAllCDK(String[] idList) {
        String strSql = "";
        String sql[] = new String[idList.length];
        int[] temp = new int[idList.length];
        for (int i = 0; i < idList.length; i++) {
            sql[i] = "UPDATE  t_coupon  set isDelete='1' where id = '" + idList[i] + "';";
            strSql += sql;
        }
        logger.debug("sql：" + strSql);
        temp = jdbcTemplate.batchUpdate(sql);
        return temp;
    }

    public Map<String, Object> getCoupon(Coupon coupon, String giftId, String giftName, String isPage, int pageNo, int pageSize, String strPlatform,
            String gameId, String isCommonCDK) {
        String sql =
                "select a.*, b.platform,c.giftName from t_coupon as a join  t_gameplatform as b on a.platformId = b.platformId join t_gift_upload as c on c.giftId = a.couponId and a.platformId = c.platformId  join t_game as d on d.id = b.gameId and d.isDelete !=1  where d.id='" +
                        gameId + "' and a.gameId = '" + gameId + "' and  a.platformId IN (" + strPlatform +
                        ")  and a.isDelete!=1 and  b.isDelete != 1  ";
        if (coupon.getPlatformId() != 0) {
            sql += " and a.platformId ='" + coupon.getPlatformId() + "' ";
        }
        if (!Objects.equals(giftId, "")) {
            sql += " and a.couponId like '%" + giftId + "%' ";
        }
        if (!Objects.equals(giftName, "")) {
            sql += " and c.giftName like '%" + giftName + "%'";
        }
        if (!Objects.equals(isCommonCDK, "")) {
            sql += " and a.isCommonCDK = '" + isCommonCDK + "'";
        }
        sql += " order by id desc ";
        logger.debug("sql：" + sql);
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
