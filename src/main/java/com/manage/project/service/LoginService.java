package com.manage.project.service;

import com.manage.project.model.UserInfo;
import com.manage.project.param.LoginParam;

public interface LoginService {
    UserInfo validateUser(LoginParam param);
    UserInfo validateUser1(LoginParam loginParam);

    UserInfo register(LoginParam loginParam);
}
