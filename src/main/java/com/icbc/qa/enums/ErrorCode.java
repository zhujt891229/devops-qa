package com.icbc.qa.enums;

public enum ErrorCode {

    ERROR(100000,"网络异常"),
    INSERT_ERROR(100001,"新增失败"),
    UPDATE_ERROR(100001,"更新失败"),
    INSERT_AGAIN_ERROR(100001,"添加重复数据"),
    DELETE_ERROR(100001,"删除失败"),
    CONDITION_ERROR(100001,"请清除已选条件，重新选择过滤条件"),
    NO_AUTHORITY_ERROR(100001,"无管理员权限"),
    REQUEST_PARAM_ERROR(100001,"请求参数异常"),
    COMMIT_AGAIN_ERROR(100001,"请不要重复提交"),
    NOT_LOGIN_ERROR(100001,"请先登录");

    private int code;
    private String msg;
    ErrorCode(int code,String msg){
        this.code=code;
        this.msg=msg;
    }
    public int getCode(){
        return this.code;
    }
    public void setCode(int code){
        this.code=code;
    }
    public String getMsg(){
        return this.msg;
    }
    public void setMsg(String msg){
        this.msg=msg;
    }
}
