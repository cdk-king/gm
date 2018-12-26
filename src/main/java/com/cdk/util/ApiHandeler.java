package com.cdk.util;

import com.cdk.dao.impl.UtilsDaoImpl;
import com.cdk.service.impl.UtilsServiceImpl;
import com.twmacinta.util.MD5;

import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

public class ApiHandeler {

    @Autowired
    public UtilsServiceImpl utilsServiceImpl;

    @Autowired
    public UtilsDaoImpl utilsDaoImpl;

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

    public String apiUrl = "";

    public String http = "http://";
    public String https = "https://";

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

    public static String getParam(String platformId) {
        long time = Math.abs(System.currentTimeMillis() / 1000);
        String strTime = time + "";
        String operator = platformId;
        String key = MANAGEMENT_KEY;
        String sign = getSign(strTime, operator, key);

        String param = TIME_KEY + time + OPERATOR_KEY + operator + "&sign=" + sign;
        return param;
    }

    public String getApiUrl(String platformId, String serverId) {
        String apiUrl = "";
        List<Map<String, String>> serverUrl = utilsServiceImpl.getServerUrl(serverId, platformId);
        if (serverUrl.get(0).get("url").matches(".*[a-zA-z].*")) {
            if (serverUrl.get(0).get("url").indexOf("http") != -1) {
                apiUrl = serverUrl.get(0).get("url").split(":")[0];
            } else {
                apiUrl = http + serverUrl.get(0).get("url").split(":")[0];
            }
        } else {
            if (serverUrl.get(0).get("url").indexOf("http") != -1) {
                apiUrl = serverUrl.get(0).get("url").split(":")[0] + ":" + serverUrl.get(0).get("url").split(":")[1];
            } else {
                apiUrl = http + serverUrl.get(0).get("url").split(":")[0] + ":" + serverUrl.get(0).get("url").split(":")[1];
            }
        }
        return apiUrl;
    }

    public String getApiUrl(Map<String, String> serverUrl) {
        String apiUrl = "";
        System.out.println(serverUrl);
        if (serverUrl.get("url").matches(".*[a-zA-z].*")) {
            System.out.println(serverUrl.get("url").matches(".*[a-zA-z].*"));
            if (serverUrl.get("url").indexOf("http") != -1) {
                apiUrl = serverUrl.get("url").split(":")[0];
            } else {
                apiUrl = http + serverUrl.get("url").split(":")[0];
            }
        } else {
            System.out.println(serverUrl.get("url").indexOf("http"));
            if (serverUrl.get("url").indexOf("http") != -1) {
                apiUrl = serverUrl.get("url").split(":")[0] + ":" + serverUrl.get("url").split(":")[1];
            } else {
                apiUrl = http + serverUrl.get("url").split(":")[0] + ":" + serverUrl.get("url").split(":")[1];
            }
        }
        return apiUrl;
    }

}
