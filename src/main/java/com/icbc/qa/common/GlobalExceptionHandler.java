package com.icbc.qa.common;

import com.icbc.qa.exception.BusinessException;
import com.icbc.qa.exception.ParamException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler({RuntimeException.class})
    @ResponseBody
    public ResultInfo exceptionHandle(RuntimeException e){
        log.error("unknown exception",e);
        if(e instanceof ParamException||e instanceof BusinessException){
            return ResultInfo.failure(201,e.getMessage());
        }
        return ResultInfo.failure(500,"系统异常");
    }

    @ExceptionHandler({Error.class})
    @ResponseBody
    public ResultInfo errorHandle(Error e){
        log.error("unknown exception",e);
        return ResultInfo.failure(500,"系统异常");
    }
}
