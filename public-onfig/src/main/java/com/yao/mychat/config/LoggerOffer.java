package com.yao.mychat.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志提供类
 */
public class LoggerOffer {
    public static Logger getBusinessLogger() { return LoggerFactory.getLogger("BUSINESS"); }

    //错误日志
    public static Logger getErrorLogger() {
        return LoggerFactory.getLogger("ERROR");
    }

    //系统日志
    public static Logger getSystemLogger() {
        return LoggerFactory.getLogger("SYSTEM");
    }
}
