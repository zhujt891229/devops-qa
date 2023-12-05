package com.zjt.qas.utils.redis;

public class RedisKeyConfig {
    public static final String SEP=":";


    // 功能模块 : [用途 ...] : key

    public static final String TEST_STRING_KEY="test:string:key";
    public static final String TEST_LIST_KEY="test:list:key";
    public static final String TEST_SET_KEY="test:set:key";
    public static final String TEST_SORTEDSET_KEY="test:sortedset:key";
    public static final String TEST_HASH_KEY="test:hash:key";

    public static final String TEST_OBJECT_KEY="test:object:key";

    // -----------------
    //分布式锁key的前缀
    public static final String LOCK_DEFAULT_REGISTRY_KEY ="lock:default:registry";

}
