package com.cdk.result;

/**
 * @description 响应码枚举，参考 HTTP状态码的语义
 * @author
 * @date 2018年月日
 * @Copyright 版权所有 (c)
 * @memo 无备注说明
 */

public enum ResultCode {
    //响应码	响应解释
    //200	请求成功
    //400	请求失败
    //401	未经授权的
    //403	拒绝访问
    //404	未找到
    //500	服务器内部错误
    SUCCESS(200), FAIL(400), UNAUTHORIZED(401), Forbidden(403), NOT_FOUND(404), INTERNAL_SERVER_ERROR(500);

    public int code;

    ResultCode(int code) {
        this.code = code;
    }

}
