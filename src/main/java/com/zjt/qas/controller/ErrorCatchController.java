package com.zjt.qas.controller;

import com.zjt.qas.common.ResponseResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ec")
public class ErrorCatchController {
    @RequestMapping("/getResult")
    public ResponseResult<String> checkLogin(int num) {
        int result = 1/num;
        return ResponseResult.success();
    }
}
