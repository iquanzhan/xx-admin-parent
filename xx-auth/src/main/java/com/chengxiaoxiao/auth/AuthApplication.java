package com.chengxiaoxiao.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 认证授权中心
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2022-02-22 20:08
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "com.chengxiaoxiao.xxadmin.*.api")
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
