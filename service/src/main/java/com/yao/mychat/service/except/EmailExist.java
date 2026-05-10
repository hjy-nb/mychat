package com.yao.mychat.service.except;

/**
 * 邮箱已存在异常
 */
public class EmailExist extends RuntimeException{
    public EmailExist(String message) {
        super(message);
    }

    public EmailExist(String message, Throwable cause){
        super(message, cause);
    }
}
