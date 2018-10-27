package com.cdk.controller;

import com.cdk.entity.VueLoginInfoVo;
import com.cdk.result.Result;
import com.cdk.result.ResultFactory;
import com.cdk.service.impl.LoginServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
public class LoginController {

    public static final String Divider = "############################";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private LoginServiceImpl loginServiceImpl;

    /**
     * 登录控制器，前后端分离用的不同协议和端口，所以需要加入@CrossOrigin支持跨域。
     * 给VueLoginInfoVo对象加入@Valid注解，并在参数中加入BindingResult来获取错误信息。
     * 在逻辑处理中我们判断BindingResult知否含有错误信息，如果有错误信息，则直接返回错误信息。
     */

    /**
     * 关键得是@requestBody注解常用来处理content-type不是默认的application/x-www-form-urlcoded编码的内容，
     * 比如说：application/json或者是application/xml等。一般情况下来说常用其来处理application/json类型。
     * 并且@requestbody传送的参数需要是son对象即可；
     * 附：form默认的提交方式content-type是x-www-form-urlencoded，会将传递的参数转换成key-value方式。
     */
    @CrossOrigin
    @RequestMapping(value = "/testLogin", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result testLogin(@Valid @RequestBody VueLoginInfoVo loginInfoVo, BindingResult bindingResult) {
        return ResultFactory.buildSuccessResult("登录成功");
    }

    //HttpServletRequest request
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result login(@RequestBody VueLoginInfoVo loginInfoVo) {
        Result re = loginServiceImpl.login(loginInfoVo);
        return re;
    }

}
