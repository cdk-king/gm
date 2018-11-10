package com.cdk.util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Redeem {

    static String stringtable = "abcdefghijkmnpqrstuvwxyz23456789";
    //final static String password = "dak3le2";

    //从byte转为字符表索引所需要的位数
    final static int convertByteCount = 5;

    public static String[] test(int groupid, int codecount, int codelength, String password, int start_sequence, int platformId, int giftId)
            throws Exception {
        ShowTime();
        System.out.println("=======================");
        //password = "-1435395402";
        //create((byte) 1, 10000, 12, password);
        String results = create((byte) groupid, codecount, codelength, password);
        String[] resultList = results.split(";");
        writerFile(resultList, platformId, giftId, codecount, codelength, start_sequence, password);
        VerifyCode("7msiwbxidckr", password);
        VerifyCode("6c2kgtxnc5uj", password);
        VerifyCode("asdgh4jqwvz2", password);
        VerifyCode("2c3cgvfng5va", password);
        VerifyCode("8siqcs2eatca", password);
        return resultList;
    }

    /**
     * 生成兑换码
     * 这里每一次生成兑换码的最大数量为int的最大值即2147483647
     * @param groupid
     * @param codecount
     * @param codelength
     * @param password
     * @return
     */
    public static String create(byte groupid, int codecount, int codelength, String password) {
        String results = new String();
        //8位的数据总长度
        int fullcodelength = codelength * convertByteCount / 8;
        //随机码对时间和id同时做异或处理
        //类型1，id4，随机码n,校验码1
        int randcount = fullcodelength - 6;//随机码有多少个

        //如果随机码小于0 不生成
        if (randcount <= 0) {
            return null;
        }
        for (int i = 0; i < codecount; i++) {
            //这里使用i作为code的id
            //生成n位随机码
            byte[] randbytes = new byte[randcount];
            for (int j = 0; j < randcount; j++) {
                randbytes[j] = (byte) (Math.random() * Byte.MAX_VALUE);
            }

            //存储所有数据
            ByteHapper byteHapper = ByteHapper.CreateBytes(fullcodelength);
            byteHapper.AppendNumber(groupid).AppendNumber(i).AppendBytes(randbytes);

            //计算校验码 这里使用所有数据相加的总和与byte.max 取余
            byte verify = (byte) (byteHapper.GetSum() % Byte.MAX_VALUE);
            byteHapper.AppendNumber(verify);

            //使用随机码与时间和ID进行异或
            for (int j = 0; j < 5; j++) {
                byteHapper.bytes[j] = (byte) (byteHapper.bytes[j] ^ (byteHapper.bytes[5 + j % randcount]));
            }

            //使用密码与所有数据进行异或来加密数据
            byte[] passwordbytes = password.getBytes();
            for (int j = 0; j < byteHapper.bytes.length; j++) {
                byteHapper.bytes[j] = (byte) (byteHapper.bytes[j] ^ passwordbytes[j % passwordbytes.length]);
            }

            //这里存储最终的数据
            byte[] bytes = new byte[codelength];

            //按6位一组复制给最终数组
            for (int j = 0; j < byteHapper.bytes.length; j++) {
                for (int k = 0; k < 8; k++) {
                    int sourceindex = j * 8 + k;
                    int targetindex_x = sourceindex / convertByteCount;
                    int targetindex_y = sourceindex % convertByteCount;
                    byte placeval = (byte) Math.pow(2, k);
                    byte val = (byte) ((byteHapper.bytes[j] & placeval) == placeval ? 1 : 0);
                    //复制每一个bit
                    bytes[targetindex_x] = (byte) (bytes[targetindex_x] | (val << targetindex_y));
                }
            }
            StringBuilder result = new StringBuilder();
            //编辑最终数组生成字符串
            for (int j = 0; j < bytes.length; j++) {
                result.append(stringtable.charAt(bytes[j]));
            }
            results = results + result.toString() + ";";
            System.out.println("out string : " + result.toString());
        }

        ShowTime();
        return results;
    }

    /**
     * 生成文件
     * @param content
     */
    public static void writerFile(String[] content, int platformId, int giftId, int count, int length, int start_sequence, String salt) {
        String fileName = "平台" + platformId + "_礼包id" + giftId + "_个数" + count + "_序号" + start_sequence + "_salt[" + salt + "].txt";

        File file = new File(fileName);

        if (file.exists()) {
            throw new RuntimeException("文件存在??? " + fileName);
        }

        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            // 创建 FileReader 对象
            System.out.println(0 + giftId * 10000);
            System.out.println(start_sequence);
            for (int i = 0; i < count; i++) {
                String s = content[i].toString();
                //System.out.print(s);
                writer.write(s);
                writer.append("\r\n");
            }

            writer.flush();
            writer.close();
            FileReader fr = new FileReader(file);
            char[] a = new char[count * (length + 4)];
            fr.read(a); // 从数组中读取内容
            String text = "";
            for (char c : a) {
                text += c;
                //System.out.print(c); // 一个个打印字符
            }
            System.out.println(text);
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证兑换码
     * @param code
     */
    public static void VerifyCode(String code, String password) {
        byte[] bytes = new byte[code.length()];

        //首先遍历字符串从字符表中获取相应的二进制数据
        for (int i = 0; i < code.length(); i++) {
            byte index = (byte) stringtable.indexOf(code.charAt(i));
            bytes[i] = index;
        }

        //还原数组
        int fullcodelength = code.length() * convertByteCount / 8;
        int randcount = fullcodelength - 6;//随机码有多少个

        byte[] fullbytes = new byte[fullcodelength];
        for (int j = 0; j < fullbytes.length; j++) {
            for (int k = 0; k < 8; k++) {
                int sourceindex = j * 8 + k;
                int targetindex_x = sourceindex / convertByteCount;
                int targetindex_y = sourceindex % convertByteCount;

                byte placeval = (byte) Math.pow(2, targetindex_y);
                byte val = (byte) ((bytes[targetindex_x] & placeval) == placeval ? 1 : 0);

                fullbytes[j] = (byte) (fullbytes[j] | (val << k));
            }
        }

        //解密，使用密码与所有数据进行异或来加密数据
        byte[] passwordbytes = password.getBytes();
        for (int j = 0; j < fullbytes.length; j++) {
            fullbytes[j] = (byte) (fullbytes[j] ^ passwordbytes[j % passwordbytes.length]);
        }

        //使用随机码与时间和ID进行异或
        for (int j = 0; j < 5; j++) {
            fullbytes[j] = (byte) (fullbytes[j] ^ (fullbytes[5 + j % randcount]));
        }

        //获取校验码 计算除校验码位以外所有位的总和
        int sum = 0;
        for (int i = 0; i < fullbytes.length - 1; i++) {
            sum += fullbytes[i];
        }
        byte verify = (byte) (sum % Byte.MAX_VALUE);

        //校验
        if (verify == fullbytes[fullbytes.length - 1]) {
            System.out.println(code + " : verify success!");
        } else {
            System.out.println(code + " : verify failed!");
        }

    }


    public static void ShowTime() {
        Date date = new Date();
        long times = date.getTime();//时间戳
        System.out.println("time  : " + times);
    }
}
