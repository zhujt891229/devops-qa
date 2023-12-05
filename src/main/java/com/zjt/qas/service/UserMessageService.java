package com.zjt.qas.service;

import com.zjt.qas.model.UserMessageInfo;

import java.util.List;

public interface UserMessageService {
    void sendMessage(UserMessageInfo messageInfo);
    void sendMessage(List<UserMessageInfo> messageInfoList);
}
