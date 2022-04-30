package com.manage.project.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.manage.project.model.UserInfo;
import com.manage.project.model.base.SysLogInfo;
import com.manage.project.service.SysLogService;
import org.apache.catalina.connector.RequestFacade;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Component
@Aspect
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
    @Autowired
    private SysLogService sysLogService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Pointcut("execution(public * com.manage.project.controller.*.*(..))"
//        +"&&!execution(public * com.manage.project.branch.controller.BranchController.queryJcpPageStatus(..))"
        )
    private void controllerAspect(){
        logger.info("切点");
    }

    @Before("controllerAspect()")
    public void methodBefore(JoinPoint joinPoint){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if(ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }

        logger.info("**********请求内容**********");
        logger.info("*****URL:"+request.getRequestURI());
        logger.info("*****HTTP_METHOD:"+request.getMethod());
        logger.info("*****IP:"+ip);
        logger.info("*****HOST:"+request.getRemoteHost());
        logger.info("*****PORT:"+request.getRemotePort());
        logger.info("*****ARGS:"+ Arrays.toString(joinPoint.getArgs()));
        logger.info("*****CLASS_METHOD:"+joinPoint.getSignature().getDeclaringTypeName()+","+joinPoint.getSignature().getName());
        logger.info("**********请求内容**********");


        SysLogInfo sysLogInfo = SysLogInfoUtil.getSysLogInfo();
        Object[] args = joinPoint.getArgs();
        String param = "";
        try{
            if(args.length!=0&&args[0]!=null){
                if(AspectUtil.isSelfObject(args)){
                    if(!args[0].getClass().equals(RequestFacade.class)&&!args[0].getClass().equals(RequestFacade.class)){
                        param= JSONObject.toJSONString(args[0]);
                    }
                }else{
                    param=Arrays.toString(args);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        String operatorId="";
        try{
            String token=request.getHeader("x-token");
            if(null!=token&&!"undefined".equals(token)){
                operatorId=stringRedisTemplate.opsForValue().get(token+"userId");
                if(StringUtils.isEmpty(operatorId)){
                    String s=stringRedisTemplate.opsForValue().get(token);
                    UserInfo euser = JSON.parseObject(s,UserInfo.class);
                    if(null!=euser){
                        operatorId=euser.getUserId()+"";
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        sysLogInfo.setClientIp(ip);
        sysLogInfo.setMethod(joinPoint.getSignature().getName());
        sysLogInfo.setOperation(joinPoint.getSignature().getDeclaringTypeName());
        sysLogInfo.setOperatorId(operatorId);
        //非登录接口才记录参数
        if(!"login".equals(joinPoint.getSignature().getName())){
            sysLogInfo.setParam(param);
        }
        sysLogInfo.setLevel("info");
        sysLogInfo.setReqUrl(request.getRequestURI());
    }

    @AfterReturning(returning = "o",pointcut = "controllerAspect()")
    public void methodAfter(Object o){
        SysLogInfo sysLogInfo=SysLogInfoUtil.getSysLogInfo();
        logger.info("**********返回内容**********");
        logger.info(JSONObject.toJSONString(o));
        logger.info("**********返回内容**********");
        JSONObject rjo = null;
        try{
            rjo=JSONObject.parseObject(JSONObject.toJSONString(o));
        }catch(Exception e){
            e.printStackTrace();
            sysLogService.addSysLog(sysLogInfo);
        }
        if(rjo!=null){
            String msg = rjo.getString("msg");
            if(!StringUtils.isEmpty(msg)){
                if(!msg.equals("缓存失效，请重新登录")&&!msg.equals("请先登录")){
                    sysLogService.addSysLog(sysLogInfo);
                }
            }else{
                sysLogService.addSysLog(sysLogInfo);
            }
        }
    }

    @Around("controllerAspect()")
    public Object around(ProceedingJoinPoint joinPoint){
        SysLogInfo sysLogInfo = SysLogInfoUtil.getSysLogInfo();
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        try{
            long startTimeMillis = System.currentTimeMillis();
            Object result = joinPoint.proceed();
            long execTimeMillis = System.currentTimeMillis()-startTimeMillis;
            logger.info("接口执行时间："+execTimeMillis+"毫秒");
            sysLogInfo.setTotalTime(String.valueOf(execTimeMillis));
            return result;
        }catch(Throwable e){
            e.printStackTrace();
            logger.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @AfterThrowing(pointcut = "controllerAspect()",throwing = "throwable")
    public void afterThrow(JoinPoint joinPoint,Throwable throwable){
        SysLogInfo sysLogInfo = SysLogInfoUtil.getSysLogInfo();
        sysLogInfo.setLevel("error");
        //sysLogInfo.setErrorContent(throwable.getMessage());
        sysLogService.addSysLog(sysLogInfo);
    }
}

