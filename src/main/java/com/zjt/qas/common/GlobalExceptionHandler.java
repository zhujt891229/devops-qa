package com.zjt.qas.common;

import com.zjt.qas.common.exception.BusinessException;
import com.zjt.qas.common.exception.ParamException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler({RuntimeException.class})
    @ResponseBody
    public Response<String> exceptionHandle(RuntimeException e){
        log.error("unknown exception",e);
        if(e instanceof ParamException ||e instanceof BusinessException){
            return Response.fail(e.getMessage());
        }
        return Response.fail("系统异常");
    }

    @ExceptionHandler({Error.class})
    @ResponseBody
    public Response<String> errorHandle(Error e){
        log.error("unknown exception",e);
        return Response.fail("系统异常");
    }
}
