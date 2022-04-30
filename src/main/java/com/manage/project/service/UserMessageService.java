package com.manage.project.service;

import com.manage.project.model.UserMessageInfo;

import java.util.List;

public interface UserMessageService {
    void sendMessage(UserMessageInfo messageInfo);
    void sendMessage(List<UserMessageInfo> messageInfoList);
}
