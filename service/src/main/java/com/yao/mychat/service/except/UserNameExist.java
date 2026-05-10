package com.yao.mychat.service.except;

/**
 * 用户名已存在存在异常类
 */
public class UserNameExist extends RuntimeException{
    public UserNameExist(String message) {
        super(message);
    }

    public UserNameExist(String message, Throwable cause) {
        super(message, cause);
    }
}
