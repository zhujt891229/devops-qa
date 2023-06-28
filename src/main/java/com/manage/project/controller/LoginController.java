package com.manage.project.controller;

import com.manage.project.common.CommonResponse;
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

    private final Integer SALT = 12345;

    @PostMapping("/check_login")
    public CommonResponse<UserInfo> checkLogin(@RequestBody LoginParam loginParam) {
        CommonResponse<UserInfo> response;
        try {
            UserInfo userInfo = loginService.validateUser(loginParam);
            response = CommonResponse.ok(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
            response = CommonResponse.fail("login error");
        }
        return response;
    }

    @RequestMapping("/encrypt")
    public CommonResponse<String> encrypt(String password){
        String s = MD5Util.md5Digest(password, SALT);
        return CommonResponse.ok("success",s);
    }

    @RequestMapping ("/decrypt")
    public CommonResponse<String> decrypt(String s){
        String password = "";
        return CommonResponse.ok("success",password);
    }
}
