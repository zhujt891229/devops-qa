package com.manage.project.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String redisHostName;

    @Value("${spring.redis.port}")
    private int redisPort;

    @Value("${spring.redis.password}")
    private String redisPassword;

    @Value("${spring.redis.jedis.pool.max-active}")
    private int maxActive;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.jedis.pool.max-wait}")
    private String maxWait;

    @Value("${spring.redis.jedis.pool.min-idle}")
    private int minIdle;

    @Bean
    public StringRedisTemplate stringRedisTemplate(){
        StringRedisTemplate stringRedisTemplate=new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(jedisConnectionFactory());
        return stringRedisTemplate;
    }

    @Bean
    public RedisTemplate<String,Object> redisTemplate(){
        RedisTemplate redisTemplate=new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer=new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        RedisSerializer redisSerializer=new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    RedisConnectionFactory jedisConnectionFactory(){
        RedisStandaloneConfiguration redisStandaloneConfiguration=new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisHostName);
        redisStandaloneConfiguration.setPassword(redisPassword);
        redisStandaloneConfiguration.setPort(redisPort);
        redisStandaloneConfiguration.setDatabase(1);
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpcf=
                (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
        jpcf.poolConfig(jedisPoolConfig());
        JedisClientConfiguration jedisClientConfiguration=jpcf.build();
        return new JedisConnectionFactory(redisStandaloneConfiguration,jedisClientConfiguration);
    }

    @Bean
    JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxActive);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(-1L);
        String maxwait=maxWait.replace("ms","");
        //jedisPoolConfig.setMaxWaitMillis(3000);
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setTestWhileIdle(true);
        jedisPoolConfig.setMinIdle(minIdle);
        return jedisPoolConfig;
    }
}
