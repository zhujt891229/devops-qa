package com.icbc.qa.mapper;

import com.icbc.qa.model.UserMessageInfo;
import org.springframework.stereotype.Component;

@Component
public interface UserMessageMapper {
    void addUserMessage(UserMessageInfo messageInfo);
}
