package com.manage.project.exception;

import java.util.function.Supplier;

public class BusinessException extends RuntimeException implements Supplier<BusinessException> {
    public BusinessException(){
        super();
    }
    public BusinessException(String message){
        super(message);
    }
    @Override
    public BusinessException get() {
        return this;
    }
}
