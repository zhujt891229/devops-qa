package com.zjt.qas.service;

import com.zjt.qas.model.entity.UserInfo;
import com.zjt.qas.model.param.LoginParam;

public interface LoginService {
    UserInfo validateUser(LoginParam param);
    UserInfo validateUser1(LoginParam loginParam);

    String login(String username,String password);

    UserInfo register(LoginParam loginParam);
}
