package com.zjt.qas.service.impl;

import com.zjt.qas.mapper.UserInfoMapper;
import com.zjt.qas.model.entity.UserInfo;
import com.zjt.qas.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public List<UserInfo> query() {
        return null;
    }
}
