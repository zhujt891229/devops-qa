package com.zjt.qas.utils.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class RedisManager<K,V> {

    private RedisTemplate<K,V> redisTemplate;

    public RedisManager(RedisTemplate<K, V> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public RedisTemplate<K, V> getRedisTemplate() {
        return redisTemplate;
    }

    // --------------------- key

    public void expireKey(K key, long timeout, TimeUnit unit){
        redisTemplate.expire(key, timeout, unit);
    }

    public void deleteKey(K key){
        redisTemplate.delete(key);
    }

    public void deleteKeyBatch(Collection<K> keys){
        redisTemplate.delete(keys);
    }

    //---------------------- string
    public void set(K key, V value){
        redisTemplate.opsForValue().set(key, value);
    }

    public void set(K key, V value, long timeout, TimeUnit unit){
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    public void setBatch(Map<K, V> kvMap){
        redisTemplate.opsForValue().multiSet(kvMap);
    }

    public V get(K key){
        return redisTemplate.opsForValue().get(key);
    }

    public Map<K, V> getBatch(Collection<K> keys){

        List<V> values=redisTemplate.opsForValue().multiGet(keys);
        if(CollectionUtils.isEmpty(values)){
            return Collections.emptyMap();
        }

        // wrapper map
        Map<K,V> resultMap=new HashMap<>(keys.size());
        K key=null;
        V value=null;
        Iterator<K> keyIter=keys.iterator();
        Iterator<V> valueIter=values.iterator();
        while(keyIter.hasNext()){
            key=keyIter.next();
            value=valueIter.next();

            resultMap.put(key, value);
        }
        return resultMap;
    }

    //---------------------- list 左头右尾

    public void lLeftPush(K key, V value){
        redisTemplate.opsForList().leftPush(key, value);
    }

    public void lLeftPush(K key, V pivot, V value){
        redisTemplate.opsForList().leftPush(key, pivot, value);
    }

    public void lLeftPushBatch(K key, Collection<V> values){
        redisTemplate.opsForList().leftPushAll(key, values);
    }

    public void lRightPush(K key, V value){
        redisTemplate.opsForList().rightPush(key, value);
    }

    public void lRightPush(K key, V pivot, V value){
        redisTemplate.opsForList().rightPush(key, pivot, value);
    }

    public void lRightPushBatch(K key, Collection<V> values){
        redisTemplate.opsForList().rightPushAll(key, values);
    }

    public V lLeftPop(K key){
        return redisTemplate.opsForList().leftPop(key);
    }

    public V lRightPop(K key){
        return redisTemplate.opsForList().rightPop(key);
    }

    /**
     * 批量查询(不删除) 下标从左到右递增
     * @param key
     * @param start 0 表示第一个
     * @param stop -1 表示最后一个
     * @return
     */
    public List<V> lRange(K key, long start, long stop){
        return redisTemplate.opsForList().range(key, start, stop);
    }

    /**
     * 保留[start, stop]下标范围的队列,裁剪删除其他  (可以用作批量删除)
     * @param key
     * @param start
     * @param stop
     */
    public void lTrim(K key, long start, long stop){
        redisTemplate.opsForList().trim(key, start, stop);
    }

    public Long lSize(K key ){
        return redisTemplate.opsForList().size(key);
    }

    // ------------------------------- set

    public void sAdd(K key, V value){
        redisTemplate.opsForSet().add(key, value);
    }

    public void sAddBatch(K key, Collection<V> values){
        if(CollectionUtils.isEmpty(values)){
            return;
        }
        V[] valueArray=(V[])values.toArray();
        redisTemplate.opsForSet().add(key, valueArray);
    }

    public Set<V> sMembers(K key){
        return redisTemplate.opsForSet().members(key);
    }

    public V sRandomMember(K key){
        return redisTemplate.opsForSet().randomMember(key);
    }

    public Long sRemove(K key, V value){
        return redisTemplate.opsForSet().remove(key, value);
    }

    public Long sRemoveBatch(K key, Collection<V> values){
        if(CollectionUtils.isEmpty(values)){
            return 0L;
        }
        V[] valueArray=(V[])values.toArray();
        return redisTemplate.opsForSet().remove(key, valueArray);
    }

    public Boolean sIsMember(K key, V value){
        return redisTemplate.opsForSet().isMember(key, value);
    }

    public Long sSize(K key){
        return redisTemplate.opsForSet().size(key);
    }

    // ------------------------------- sorted set

    /**
     * 添加
     * @param key
     * @param value
     * @param score 分数
     * @return
     */
    public Boolean zAdd(K key,V value, double score){
        return redisTemplate.opsForZSet().add(key, value, score);
    }

    /**
     * 批量添加
     * @param key
     * @param tuples
     * @return
     */
    public Long zAddBatch(K key,Set<ZSetOperations.TypedTuple<V>> tuples){
        return redisTemplate.opsForZSet().add(key, tuples);
    }

    /**
     * 根据分数排序(从低到高)后的下标范围查询
     * @param key
     * @param start 0 表示第一个
     * @param stop -1 表示最后一个
     * @return
     */
    public Set<V> zRange(K key, long start, long stop){
        return redisTemplate.opsForZSet().range(key, start, stop);
    }

    /**
     * 根据分数排序(从高到低)后的下标范围查询
     * @param key
     * @param start 0 表示第一个
     * @param stop -1 表示最后一个
     * @return
     */
    public Set<V> zReverseRange(K key, long start, long stop){
        return redisTemplate.opsForZSet().reverseRange(key, start, stop);
    }

    /**
     * 根据分数排序(从低到高)后的下标范围查询
     * @param key
     * @param start 0 表示第一个
     * @param stop -1 表示最后一个
     * @return 结果带有分数
     */
    public Set<ZSetOperations.TypedTuple<V>> zRangeWithScores(K key, long start, long stop){
        return redisTemplate.opsForZSet().rangeWithScores(key, start, stop);
    }

    /**
     * 根据分数排序(从高到低)后的下标范围查询
     * @param key
     * @param start 0 表示第一个
     * @param stop -1 表示最后一个
     * @return 结果带有分数
     */
    public Set<ZSetOperations.TypedTuple<V>> zReverseRangeWithScores(K key, long start, long stop){
        return redisTemplate.opsForZSet().reverseRangeWithScores(key, start, stop);
    }

    /**
     * (排序集合按照分数从低到高排序)
     * 根据分数范围[min, max]查询对应的结果
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Set<V> zRangeByScores(K key, double min, double max){
        return redisTemplate.opsForZSet().rangeByScore(key, min, max);
    }

    /**
     * (排序集合按照分数从高到低排序)
     * 根据分数范围[min, max]查询对应的结果
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Set<V> zReverseRangeByScores(K key, double min, double max){
        return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max);
    }

    /**
     * (排序集合按照分数从低到高排序)
     * 根据分数范围[min, max]查询对应的结果
     * @param key
     * @param min
     * @param max
     * @return 结果带有分数
     */
    public Set<ZSetOperations.TypedTuple<V>> zRangeByScoresWithScores(K key, double min, double max){
        return redisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max);
    }

    /**
     * (排序集合按照分数从低到高排序)
     * 根据分数范围[min, max]查询对应的结果
     * @param key
     * @param min
     * @param max
     * @param offset 分页查询 偏移下标
     * @param count 分页查询 一页大小
     * @return 结果带有分数
     */
    public Set<ZSetOperations.TypedTuple<V>> zRangeByScoresWithScores(K key, double min, double max, int offset, int count){
        return redisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max, offset, count);
    }

    /**
     * (排序集合按照分数从高到低排序)
     * 根据分数范围[min, max]查询对应的结果
     * @param key
     * @param min
     * @param max
     * @return 结果带有分数
     */
    public Set<ZSetOperations.TypedTuple<V>> zReverseRangeByScoresWithScores(K key, double min, double max){
        return redisTemplate.opsForZSet().reverseRangeByScoreWithScores(key, min, max);
    }

    /**
     * (排序集合按照分数从高到低排序)
     * 根据分数范围[min, max]查询对应的结果
     * @param key
     * @param min
     * @param max
     * @param offset 分页查询 偏移下标
     * @param count 分页查询 一页大小
     * @return 结果带有分数
     */
    public Set<ZSetOperations.TypedTuple<V>> zReverseRangeByScoresWithScores(K key, double min, double max, int offset, int count){
        return redisTemplate.opsForZSet().reverseRangeByScoreWithScores(key, min, max, offset, count);
    }

    /**
     * (排序集合按照分数从低到高排序)
     * 查询value的下标(排名)
     * @param key
     * @param value
     * @return 如果不存在,则返回null
     */
    public Long zRank(K key, V value){
        return redisTemplate.opsForZSet().rank(key, value);
    }

    /**
     * (排序集合按照分数从高到低排序)
     * 查询value的下标(排名)
     * @param key
     * @param value
     * @return 如果不存在,则返回null
     */
    public Long zReverseRank(K key, V value){
        return redisTemplate.opsForZSet().reverseRank(key, value);
    }

    /**
     * 查询value的分数
     * @param key
     * @param value
     * @return
     */
    public Double zScore(K key, V value){
        return redisTemplate.opsForZSet().score(key, value);
    }

    /**
     * 排序集合的个数
     * @param key
     * @return
     */
    public Long zSize(K key){
        return redisTemplate.opsForZSet().size(key);
    }

    /**
     * 统计指定分数范围[min, max]的个数
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Long zCount(K key, double min, double max){
        return redisTemplate.opsForZSet().count(key, min, max);
    }

    /**
     * 删除指定值
     * @param key
     * @param value
     */
    public Long zRemove(K key, V value){
        return redisTemplate.opsForZSet().remove(key, value);
    }

    /**
     * 批量删除指定值
     * @param key
     * @param values
     * @return
     */
    public Long zRemoveBatch(K key, Collection<V> values){
        if(CollectionUtils.isEmpty(values)){
            return 0L;
        }
        V[] valueArray=(V[])values.toArray();
        return redisTemplate.opsForZSet().remove(key, valueArray);
    }

    /**
     * (排序集合按照分数从低到高排序)
     * 根据下标范围[start, stop]删除
     * @param key
     * @param start
     * @param stop
     * @return
     */
    public Long zRemoveRange(K key, long start, long stop){
        return redisTemplate.opsForZSet().removeRange(key, start, stop);
    }

    /**
     * 根据分数范围[min, max]删除
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Long zRemoveRangeByScore(K key, double min, double max){
        return redisTemplate.opsForZSet().removeRangeByScore(key, min, max);
    }


    /**
     * 增加指定值的分数
     * @param key
     * @param value
     * @param delta
     * @return
     */
    public Double zIncrementScore(K key, V value, double delta){
        return redisTemplate.opsForZSet().incrementScore(key, value, delta);
    }

    // ------------------------------- hash

    /**
     * 在key对应的hash结构中, 添加hashKey, value
     * @param key
     * @param hashKey
     * @param value
     */
    public void hPut(K key, Object hashKey, Object value){
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    public void hPutAll(K key, Map hashMap){
        redisTemplate.opsForHash().putAll(key, hashMap);
    }

    /**
     * 在key对应的hash结构中, 查询hashKey对应的值
     * @param key
     * @param hashKey
     * @return
     */
    public Object hGet(K key, Object hashKey){
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * 在key对应的hash结构中, 批量查询hashKeys对应的值
     * @param key
     * @param hashKeys
     * @return
     */
    public Map hGetBatch(K key, Collection hashKeys){
        if(CollectionUtils.isEmpty(hashKeys)){
            return Collections.EMPTY_MAP;
        }

        List values=redisTemplate.opsForHash().multiGet(key, hashKeys);

        // wrap map
        Map resultMap=new HashMap(hashKeys.size());
        Object hashKey=null;
        Object value=null;

        Iterator hashKeyIter=hashKeys.iterator();
        Iterator valueIter=values.iterator();
        while(hashKeyIter.hasNext()){
            hashKey=hashKeyIter.next();
            value=valueIter.next();
            resultMap.put(hashKey, value);
        }
        return resultMap;
    }

    /**
     * 在key对应的hash结构中, 删除hashKey
     * @param key
     * @param hashKey
     * @return
     */
    public Long hDelete(K key, Object hashKey){
        return redisTemplate.opsForHash().delete(key, hashKey);
    }

    /**
     * 在key对应的hash结构中, 批量删除hashKeys
     * @param key
     * @param hashKeys
     * @return
     */
    public Long hDeleteBatch(K key, Collection hashKeys){
        if(CollectionUtils.isEmpty(hashKeys)){
            return 0L;
        }
        Object[] hashKeyArray=hashKeys.toArray();
        return redisTemplate.opsForHash().delete(key, hashKeyArray);
    }

    /**
     * 获取key对应hash结构中的所有hashKeys
     * @param key
     * @return
     */
    public Set hKeys(K key){
        return redisTemplate.opsForHash().keys(key);
    }

    /**
     * 获取key对应hash结构中的所有value
     * @param key
     * @return
     */
    public List hValues(K key){
        return redisTemplate.opsForHash().values(key);
    }

    /**
     * 判断hashKey是否在key对应的hash结构中
     * @param key
     * @param hashKey
     * @return
     */
    public Boolean hHasKey(K key, Object hashKey){
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    /**
     * 获取key对应的hash结构中数据个数
     * @param key
     * @return
     */
    public Long hSize(K key){
        return redisTemplate.opsForHash().size(key);
    }

}
