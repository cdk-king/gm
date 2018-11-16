package com.cdk.util;

import com.twmacinta.util.MD5;

import org.springframework.beans.factory.annotation.Value;

import java.nio.charset.Charset;

public class ApiHandeler {

    public static final String UTF_8_NAME = "UTF-8";
    public static final Charset UTF_8 = Charset.forName("UTF-8");
    public static final String TIME_KEY = "time=";

    public static final String KEY = "&key=";

    public static final String OPERATOR_KEY = "&operator=";

    public static final String MANAGEMENT_KEY = "wojiusuibianyongleyigemima123456";

    private static final byte[] timeKeyBytes = encode(TIME_KEY);

    private static final byte[] keyBytes = encode(KEY);

    private static final byte[] operatorKeyBytes = encode(OPERATOR_KEY);

    public static byte[] encode(String toDo) {
        return toDo.getBytes(UTF_8);
    }

    @Value("${api.url}")
    public String apiUrl;

    private static final byte[] HEX_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f',};

    public static final byte[] asHex(byte[] input) {
        int length = input.length;
        byte[] result = new byte[length * 2];
        for (int i = 0, x = 0; i < length; i++) {
            result[x++] = HEX_CHARS[(input[i] >>> 4) & 0xf];
            result[x++] = HEX_CHARS[input[i] & 0xf];
        }
        return result;
    }

    public static String getSign(String time, String operator, String key) {
        MD5 md5 = new MD5();
        md5.Update(timeKeyBytes);
        md5.Update(time);


        md5.Update(keyBytes);
        md5.Update(key);
        md5.Update(operatorKeyBytes);
        md5.Update(operator);

        return md5.asHex().toLowerCase();
    }

}
