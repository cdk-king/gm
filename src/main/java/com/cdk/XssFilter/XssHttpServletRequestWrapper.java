package com.cdk.XssFilter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private static final Logger logger = LoggerFactory.getLogger(XssHttpServletRequestWrapper.class);

    private byte[] requestBody = null;

    private JSONObject jsonObject = null;

    // 传入是JSON格式 转换成JSONObject
    public JSONObject getRequestBody() throws UnsupportedEncodingException {
        return JSON.parseObject((new String(requestBody, "UTF-8")));
    }

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     *
     * @throws IllegalArgumentException
     *             if the request is null
     */
    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        String path = request.getContextPath();
        String method = request.getServletPath();
        Map map = request.getParameterMap();

        try {
            requestBody = StreamUtils.copyToByteArray(request.getInputStream());
            String strRequestBody = new String(requestBody, "UTF-8");
            String re = cleanXSS(strRequestBody);
            requestBody = re.getBytes("UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (requestBody == null) {
            requestBody = new byte[0];
        }
        //字节数组输入流在内存中创建一个字节数组缓冲区，从输入流读取的数据保存在该字节数组缓冲区中
        final ByteArrayInputStream bais = new ByteArrayInputStream(requestBody);
        return new ServletInputStream() {
            @Override
            public int read() throws IOException {
                return bais.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener listener) {

            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {

            encodedValues[i] = cleanXSS(values[i]);
        }
        return encodedValues;
    }

    /* 覆盖getParameter方法，将参数名和参数值都做xss过滤。
     * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取
     * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖
     */
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        if (value == null) {
            return null;
        }

        return cleanXSS(value);
    }

    /**
     * 覆盖getHeader方法，将参数名和参数值都做xss过滤。
     * 如果需要获得原始的值，则通过super.getHeaders(name)来获取
     * getHeaderNames 也可能需要覆盖
     */
    public String getHeader(String name) {
        String value = super.getHeader(name);
        if (value == null) {
            return null;
        }

        return cleanXSS(value);
    }


    private String cleanXSS(String value) {

        //You'll need to remove the spaces from the html entities below
        value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
        //value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
        value = value.replaceAll("'", "& #39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        value = value.replaceAll("script", "");
        value = value.replaceAll("[*]", "[" + "*]");
        value = value.replaceAll("[+]", "[" + "+]");
        value = value.replaceAll("[?]", "[" + "?]");


        // replace sql 这里可以自由发挥
        String[] values = value.split(" ");

        String badStr = "'|and|exec|execute|insert|select|delete|update|count|drop|%|chr|mid|master|truncate|" +
                "char|declare|sitename|net user|xp_cmdshell|;|or|-|+|,|like'|and|exec|execute|insert|create|drop|" +
                "table|from|grant|use|group_concat|column_name|" +
                "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|" +
                "chr|mid|master|truncate|char|declare|or|;|-|--|,|like|//|/|%|#";
        String[] badStrs = badStr.split("\\|");
        for (int i = 0; i < badStrs.length; i++) {
            for (int j = 0; j < values.length; j++) {
                if (values[j].equalsIgnoreCase(badStrs[i])) {
                    values[j] = "forbid";
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            if (i == values.length - 1) {
                sb.append(values[i]);
            } else {
                sb.append(values[i] + " ");
            }
        }

        value = sb.toString();

        return value;
    }
}
