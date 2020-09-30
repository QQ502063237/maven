package com.maven.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @description:接口返回统一格式
 * @param: com.wssbdc.system.common.api.vo.HttpResult
 * @author: zhanglei
 * @date: 2019/04/14
 */
@Data
public class HttpResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer code = HttpCode.SYS_OK_CODE;

    private String message;

    private T data;

    public HttpResult() {
    }

    public HttpResult<T> error(String message){
        this.message = message;
        this.code = HttpCode.SYS_ERROR_CODE;
        return this;
    }

    public HttpResult<T> success(String message){
        this.message = message;
        this.code = HttpCode.SYS_OK_CODE;
        return this;
    }

    public static HttpResult<Object> error500(String message){
        HttpResult<Object> r = new HttpResult<>();
        r.setMessage(message);
        r.setCode(HttpCode.SYS_ERROR_CODE);
        return r;
    }

    public static HttpResult<Object> error500(Object data){
        HttpResult<Object> r = new HttpResult<>();
        r.setData(data);
        r.setCode(HttpCode.SYS_ERROR_CODE);
        return r;
    }

    public static HttpResult<Object> ok(String message){
        HttpResult<Object> r = new HttpResult<>();
        r.setMessage(message);
        r.setCode(HttpCode.SYS_OK_CODE);
        return r;
    }

    public static HttpResult<Object> result(String message,Integer code,Object data){
        HttpResult<Object> r = new HttpResult<>();
        r.setMessage(message);
        r.setCode(code);
        r.setData(data);
        return r;
    }


    public static HttpResult<Object> ok(){
        HttpResult<Object> r = new HttpResult<>();
        r.setMessage(HttpCode.OK_MESSAGE);
        r.setCode(HttpCode.SYS_OK_CODE);
        return r;
    }

    public static HttpResult<Object> error500(){
        HttpResult<Object> r = new HttpResult<>();
        r.setMessage(HttpCode.ERROR_MESSAGE);
        r.setCode(HttpCode.SYS_ERROR_CODE);
        return r;
    }

    public static HttpResult<Object> ok(Object data){
        HttpResult<Object> r = new HttpResult<>();
        r.setData(data);
        r.setCode(HttpCode.SYS_OK_CODE);
        return r;
    }


    public static HttpResult<Object> custom(int code, String msg) {
        HttpResult<Object> r = new HttpResult<Object>();
        r.setCode(code);
        r.setMessage(msg);
        return r;
    }

    public HttpResult<T> authError(String message){
        this.message = message;
        this.code = HttpCode.SYS_NO_AUTHEN;
        return this;
    }
    /**
     * 无权限访问返回结果
     */
    public static HttpResult<Object> noauth(String msg) {
        return custom(HttpCode.SYS_NO_AUTHZ, msg);
    }

    /**
     * 登录失败访问返回结果
     */
    public static HttpResult<Object> authen(String msg) {
        return custom(HttpCode.SYS_NO_AUTHEN, msg);
    }

    /**
     * 认证token失败访问返回结果
     */
    public static HttpResult<Object> tokenError(String msg) {
        return custom(HttpCode.SYS_TOKEN_ERROR, msg);
    }

    /**
     * 首先根据请求Url查找有没有对应的控制器，404异常
     */
    public static HttpResult<Object> notFoundError(String msg) {
        return custom(HttpCode.NOT_FOUND_ERROR, msg);
    }

    /**
     * 刷新token返回
     */
    public static HttpResult<Object> refreshTokenResult(String msg,String token) {
        return result(msg,HttpCode.REFRESH_TOKEN_CODE, token);
    }
}
