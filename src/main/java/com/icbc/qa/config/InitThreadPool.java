package com.icbc.qa.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class InitThreadPool {
    private static final int CORE_POOL_SIZE = 10;
    private static final int MAX_POOL_SIZE = 20;
    private static final int QUEUE_CAPACITY = 99999;
    private static final int KEEP_ALIVE = 60;

    @Value("${threadName}")
    private String threadName;

    @Bean
    public Executor myExecutor(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        threadPoolTaskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        threadPoolTaskExecutor.setQueueCapacity(QUEUE_CAPACITY);
        threadPoolTaskExecutor.setKeepAliveSeconds(KEEP_ALIVE);
        threadPoolTaskExecutor.setThreadFactory(new ThreadExecutorFactory(threadName));
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
class ThreadExecutorFactory implements ThreadFactory{
    private final String namePrefix;
    private final AtomicInteger threadId=new AtomicInteger(1);
    public ThreadExecutorFactory(String featureGroup){
        namePrefix=featureGroup+" 编号：";
    }
    @Override
    public Thread newThread(Runnable task){
        String threadName=namePrefix+threadId;
        return new Thread(null,task,threadName,0);
    }
}
