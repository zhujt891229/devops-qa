package com.zjt.qas.utils.redis;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class Fastjson2JsonRedisSerializer<T> implements RedisSerializer<T> {

    private Class<T> targetType;
    private GenericFastjson2JsonRedisSerializer genericRedisSerializer=new GenericFastjson2JsonRedisSerializer();

    public Fastjson2JsonRedisSerializer(Class<T> targetType) {
        this.targetType=targetType;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        return genericRedisSerializer.serialize(t);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        Object obj=genericRedisSerializer.deserialize(bytes);
        return genericRedisSerializer.deserialize(obj, targetType);
    }

    @Override
    public Class<?> getTargetType() {
        return targetType;
    }

}
