package com.manage.project.mapper;

import com.manage.project.model.UserMessageInfo;
import org.springframework.stereotype.Component;

@Component
public interface UserMessageMapper {
    void addUserMessage(UserMessageInfo messageInfo);
}
