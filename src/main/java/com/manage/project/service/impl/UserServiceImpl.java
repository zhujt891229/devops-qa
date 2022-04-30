package com.manage.project.service.impl;

import com.manage.project.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public String getUserInfo(String userId) {
        System.out.println("查询结束");
        return "123";
    }
}
