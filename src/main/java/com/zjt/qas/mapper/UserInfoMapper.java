package com.zjt.qas.mapper;

import com.zjt.qas.model.entity.UserInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

public interface UserInfoMapper {
    @Options(useGeneratedKeys = true,keyProperty = "userId",keyColumn = "user_id")
    @Insert("insert into user_info (username,salt,password,is_delete,phone_number,created_time,updated_time) " +
            "values (#{userInfo.username},#{userInfo.salt},#{userInfo.password},#{userInfo.isDelete},#{userInfo.phoneNumber},#{userInfo.createdTime},#{userInfo.updatedTime})")
    int insert(@Param("userInfo") UserInfo userInfo);

    int updateByPrimaryKeySelective(UserInfo userInfo);

    UserInfo selectByUserId(@Param("userId") Integer userId);

    UserInfo selectByUsername(@Param("username") String username);

    UserInfo selectByUserIdAndPassword(Integer userId, String password);

    UserInfo selectByUsernameAndPassword(String username,String password);

    @Delete("delete from user_info where user_id = #{userId}")
    int deleteByUserId(Integer userId);
}
