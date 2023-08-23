package com.manage.project.service;

import com.manage.project.model.UserInfo;

public interface UserService {

    UserInfo getUserInfo(Integer userId);

    int delete(Integer userId);
}
