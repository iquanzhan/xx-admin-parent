package com.chengxiaoxiao.openfeign;

import com.chengxiaoxiao.openfeign.aspect.FeignClientAspect;
import com.chengxiaoxiao.openfeign.interceptor.FeignRequestInterceptor;
import feign.Logger;
import feign.RequestInterceptor;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.*;


/**
 * open-feign自动配置类
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2022-02-23 10:36
 */
@Configuration
@AutoConfigureBefore(FeignAutoConfiguration.class)
public class OpenFeignAutoConfiguration {

    @Bean
    public FeignClientAspect feignClientAspect(){
        return new FeignClientAspect();
    }

    @Bean
    public RequestInterceptor requestInterceptor(){
        return new FeignRequestInterceptor();
    }


    /**
     * 修改开发测试环境下的日志级别
     *
     * @return 日志级别
     */
    @Bean
    @Profile({"dev", "test"})
    Logger.Level feignLevel() {
        return Logger.Level.FULL;
    }

    /**
     * 支持multipart请求
     *
     * @param objectFactory 工厂
     * @return 解码器
     */
    @Bean
    @Primary
    @Scope("prototype")
    public Encoder multipartFormEncoder(ObjectFactory<HttpMessageConverters> objectFactory) {
        return new SpringFormEncoder(new SpringEncoder(objectFactory));
    }






}
