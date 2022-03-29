package com.icbc.qa.controller;

import com.icbc.qa.config.DataConfig;
import com.icbc.qa.config.RedisConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("home controller")
@RestController
public class HomeController {
    @Autowired
    private DataConfig dataConfig;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/env")
    @ApiOperation("env")
    public Object testEnv() {
        RedisConfig redisConfig=new RedisConfig();
        stringRedisTemplate.opsForValue().set("aaa","bbb");
        String aaa = stringRedisTemplate.opsForValue().get("aaa");
        return dataConfig;
    }
}
