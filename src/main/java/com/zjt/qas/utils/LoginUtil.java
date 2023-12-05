package com.zjt.qas.utils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class LoginUtil {

    private static StringRedisTemplate stringRedisTemplate;
    private static final Logger log = LoggerFactory.getLogger(LoginUtil.class);

    @Resource(name = "stringRedisTemplate")
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate){
        LoginUtil.stringRedisTemplate = stringRedisTemplate;
    }

    public static String getCurrentToken(){
        try{
            ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = sra.getRequest();
            String token = request.getHeader("x-token");
            return token;
        }catch(Exception e){
            if(log.isErrorEnabled()){
                log.error("获取token失败:{}",e.toString());
            }
            return null;
        }
    }

    public static String generateToken(Integer userId) {
        String key = "login_token_" + userId;
        String value = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(key, value, Duration.ofHours(2));
        return value;
    }

    public static boolean validateToken(Integer userId, String token) {
        String key = "login_token_"+userId;
        String value = stringRedisTemplate.opsForValue().get(key);
        if (!StringUtils.equals(value, token)) {
            return false;
        }
        stringRedisTemplate.expire(key,2L, TimeUnit.HOURS);
        return true;

    }

    public static void logout(Integer userId) {
        String key = "login_token_" + userId;
        stringRedisTemplate.delete(key);
    }
}
