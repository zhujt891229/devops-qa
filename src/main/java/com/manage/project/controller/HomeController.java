package com.manage.project.controller;

import com.manage.project.config.DataConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Api("home_controller")
@RestController
@RequestMapping("home_controller")
public class HomeController {
    @Autowired
    private DataConfig dataConfig;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/env")
//    @ApiOperation("env1")
    public Object testEnv() {
//        RedisConfig redisConfig=new RedisConfig();
        stringRedisTemplate.opsForValue().set("aaa","bbb");
        String aaaValue = stringRedisTemplate.opsForValue().get("aaa");
        return dataConfig;
    }
}
