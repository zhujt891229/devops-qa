package com.manage.project.service.impl;

import com.manage.project.mapper.UserInfoMapper;
import com.manage.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserInfoMapper userInfoMapper;
    @Override
    public String getUserInfo(String userId) {
        System.out.println("查询结束");
        return "123";
    }

    @Override
    public int delete(Integer userId) {
        return userInfoMapper.deleteByUserId(userId);
    }
}
