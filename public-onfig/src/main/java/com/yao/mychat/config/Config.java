package com.yao.mychat.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 公共配置类
 */
@Configuration
public class Config {
    //业务日志
    public Logger getBusinessLogger() { return LoggerFactory.getLogger("BUSINESS"); }

    //错误日志
    public Logger getErrorLogger() {
        return LoggerFactory.getLogger("ERROR");
    }

    //系统日志
    public Logger getSystemLogger() {
        return LoggerFactory.getLogger("SYSTEM");
    }
}
