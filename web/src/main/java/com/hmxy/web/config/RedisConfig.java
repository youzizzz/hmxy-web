package com.hmxy.web.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @discripeion:
 * @author: YM10147
 * @date: 2018/11/21 15:57
 */
@Configuration
public class RedisConfig {


    /**
     * redis缓存管理类
     * @param redisConnectionFactory
     * @return
     */
    @ConditionalOnClass
    public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory){
        return RedisCacheManager.create(redisConnectionFactory);
    }


    /**
     * 以string形式操作redis模板类
     * @param redisConnectionFactory
     * @return
     */
    @ConditionalOnClass
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        StringRedisTemplate redisTemplate=new StringRedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
}
