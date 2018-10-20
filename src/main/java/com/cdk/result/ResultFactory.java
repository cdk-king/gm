package com.cdk.result;

/**
 * @description 响应结果生成工厂类
 * @author
 * @date 2018年月日
 * @Copyright 版权所有 (c)
 * @memo 无备注说明
 */

public class ResultFactory {

    public static Result buildSuccessResult(Object data){
        return buidResult(ResultCode.SUCCESS,"成功",data);
    }

    public static Result buidFailResult(String message){
        return buidResult(ResultCode.FAIL,message,null);
    }

    public static Result buidResult(ResultCode resultCode, String message, Object data) {
        return  buidResult(resultCode,message,data);
    }

    public static Result buidResult(int resultCode,String message,Object data){

        return new Result(resultCode,message,data);
    }

}
