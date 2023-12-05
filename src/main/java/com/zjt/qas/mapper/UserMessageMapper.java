package com.zjt.qas.mapper;

import com.zjt.qas.model.UserMessageInfo;
import org.springframework.stereotype.Component;

@Component
public interface UserMessageMapper {
    void addUserMessage(UserMessageInfo messageInfo);
}
