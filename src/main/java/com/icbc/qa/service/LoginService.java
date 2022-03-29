package com.icbc.qa.service;

import com.icbc.qa.model.UserInfo;
import com.icbc.qa.param.UserLoginParam;

public interface LoginService {
    UserInfo validateUser(UserLoginParam param);
}
