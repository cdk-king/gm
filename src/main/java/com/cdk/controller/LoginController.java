package com.cdk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import  org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.cdk.entity.VueLoginInfoVo;
import com.cdk.result.Result;
import  com.cdk.result.ResultFactory;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@RestController
public class LoginController {

    public static final String  Divider= "############################";

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
    public Result login(@Valid @RequestBody VueLoginInfoVo loginInfoVo,BindingResult bindingResult){
        //    if(bindingResult.hasErrors()){
        //        String message = String.format("登录失败，详细信息[%s]",bindingResult.getFieldError().getDefaultMessage());
        //        return ResultFactory.buidFailResult(message);
        //    }
        //    if(!Objects.equals("cdk",loginInfoVo.getUsername() ) || !Objects.equals("123456",loginInfoVo.getPassword())){
        //
        //        String message = String.format("登录失败，详细信息【用户名、密码信息不正确】");
        //        return ResultFactory.buidFailResult(message);
        //    }
        return ResultFactory.buildSuccessResult("登录成功");
    }

    //HttpServletRequest request
    @RequestMapping(value="/login", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result login(@RequestBody VueLoginInfoVo loginInfoVo){

        //String username = request.getParameter("username");
        String username = loginInfoVo.getUsername();
        String password = loginInfoVo.getPassword();

        ///如果queryFoMap的的执行没有结果，则直接抛异常进入catch模块;queryForList则没这问题，
        //queryFoMap的设计初衷应该就是为“执行结果有且只有一条数据”的查询情况所设计的，所以，查询不到数据的时候就抛出异常，
        //如果没有进行很合理的异常处理，则结果会明显不符合预期，为了避免这情况，干脆永远放弃queryFoMap，需要查询结果的时候就只用queryForList。
        //String sql="select * from user where name = '" +  username + "' order by id desc limit 1";
        //Map<String,Object> map=jdbcTemplate.queryForMap(sql);

        String sql="select * from t_user where name = '" +  username + "' order by id desc limit 1";
        System.out.println("sql：" + sql);
        List<Map<String,Object>> list=jdbcTemplate.queryForList(sql);

        if(list != null && list.size() >= 1){
            //因为string属于符合数据类型，所以应该是使用equals，假如我们使用==比较，肯定是比较它们的内存地址了
            if(!Objects.equals(list.get(0).get("password"),password)){
                Result  re = new Result(400,"登录失败，密码错误",list.get(0).get("password")+"|" + password );
                System.out.println("登录失败，密码错误");
                return re;
            }
        }else{
            Result  re = new Result(400,"登录失败，用户名无效", password );
            System.out.println("登录失败，用户名无效");
            return re;
        }

        Result  re = new Result(200,"登录成功",list.get(0) );
        System.out.println("用户登录成功");
        return re;
    }

}
