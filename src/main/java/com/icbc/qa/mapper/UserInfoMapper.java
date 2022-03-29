package com.icbc.qa.mapper;

import com.icbc.qa.model.UserInfo;
import org.apache.ibatis.annotations.Param;

public interface UserInfoMapper {
    int insertSelective(UserInfo userInfo);

    int updateByPrimaryKeySelective(UserInfo userInfo);

    UserInfo selectByUserId(@Param("userId") String userId);
}
