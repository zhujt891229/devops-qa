package com.zjt.qas.controller;

import com.zjt.qas.common.Constant;
import com.zjt.qas.common.Response;
import com.zjt.qas.common.ResponseResult;
import com.zjt.qas.model.entity.UserInfo;
import com.zjt.qas.model.param.LoginParam;
import com.zjt.qas.service.LoginService;
import com.zjt.qas.utils.MD5Util;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("login")
public class LoginController {

    @Resource
    private LoginService loginService;

    @GetMapping("/checkLogin")
    public ResponseResult<String> checkLogin(@RequestBody LoginParam loginParam) {
        ResponseResult<String> response;
        try {
            String rawPassword = loginParam.getPassword();
            String ripePassword = MD5Util.md5encrypt(rawPassword, Constant.SALT);

            String token = loginService.login(loginParam.getUsername(), ripePassword);
            //UserInfo userInfo = loginService.validateUser(loginParam);
            response = ResponseResult.success(token);
        } catch (Exception e) {
            e.printStackTrace();
            response = ResponseResult.fail("login error");
        }
        return response;
    }

    @RequestMapping("/encrypt")
    public Response<String> encrypt(String password){
        //final String SALT = "12345";
        String s = MD5Util.md5encrypt(password, Constant.SALT);
        return Response.ok("success",s);
    }

    @RequestMapping("/signIn")
    public Response<UserInfo> signIn(@RequestBody LoginParam loginParam){
        UserInfo user = loginService.register(loginParam);
        return Response.ok();
    }
}
