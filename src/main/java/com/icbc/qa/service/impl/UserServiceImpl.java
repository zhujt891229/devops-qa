package com.icbc.qa.service.impl;

import com.icbc.qa.model.base.SysLogInfo;
import com.icbc.qa.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public String getUserInfo(String userId) {
        System.out.println("查询结束");
        return "123";
    }
}
