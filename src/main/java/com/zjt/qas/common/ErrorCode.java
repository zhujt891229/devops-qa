package com.zjt.qas.common;

public enum ErrorCode {

    ERROR(100000,"网络异常"),
    INSERT_ERROR(100001,"新增失败"),
    UPDATE_ERROR(100002,"更新失败"),
    INSERT_AGAIN_ERROR(100003,"添加重复数据"),
    DELETE_ERROR(100004,"删除失败"),
    CONDITION_ERROR(100005,"请清除已选条件，重新选择过滤条件"),
    NO_AUTHORITY_ERROR(100006,"无管理员权限"),
    REQUEST_PARAM_ERROR(100007,"请求参数异常"),
    COMMIT_AGAIN_ERROR(100008,"请不要重复提交"),
    NOT_LOGIN_ERROR(100009,"请先登录"),

    FILE_FORMAT_ERROR(100010,"文件格式不正确"),
    FILE_EMPTY_ERROR(100011,"文件为空"),
    FILE_SUFFIX_ERROR(100012,"文件后缀名不正确"),
    FILE_TYPE_ERROR(100013,"文件类型不正确"),
    FILE_SIZE_ERROR(100014,"文件大小超出限制"),
    ;

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
