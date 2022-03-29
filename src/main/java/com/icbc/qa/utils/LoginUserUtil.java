package com.icbc.qa.utils;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class LoginUserUtil {
    private static final Logger log = LoggerFactory.getLogger(LoginUserUtil.class);
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
}
