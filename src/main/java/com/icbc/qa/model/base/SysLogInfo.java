package com.icbc.qa.model.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysLogInfo {
    private String id;
    private String level;
    private String reqUrl;
    private String method;
    private String param;
    private String response;
    private String totalTime;
    private String errorContent;
    private String operation;
    private String clientIp;
    private String operatorId;
    private String createTime;

//    private String count;
//    private String percentage;//占比
//    private String zong;//总数
}
