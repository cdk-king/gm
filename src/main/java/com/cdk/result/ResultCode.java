package com.cdk.result;

/**
 * @description 响应码枚举，参考 HTTP状态码的语义
 * @author
 * @date 2018年月日
 * @Copyright 版权所有 (c)
 * @memo 无备注说明
 */

public enum ResultCode {

    SUCCESS(200),
    FAIL(400),
    UNAUTHORIZED(401),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500);

    public int code;

    ResultCode(int code){
        this.code = code;
    }

}
