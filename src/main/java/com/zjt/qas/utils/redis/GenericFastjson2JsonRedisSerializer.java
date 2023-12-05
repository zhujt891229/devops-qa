package com.zjt.qas.utils.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class GenericFastjson2JsonRedisSerializer implements RedisSerializer {

    @Override
    public byte[] serialize(Object t) throws SerializationException {
        return JSON.toJSONString(t).getBytes();
    }

    /**
     * 直接使用fastjson反序列化, 结果可能是JSONObject, JSONArray
     *
     * @see #deserialize(Object, Class)
     * @param bytes
     * @return 可能为null
     * @throws SerializationException
     */
    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if(isEmpty(bytes)){
            return null;
        }
        return JSON.parse(new String(bytes));
    }

    /**
     * 用于辅助将{@link #deserialize(byte[])}转换成对应对象
     * @param deserializeObj
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T deserialize(Object deserializeObj, Class<T> clazz){
        if(deserializeObj==null){
            return null;
        }
        if(deserializeObj instanceof JSONObject){
            return ((JSONObject)deserializeObj).toJavaObject(clazz);
        }
        throw new SerializationException("不能识别类型: "+deserializeObj.getClass());
    }

    protected boolean isEmpty(byte[] bytes){
        if(bytes==null || bytes.length==0){
            return true;
        }
        return false;
    }
}