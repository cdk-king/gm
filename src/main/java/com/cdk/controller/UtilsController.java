package com.cdk.controller;

import com.cdk.result.Result;
import com.cdk.service.impl.UtilsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import static com.cdk.util.MD5Util.convertMD5;
import static com.cdk.util.MD5Util.md5Decode;
import static com.cdk.util.MD5Util.string2MD5;

@RestController
public class UtilsController {

    public static final String Divider = "############################";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UtilsServiceImpl utilsServiceImpl;

    /**
      * 〈一句话功能简述〉
      * 〈功能详细描述〉
      * @param  [参数1]   [参数1说明]
      * @param  [参数2]   [参数2说明]
      * @return [返回类型说明]
      * @exception/throws [违例类型] [违例说明]
      * @see [类、类#方法、类#成员]
      * @deprecated
      */
    @RequestMapping("/md5")
    public String md5(HttpServletRequest request) {
        String s = request.getParameter("psw");
        System.out.println(md5Decode("a6aeb3ffa55fc7d664406af9c3bd0f1b"));
        System.out.println("原始：" + s);
        System.out.println("MD5后：" + string2MD5(s));
        System.out.println("加密的：" + convertMD5(s));
        System.out.println("解密的：" + convertMD5(convertMD5(s)));
        String re = "原始：" + s + "<br/>" + "MD5后：" + string2MD5(s) + "<br/>" + "加密的：" + convertMD5(s) + "<br/>" + "解密的：" + convertMD5(convertMD5(s));
        return re;
    }

    @RequestMapping("/getUserAllRight")
    public Result getUserAllRight(@RequestBody Map map) {
        Result re = utilsServiceImpl.getUserAllRight(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/getUserAllRole")
    public Result getUserAllRole(@RequestBody Map map) {
        Result re = utilsServiceImpl.getUserAllRole(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/getGameListForUser")
    public Result getGameListForUser(@RequestBody Map map) {
        Result re = utilsServiceImpl.getGameListForUser(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/getPlatformListForGameId")
    public Result getPlatformListForGameId(@RequestBody Map map) {
        Result re = utilsServiceImpl.getPlatformListForGameId(map);
        System.out.println(Divider);
        return re;
    }
}
