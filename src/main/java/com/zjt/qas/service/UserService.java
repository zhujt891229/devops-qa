package com.zjt.qas.service;

import com.zjt.qas.model.entity.UserInfo;

import java.util.List;

public interface UserService {

    UserInfo getUserInfo(Integer userId);

    int delete(Integer userId);

    List<UserInfo> query();
}
