package com.zjt.qas.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultInfo implements Serializable {
    private static final long serialVersionUID=2633283546876721434L;
    private Integer code = 200;
    private String msg = "success";
    private String description;
    private Object data;

    private HashMap<String,Object> exend;

    public Integer getCode(){
        return this.code;
    }
    public void setCode(Integer code){
        this.code=code;
    }
    public String getMsg(){
        return this.msg;
    }
    public void setMsg(String msg){
        this.msg=msg;
    }
    public Object getData(){
        return this.data;
    }
    public ResultInfo setData(Object data){
        this.data = data;
        return this;
    }

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description= description;
    }

    @JsonIgnore
    public HashMap<String,Object> getExend(){
        return this.exend;
    }

    public void setExend(HashMap<String,Object> exend){
        this.exend=exend;
    }

    public ResultInfo(){
        this.exend = new HashMap<>();
    }

    public static ResultInfo failure(ErrorCode errorCode){
        int code = errorCode.getCode();
        String msg=errorCode.getMsg();
        return failure(code,msg);
    }

    public static ResultInfo failure(int code,String msg){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(code);
        resultInfo.setMsg(msg);
        return resultInfo;
    }

    public static ResultInfo ok(String msg){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.put("msg",msg);
        return resultInfo;
    }

     public static ResultInfo ok(Map map){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.exend.putAll(map);
        return resultInfo;
     }

     public static ResultInfo ok(){
        return new ResultInfo();
     }

     public static ResultInfo ok(Object obj){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setData(obj);
        return resultInfo;
     }

     public static ResultInfo ok(Object obj,int code){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(code);
        resultInfo.setData(obj);
        return resultInfo;
     }

     public static ResultInfo ok(Object obj,int code,String msg){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(code);
        resultInfo.setMsg(msg);
        resultInfo.setData(obj);
        return resultInfo;
     }

     public ResultInfo put(String key,Object value){
        exend.put(key,value);
        return this;
     }
}
