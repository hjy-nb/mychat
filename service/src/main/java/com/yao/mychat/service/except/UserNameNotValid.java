package com.yao.mychat.service.except;

/**
 * 用户名不存在
 */
public class UserNameNotValid extends RuntimeException{
    public UserNameNotValid(String message) {
        super(message);
    }

    public UserNameNotValid(String message, Throwable cause) {
        super(message, cause);
    }
}
