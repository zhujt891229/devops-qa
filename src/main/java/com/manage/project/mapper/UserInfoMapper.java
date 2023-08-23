package com.manage.project.mapper;

import com.manage.project.model.UserInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

public interface UserInfoMapper {
    @Options(useGeneratedKeys = true,keyProperty = "userId",keyColumn = "user_id")
    @Insert("insert into user_info (user_name,salt,password,is_delete,phone_number,created_time,modified_time) " +
            "values (#{userInfo.userName},#{userInfo.salt},#{userInfo.password},#{userInfo.isDelete},#{userInfo.phoneNumber},#{userInfo.createdTime},#{userInfo.modifiedTime})")
    int insert(@Param("userInfo") UserInfo userInfo);

    int updateByPrimaryKeySelective(UserInfo userInfo);

    UserInfo selectByUserId(@Param("userId") Integer userId);

    UserInfo selectByUserIdAndPassword(Integer userId, String password);

    @Delete("delete from user_info where user_id = #{userId}")
    int deleteByUserId(Integer userId);
}
