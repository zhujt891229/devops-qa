package com.zjt.qas.controller;

import com.zjt.qas.common.Response;
import com.zjt.qas.model.entity.UserInfo;
import com.zjt.qas.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;
    @RequestMapping("/delete")
    public Response<Integer> delete(Integer userId){
        int i = userService.delete(userId);
        return Response.ok(i);
    }
    @RequestMapping("/query")
    public Response<List<UserInfo>> query(){
        List<UserInfo> list = userService.query();
        return Response.ok(list);
    }

}
