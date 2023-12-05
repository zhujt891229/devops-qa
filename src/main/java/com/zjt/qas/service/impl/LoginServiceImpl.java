package com.zjt.qas.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.zjt.qas.mapper.UserInfoMapper;
import com.zjt.qas.model.entity.UserInfo;
import com.zjt.qas.model.param.LoginParam;
import com.zjt.qas.service.LoginService;
import com.zjt.qas.utils.HttpUtil;
import com.zjt.qas.utils.LoginUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;

@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    UserInfoMapper userInfoMapper;

    private String BAAS_LOGIN;
    private String BAAS_GET_USER_INFO;

    private RedisTemplate redisTemplate;

    @Override
    public UserInfo validateUser1(LoginParam param) {
        UserInfo userInfo = null;
        JSONObject ret = sendHttpRequest(param,BAAS_LOGIN);
        if(null!=ret){
            String message = ret.getString("message");
            if("successful".equals(message)){
                JSONObject values = ret.getJSONObject("values");
                String token = values.getString("token");
                String s = HttpUtil.sendPostWithToken(BAAS_GET_USER_INFO,new HashMap<>(),token);
                JSONObject jsonObject =JSONObject.parseObject(s);
                if(jsonObject!=null&&(userInfo=jsonObject.getObject("data",UserInfo.class))!=null){
                    UserInfo userInfoExisted = userInfoMapper.selectByUserId(userInfo.getUserId());
                    if(userInfoExisted==null){
                        userInfoMapper.insert(userInfo);
                    }else{
                        userInfo.setUserId(userInfoExisted.getUserId());
                        userInfoMapper.updateByPrimaryKeySelective(userInfo);
                    }
                }else{
                    userInfo = userInfoMapper.selectByUserId(param.getUserId());
                }
            }
        }
        return userInfo;
    }

    private JSONObject sendHttpRequest(LoginParam param, String url){
        HashMap<String,Object> map = Maps.newHashMap();
        map.put("userId",param.getUserName());
        map.put("password",param.getPassword());
        String res = HttpUtil.sendPost(url,map);
        JSONObject ret = JSONObject.parseObject(res);
        return ret;
    }

    @Override
    public UserInfo validateUser(LoginParam loginParam) {
        UserInfo userInfo = userInfoMapper.selectByUserIdAndPassword(loginParam.getUserId(),loginParam.getPassword());
        if (userInfo == null) {
            throw new RuntimeException("用户不存在");
        }
        String token = LoginUtil.generateToken(loginParam.getUserName());

        return userInfo;
    }

    @Override
    public UserInfo register(LoginParam loginParam) {

//        UserInfo user = userInfoMapper.selectByUserId(loginParam.getUserId());
//        if (user != null) {
//            throw new RuntimeException("用户名已被占用");
//        }
        UserInfo user = new UserInfo();

        //密码加密：使用md5算法加密
//        String oldPassword = loginParam.getPassword();
        //获取盐值
//        String salt = UUID.randomUUID().toString().toUpperCase();
        //将密码和盐值作为一个整体进行加密处理
//        String md5Password = MD5Util.md5encrypt(oldPassword, salt);
//        user.setPassword(md5Password);
        user.setSalt("");
        user.setPhoneNumber(null==loginParam.getPhoneNumber()?"": loginParam.getPhoneNumber());
        user.setUserName(loginParam.getUserName());
        user.setPassword(loginParam.getPassword());
        user.setIsDelete(0);
        LocalDateTime date = LocalDateTime.now();
        user.setCreatedTime(date);
        user.setModifiedTime(date);

        int rows = userInfoMapper.insert(user);
        if (rows != 1) {
            throw new RuntimeException("失败(用户创建过程中产生了未知的异常)");
        }
        return user;
    }
}
