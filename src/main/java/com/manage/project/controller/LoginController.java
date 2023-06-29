package com.manage.project.controller;

import com.manage.project.common.Response;
import com.manage.project.model.UserInfo;
import com.manage.project.param.LoginParam;
import com.manage.project.service.LoginService;
import com.manage.project.utils.MD5Util;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping("/check_login")
    public Response<UserInfo> checkLogin(@RequestBody LoginParam loginParam) {
        Response<UserInfo> response;
        try {
            UserInfo userInfo = loginService.validateUser(loginParam);
            response = Response.ok(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
            response = Response.fail("login error");
        }
        return response;
    }

    @RequestMapping("/encrypt")
    public Response<String> encrypt(String password){
        final String SALT = "12345";
        String s = MD5Util.md5encrypt(password, SALT);
        return Response.ok("success",s);
    }

    @RequestMapping("/signIn")
    public Response<UserInfo> signIn(@RequestBody LoginParam loginParam){
        UserInfo user = loginService.register(loginParam);
        return Response.ok(user);
    }
}
