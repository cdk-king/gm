package com.cdk.controller;

import  org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.cdk.entity.VueLoginInfoVo;
import com.cdk.result.Result;
import  com.cdk.result.ResultFactory;

import javax.validation.Valid;
import java.util.Objects;


@RestController
public class LoginController {

    /**
     * 登录控制器，前后端分离用的不同协议和端口，所以需要加入@CrossOrigin支持跨域。
     * 给VueLoginInfoVo对象加入@Valid注解，并在参数中加入BindingResult来获取错误信息。
     * 在逻辑处理中我们判断BindingResult知否含有错误信息，如果有错误信息，则直接返回错误信息。
     */

    @CrossOrigin
    @RequestMapping(value = "/dist/index/login", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result login(@Valid @RequestBody VueLoginInfoVo loginInfoVo,BindingResult bindingResult){
//        if(bindingResult.hasErrors()){
////            String message = String.format("登录失败，详细信息[%s]",bindingResult.getFieldError().getDefaultMessage());
////            return ResultFactory.buidFailResult(message);
////        }
////        if(!Objects.equals("cdk",loginInfoVo.getUsername() ) || !Objects.equals("123456",loginInfoVo.getPassword())){
////
////            String message = String.format("登录失败，详细信息【用户名、密码信息不正确】");
////            return ResultFactory.buidFailResult(message);
////        }
        return ResultFactory.buildSuccessResult("登录成功");
    }

}
