package com.zjt.qas.config;

//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//import redis.clients.jedis.JedisPoolConfig;
//
//@Configuration
//public class RedisConfig {
//
//    @Value("${spring.redis.host}")
//    private String redisHostName;
//
//    @Value("${spring.redis.port}")
//    private int redisPort;
//
//    @Value("${spring.redis.password}")
//    private String redisPassword;
//
//    @Value("${spring.redis.jedis.pool.max-active}")
//    private int maxActive;
//
//    @Value("${spring.redis.jedis.pool.max-idle}")
//    private int maxIdle;
//
//    @Value("${spring.redis.jedis.pool.max-wait}")
//    private String maxWait;
//
//    @Value("${spring.redis.jedis.pool.min-idle}")
//    private int minIdle;
//
//    @Bean
//    public StringRedisTemplate stringRedisTemplate(){
//        StringRedisTemplate stringRedisTemplate=new StringRedisTemplate();
//        stringRedisTemplate.setConnectionFactory(jedisConnectionFactory());
//        return stringRedisTemplate;
//    }
//
//    @Bean
//    public RedisTemplate<String,Object> redisTemplate(){
//        RedisTemplate redisTemplate=new RedisTemplate();
//        redisTemplate.setConnectionFactory(jedisConnectionFactory());
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer=new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper objectMapper=new ObjectMapper();
//        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
//        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
//
//        RedisSerializer redisSerializer=new StringRedisSerializer();
//        redisTemplate.setKeySerializer(redisSerializer);
//        redisTemplate.setHashKeySerializer(redisSerializer);
//        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
//        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
//        redisTemplate.afterPropertiesSet();
//        return redisTemplate;
//    }
//
//    @Bean
//    RedisConnectionFactory jedisConnectionFactory(){
//        RedisStandaloneConfiguration redisStandaloneConfiguration=new RedisStandaloneConfiguration();
//        redisStandaloneConfiguration.setHostName(redisHostName);
//        redisStandaloneConfiguration.setPassword(redisPassword);
//        redisStandaloneConfiguration.setPort(redisPort);
//        redisStandaloneConfiguration.setDatabase(1);
//        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpcf=
//                (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
//        jpcf.poolConfig(jedisPoolConfig());
//        JedisClientConfiguration jedisClientConfiguration=jpcf.build();
//        return new JedisConnectionFactory(redisStandaloneConfiguration,jedisClientConfiguration);
//    }
//
//    @Bean
//    JedisPoolConfig jedisPoolConfig(){
//        JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
//        jedisPoolConfig.setMaxTotal(maxActive);
//        jedisPoolConfig.setMaxIdle(maxIdle);
//        jedisPoolConfig.setMaxWaitMillis(-1L);
//        String maxwait=maxWait.replace("ms","");
//        //jedisPoolConfig.setMaxWaitMillis(3000);
//        jedisPoolConfig.setTestOnBorrow(true);
//        jedisPoolConfig.setTestWhileIdle(true);
//        jedisPoolConfig.setMinIdle(minIdle);
//        return jedisPoolConfig;
//    }
//}

//-----------------------------------------------------------------------
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializationContext;
//import org.springframework.data.redis.serializer.RedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//import redis.clients.jedis.JedisPoolConfig;
//
//import java.time.Duration;
//
///**
// * @version v1.0
// * @description: redis配置
// * @author: 47 on 2020/4/3 13:45
// */
//@Configuration
//@EnableCaching
//public class RedisConfig {
//    @Value("${spring.redis.host}")
//    private String redisHostName;
//
//    @Value("${spring.redis.port}")
//    private int redisPort;
//
//    @Value("${spring.redis.password}")
//    private String redisPassword;
//
//    @Value("${spring.redis.jedis.pool.max-active}")
//    private int maxActive;
//
//    @Value("${spring.redis.jedis.pool.max-idle}")
//    private int maxIdle;
//
//    @Value("${spring.redis.jedis.pool.max-wait}")
//    private String maxWait;
//
//    @Value("${spring.redis.jedis.pool.min-idle}")
//    private int minIdle;
//    /**
//     * 配置StringRedisTemplate
//     * @param factory 连接
//     * @return redisTemplate
//     */
//    @Bean
//    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory){
//        StringRedisTemplate stringRedisTemplate=new StringRedisTemplate();
//        stringRedisTemplate.setConnectionFactory(factory);
//        return stringRedisTemplate;
//    }
//
//    /**
//     * 配置RedisTemplate
//     * @param factory 连接
//     * @return redisTemplate
//     */
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory){
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(factory);
//        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
//        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
////        ObjectMapper om = new ObjectMapper();
////        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
////        //反序列化时候遇到不匹配的属性并不抛出异常
////        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
////        //序列化时候遇到空对象不抛出异常
////        om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
////        //反序列化的时候如果是无效子类型,不抛出异常
////        om.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
////        //不使用默认的dateTime进行序列化,
////        om.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
////        //使用JSR310提供的序列化类,里面包含了大量的JDK8时间序列化类
////        om.registerModule(new JavaTimeModule());
////        //启用反序列化所需的类型信息,在属性中添加@class
////        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
////        jackson2JsonRedisSerializer.setObjectMapper(om);
//        RedisSerializer<?> stringSerializer = new StringRedisSerializer();
//        //key序列化
//        redisTemplate.setKeySerializer(stringSerializer);
//        //value序列化
//        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
//        //Hash key序列化
//        redisTemplate.setHashKeySerializer(stringSerializer);
//        // Hashvalue序列化
//        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
//        redisTemplate.afterPropertiesSet();
//        return redisTemplate;
//    }
//
//    /**
//     * 使用spring的CacheManager
//     * @param factory 连接
//     * @return cacheManager
//     */
//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory factory) {
//        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
//        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
//
//        //解决查询缓存转换异常的问题
////        ObjectMapper om = new ObjectMapper();
////        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
////        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,ObjectMapper.DefaultTyping.NON_FINAL);
////        jackson2JsonRedisSerializer.setObjectMapper(om);
//
//        // 配置序列化（解决乱码的问题）
//        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(Duration.ofMinutes(10))
//                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
//                .disableCachingNullValues();
//
//        return RedisCacheManager.builder(factory)
//                .cacheDefaults(config)
//                .build();
//    }
//
//
//    @Bean
//    RedisConnectionFactory jedisConnectionFactory(){
//        RedisStandaloneConfiguration redisStandaloneConfiguration=new RedisStandaloneConfiguration();
//        redisStandaloneConfiguration.setHostName(redisHostName);
//        redisStandaloneConfiguration.setPassword(redisPassword);
//        redisStandaloneConfiguration.setPort(redisPort);
//        redisStandaloneConfiguration.setDatabase(1);
//        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpcf=
//                (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
//        jpcf.poolConfig(jedisPoolConfig());
//        JedisClientConfiguration jedisClientConfiguration=jpcf.build();
//        return new JedisConnectionFactory(redisStandaloneConfiguration,jedisClientConfiguration);
//    }
//
//    @Bean
//    JedisPoolConfig jedisPoolConfig(){
//        JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
//        jedisPoolConfig.setMaxTotal(maxActive);
//        jedisPoolConfig.setMaxIdle(maxIdle);
//        jedisPoolConfig.setMaxWaitMillis(-1L);
//        String maxwait=maxWait.replace("ms","");
//        //jedisPoolConfig.setMaxWaitMillis(3000);
//        jedisPoolConfig.setTestOnBorrow(true);
//        jedisPoolConfig.setTestWhileIdle(true);
//        jedisPoolConfig.setMinIdle(minIdle);
//        return jedisPoolConfig;
//    }
//}

//-----------------------------------------------------------------------------
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonTypeInfo;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.MapperFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//@Configuration
//public class RedisConfig {
//    @Autowired
//    private RedisConnectionFactory redisConnectionFactory;
//
//    @Bean
//    @SuppressWarnings("all")
//    public RedisTemplate<String,Object> redisTemplate(){
//
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(redisConnectionFactory);
//
//        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = this.jackson2JsonRedisSerializer();
//
//        //String序列化
//        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//        redisTemplate.setDefaultSerializer(RedisSerializer.string());
//        //key采用string的序列化方式
//        redisTemplate.setKeySerializer(stringRedisSerializer);
//        //hash的key采用string的序列化方式
//        redisTemplate.setHashKeySerializer(stringRedisSerializer);
//        //value序列化也采用jackson
//        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
//        //hash的value也采用jackson
//        redisTemplate.setHashValueSerializer( jackson2JsonRedisSerializer);
//        redisTemplate.afterPropertiesSet();
//
//        return redisTemplate;
//    }
//
//    /**
//     * 自定义jackson2JsonRedisSerializer对象
//     * @return
//     */
//    private Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer() {
//        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer =
//                new Jackson2JsonRedisSerializer<>(Object.class);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
////        objectMapper.configure(MapperFeature.USE_ANNOTATIONS, false);
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//        // 此项必须配置，否则会报java.lang.ClassCastException: java.util.LinkedHashMap cannot be cast to XXX
//        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,ObjectMapper.DefaultTyping.NON_FINAL
//                , JsonTypeInfo.As.PROPERTY);
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
//        return jackson2JsonRedisSerializer;
//    }
//
//}


//-------------------------

import com.zjt.qas.utils.redis.GenericFastjson2JsonRedisSerializer;
import com.zjt.qas.utils.redis.RedisDbSelectFactory;
import com.zjt.qas.utils.redis.RedisManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;

/**
 *
 * @see RedisProperties
 * @see RedisAutoConfiguration
 *
 * @author TimFruit
 * @date 19-12-27
 */
@Configuration
public class RedisConfig {

    /**
     * 默认key是String, value是json
     *
     * @param redisConnectionFactory
     * @return
     */
    @Bean("defaultRedisTemplate")
    public RedisTemplate<String, Object> defaultRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        //设置序列化
        RedisSerializer stringSerializer = new StringRedisSerializer();
        //本人偏好使用fastjson序列化成json
        RedisSerializer jsonSerializer = new GenericFastjson2JsonRedisSerializer();
        //官方提供jackson序列化成json
        //RedisSerializer jsonSerializer=new GenericJackson2JsonRedisSerializer();

        template.setKeySerializer(stringSerializer);
        template.setValueSerializer(jsonSerializer);

        template.setHashKeySerializer(stringSerializer);
        template.setHashValueSerializer(jsonSerializer);

        return template;
    }

    /**
     * key是String, value是json
     *
     * @param defaultRedisTemplate
     * @return
     */
    @Bean("defaultRedisManager")
    public RedisManager<String, Object> defaultRedisManager(
            @Qualifier("defaultRedisTemplate") RedisTemplate<String, Object> defaultRedisTemplate) {
        return new RedisManager<>(defaultRedisTemplate);
    }

    /**
     * key是String, value是json
     * 使用3号库
     *
     * @param defaultRedisTemplate
     * @return
     */
    @Bean("default3RedisManager")
    public RedisManager<String, Object> default3RedisManager(
            @Qualifier("defaultRedisTemplate") RedisTemplate<String, Object> defaultRedisTemplate) {
        RedisTemplate<String, Object> default3RedisTemplate = RedisDbSelectFactory.selectDb(defaultRedisTemplate, 3);
        return new RedisManager<>(default3RedisTemplate);
    }

    /**
     * key, value 都是字符串
     *
     * @param stringRedisTemplate 由{@link RedisAutoConfiguration}实例化stringRedisTemplate
     * @return
     */
    @Bean("stringRedisManager")
    public RedisManager<String, String> stringRedisManager(
            @Qualifier("stringRedisTemplate") RedisTemplate<String, String> stringRedisTemplate) {
        return new RedisManager<>(stringRedisTemplate);
    }

    /**
     * key, value 都是字符串
     * 使用3号库
     *
     * @param stringRedisTemplate 由{@link RedisAutoConfiguration}实例化stringRedisTemplate
     * @return
     */
    @Bean("string3RedisManager")
    public RedisManager<String, String> string3RedisManager(
            @Qualifier("stringRedisTemplate") RedisTemplate<String, String> stringRedisTemplate) {
        RedisTemplate<String, String> string3RedisTemplate = RedisDbSelectFactory.selectDb(stringRedisTemplate, 3);
        return new RedisManager<>(string3RedisTemplate);
    }

    @Bean("stringRedisTemplate")
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
////      是否开启共享连接, 默认开启
//        ((LettuceConnectionFactory)redisConnectionFactory).setShareNativeConnection(false);
        return template;
    }
}