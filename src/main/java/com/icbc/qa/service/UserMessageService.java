package com.icbc.qa.service;

import com.icbc.qa.model.UserMessageInfo;

import java.util.List;

public interface UserMessageService {
    void sendMessage(UserMessageInfo messageInfo);
    void sendMessage(List<UserMessageInfo> messageInfoList);
}
