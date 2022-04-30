package com.manage.project.service;

import com.manage.project.model.UserInfo;
import com.manage.project.param.UserLoginParam;

public interface LoginService {
    UserInfo validateUser(UserLoginParam param);
}
