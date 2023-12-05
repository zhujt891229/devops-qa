package com.zjt.qas.controller;

import com.zjt.qas.common.Response;
import com.zjt.qas.common.ResponseResult;
import com.zjt.qas.common.ResultInfo;
import com.zjt.qas.config.DataConfig;
import com.zjt.qas.config.RedisConfig;
import com.zjt.qas.datasource.DataSourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Api("home")
@RestController
@RequestMapping("home")
public class HomeController {
    //@Autowired
    //private DataConfig dataConfig;
    //private RedisConfig dataConfig;
    //private DataSourceConfig dataConfig;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/env")
//    @ApiOperation("env1")
    public Object testEnv() {
//        RedisConfig redisConfig=new RedisConfig();
        stringRedisTemplate.opsForValue().set("aaa","bbb");
        Object aaaValue = stringRedisTemplate.opsForValue().get("aaa");
        return ResponseResult.success(aaaValue);
        //return Response.ok(aaaValue);
    }
}
