package com.manage.project.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> implements Serializable {

    private static final long serialVersionUID= -9128724469204152890L;

    // 状态码,成功为0,失败为1,也封装成常量类
    private Integer code;

    // 消息,成功消息或者失败消息
    private String msg;

    // 要返回的数据
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private Response(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Response(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 状态码 + 成功提示信息
     */
    public static <T> Response<T> ok(String msg) {
        return new Response<>(Constant.SUCCESS, msg);
    }
    public static <T> Response<T> ok(){
        return ok("success");
    }

    /**
     * 状态码 + 成功提示信息 + 数据
     */
    public static <T> Response<T> ok(String msg, T data) {
        return new Response<>(Constant.SUCCESS, msg, data);
    }

    public static <T> Response<T> ok(T data){
        return ok("success",data);
    }
    /**
     * 状态码 + 错误信息
     */
    public static <T> Response<T> fail(String msg) {
        return new Response<>(Constant.ERROR, msg);
    }

}
