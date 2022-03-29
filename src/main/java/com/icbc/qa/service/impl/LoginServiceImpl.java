package com.icbc.qa.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.icbc.qa.mapper.UserInfoMapper;
import com.icbc.qa.model.UserInfo;
import com.icbc.qa.param.UserLoginParam;
import com.icbc.qa.service.LoginService;
import com.icbc.qa.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.util.HashMap;

public class LoginServiceImpl implements LoginService {
    @Resource
    UserInfoMapper userInfoMapper;

    private String BAAS_LOGIN;
    private String BAAS_GET_USER_INFO;

    @Override
    public UserInfo validateUser(UserLoginParam param) {
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
                        userInfoMapper.insertSelective(userInfo);
                    }else{
                        userInfo.setId(userInfoExisted.getId());
                        userInfoMapper.updateByPrimaryKeySelective(userInfo);
                    }
                }else{
                    userInfo = userInfoMapper.selectByUserId(param.getUserId());
                }
            }
        }
        return userInfo;
    }

    private JSONObject sendHttpRequest(UserLoginParam param,String url){
        HashMap<String,Object> map = Maps.newHashMap();
        map.put("userId",param.getUserId());
        map.put("password",param.getPassword());
        String res = HttpUtil.sendPost(url,map);
        JSONObject ret = JSONObject.parseObject(res);
        return ret;
    }
}
