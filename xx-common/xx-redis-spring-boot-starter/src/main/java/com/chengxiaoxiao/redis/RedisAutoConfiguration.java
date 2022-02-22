package com.chengxiaoxiao.redis;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import com.chengxiaoxiao.core.constant.RedisKeyConstant;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;
import java.time.Duration;

/**
 * Redis自动配置类
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2022-02-22 16:22
 */
public class RedisAutoConfiguration extends CachingConfigurerSupport {

    public RedisAutoConfiguration() {
    }

    /**
     * 注入基本的Template
     *
     * @param connectionFactory 连接工厂
     * @return Template
     */
    @Bean
    public RedisTemplate<String, Serializable> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Serializable> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericFastJsonRedisSerializer());
        template.setConnectionFactory(connectionFactory);
        return template;
    }

    /**
     * 修改掉默认的缓存管理器。增加默认的超时时间
     *
     * @param factory 工厂
     * @return 缓存管理器
     */
    @Bean
    @Primary
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        redisCacheConfiguration
                .entryTtl(Duration.ofMinutes(RedisKeyConstant.TIMEOUT))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericFastJsonRedisSerializer()));

        return RedisCacheManager
                .builder(RedisCacheWriter.nonLockingRedisCacheWriter(factory))
                .cacheDefaults(redisCacheConfiguration)
                .build();
    }
}
