package com.icbc.qa.exception;

public class ParamException extends RuntimeException {
    public ParamException(){
        super();
    }
    public ParamException(String message){
        super(message);
    }
    public ParamException(String message,Throwable cause){
        super(message,cause);
    }
}
