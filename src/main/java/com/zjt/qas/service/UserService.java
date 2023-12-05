package com.zjt.qas.service;

import com.zjt.qas.model.entity.UserInfo;

public interface UserService {

    UserInfo getUserInfo(Integer userId);

    int delete(Integer userId);
}
