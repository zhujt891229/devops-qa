package com.zjt.qas.aspect;

import com.zjt.qas.service.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
@SuppressWarnings("unused")
public class LoginCheckAspect {
    @Autowired
    private UserService userService;

    @Pointcut("@annotation(com.zjt.qas.aspect.AuthVerify)")
    private void pointcut(){};

    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] args = joinPoint.getArgs();

//        UserInfo user = (UserInfo)SecurityUtils.getSubject().getPrincipal();
//        UserInfo userInfo = userService.getUserInfo(user.getUserId());
//        if(userInfo.getIsDelete()==1){
//            return Response.fail("当前账户已经被禁用");
//        }
        return joinPoint.proceed(args);
    }
}
