package com.zjt.qas.utils.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;
@Configuration
public class RedisLockConfig {

    /**
     * 超时时间默认1分钟
     * @param connectionFactory
     * @return
     */
    @Bean("defaultRedisLockRegistry")
    public RedisLockRegistry defaultRedisLockRegistry(RedisConnectionFactory connectionFactory){
        //默认1分钟超时
        RedisLockRegistry lockRegistry=new RedisLockRegistry(connectionFactory,
                RedisKeyConfig.LOCK_DEFAULT_REGISTRY_KEY);
        return lockRegistry;
    }

}