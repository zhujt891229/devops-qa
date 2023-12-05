package com.zjt.qas.controller;

import com.zjt.qas.common.Response;
import com.zjt.qas.model.entity.UserInfo;
import com.zjt.qas.model.param.LoginParam;
import com.zjt.qas.service.LoginService;
import com.zjt.qas.utils.MD5Util;
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
