package com.icbc.qa;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableAsync
@EnableTransactionManagement
@Slf4j
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableKafka
public class DevopsQAApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(DevopsQAApplication.class);
    public static void main(String[] args){
        SpringApplication.run(DevopsQAApplication.class,args);
        LOGGER.info("问答系统启动成功");
    }
}
