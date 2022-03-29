package com.icbc.qa.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.icbc.qa.mapper.UserMessageMapper;
import com.icbc.qa.model.UserMessageInfo;
import com.icbc.qa.service.UserMessageService;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class UserMessageServiceImpl implements UserMessageService {
    @Resource
    private UserMessageMapper userMessageMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public void sendMessage(UserMessageInfo messageInfo) {
        List<UserMessageInfo> messageInfoList = new ArrayList<>();
        messageInfoList.add(messageInfo);
        sendMessage(messageInfoList);
    }

    @Override
    public void sendMessage(List<UserMessageInfo> messageInfoList) {
        for(UserMessageInfo messageInfo:messageInfoList){
            userMessageMapper.addUserMessage(messageInfo);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userIdWithType",messageInfo.getUserId()+"-"+messageInfo.getChannel());
            jsonObject.put("message",messageInfo);
            stringRedisTemplate.convertAndSend(messageInfo.getChannel(),JSONObject.toJSONString(jsonObject));
        }
    }
}
