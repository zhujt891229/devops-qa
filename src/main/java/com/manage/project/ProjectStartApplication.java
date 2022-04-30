package com.manage.project;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
//import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication//(exclude={DataSourceAutoConfiguration.class})
@EnableAsync
@EnableTransactionManagement
@Slf4j
//@ComponentScan("com.manage.project.mapper.*")
@EnableAspectJAutoProxy(proxyTargetClass = true)
//@EnableKafka
public class ProjectStartApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectStartApplication.class);
    public static void main(String[] args){
        SpringApplication.run(ProjectStartApplication.class,args);
        LOGGER.info("系统启动成功");
    }
}
