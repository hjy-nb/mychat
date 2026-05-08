package com.yao.mychat.start;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 */
@SpringBootApplication(scanBasePackages = "com.yao.mychat")
@MapperScan("com.yao.mychat.mapper")
public class Running {
    public static void main(String[] args) {
        SpringApplication.run(Running.class, args);
    }
}
