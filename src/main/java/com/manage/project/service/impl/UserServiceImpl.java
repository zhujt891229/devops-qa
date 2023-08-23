package com.manage.project.service.impl;

import com.manage.project.mapper.UserInfoMapper;
import com.manage.project.model.UserInfo;
import com.manage.project.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserInfoMapper userInfoMapper;
    @Override
    public UserInfo getUserInfo(Integer userId) {
        UserInfo userInfo = userInfoMapper.selectByUserId(userId);
        return userInfo;
    }

    @Override
    public int delete(Integer userId) {
        return userInfoMapper.deleteByUserId(userId);
    }
}
