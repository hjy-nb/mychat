package com.yao.mychat.service.except;

/**
 * 手机号已存在异常
 */
public class PhoneExist extends RuntimeException{
    public PhoneExist(String message){
        super(message);
    }

    public PhoneExist(String message, Throwable cause){
        super(message, cause);
    }
}
