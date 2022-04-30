package com.manage.project.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class DistributedLockUtil implements ApplicationContextAware {

    private static final String LOCK_UUID = UUID.randomUUID().toString();
    private static final String RELEASE_LOCK_SCRIPT = "if redis.call('get',KEYS[1])==ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";
    private static final String IF_NOT_EXIST="NX";
    private static final String EXPIRE_IN_MILLISECONDS="PX";
    private static final long DEFAULT_EXPIRE = 15;
    private static final long THREE_EXPIRE = 300;
    private static final TimeUnit DEFAULT_EXPIRE_UNIT = TimeUnit.SECONDS;
    private static StringRedisTemplate redisTemplate;

    public static boolean tryLock(String key){
        return tryLock(key,DEFAULT_EXPIRE,DEFAULT_EXPIRE_UNIT);
    }

    public static boolean threeLock(String key){
        return tryLock(key,THREE_EXPIRE,DEFAULT_EXPIRE_UNIT);
    }

    public static boolean tryLock(String key,long expireTime,TimeUnit unit){
        System.out.println(redisTemplate);
        RedisCallback<Boolean> redisCallback = redisConnection->{
          Jedis nativeConn = (Jedis)redisConnection.getNativeConnection();
          String set = nativeConn.set(key,getId());
          return set!=null&&set.equals("OK");
        };
        return redisTemplate.execute(redisCallback);
    }

    public static boolean lock(String key,long timeout,TimeUnit unit){
        return lock(key,timeout,unit,DEFAULT_EXPIRE,DEFAULT_EXPIRE_UNIT);
    }

    public static boolean lock(String key,long timeout,TimeUnit timeoutUnit,long expireTime,TimeUnit expireUnit){
        RedisCallback<Boolean> redisCallback = redisConnection->{
            Jedis nativeConn = (Jedis)redisConnection.getNativeConnection();
            long millisTimeout = timeoutUnit.toMillis(timeout);
            long deadline = System.currentTimeMillis()+millisTimeout;
            for(;;){
                if(deadline-System.currentTimeMillis()<0){
                    return false;
                }
                String set = nativeConn.set(key,getId());
                if(set!=null&&set.equals("OK")){
                    return true;
                }
                try{
                    TimeUnit.MILLISECONDS.sleep(50);
                }catch(InterruptedException e){
                    e.printStackTrace();
                    return false;
                }
            }
        };
        return redisTemplate.execute(redisCallback);
    }

    public static boolean release(String key){
        RedisCallback<Boolean> callback =(redisConnection->{
           Jedis nativeConn = (Jedis)redisConnection.getNativeConnection();
           Object result = nativeConn.eval(RELEASE_LOCK_SCRIPT, Arrays.asList(new String[] {key}),
                   Arrays.asList(new String[]{getId()}));
           return result.toString().equals("1");
        });
        return redisTemplate.execute(callback);
    }

    private static String getId(){
        return LOCK_UUID+"_"+Thread.currentThread().getId();
    }

    @Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        DistributedLockUtil.setAppContext(ac);
    }

    public static void setAppContext(ApplicationContext applicationContext) throws BeansException{
        redisTemplate=applicationContext.getBean("StringRedisTemplate",StringRedisTemplate.class);
    }
}
