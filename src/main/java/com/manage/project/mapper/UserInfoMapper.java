package com.manage.project.mapper;

import com.manage.project.model.UserInfo;
import org.apache.ibatis.annotations.Param;

public interface UserInfoMapper {
    int insertSelective(UserInfo userInfo);

    int updateByPrimaryKeySelective(UserInfo userInfo);

    UserInfo selectByUserId(@Param("userId") String userId);

    UserInfo selectByUserIdAndPassword(@Param("userId") String userId,@Param("password") String password);
}
