package com.zjt.qas.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.beans.ConstructorProperties;
import java.sql.SQLException;
import java.util.Objects;

@Configuration
@MapperScan(basePackages = "com.zjt.qas.mapper",sqlSessionTemplateRef = "sqlSessionTemplate")
public class DataSourceConfig {
    @Bean(name = "druidDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidDataSource druidDataSource(){
        DruidDataSource druidDataSource=new DruidDataSource();
        druidDataSource.setInitialSize(10);
        druidDataSource.setMinIdle(10);
        druidDataSource.setMaxActive(100);
        druidDataSource.setMaxWait(60000);
        try{
            druidDataSource.setFilters("stat,config");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return druidDataSource;
    }

    @Bean(name="sqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("druidDataSource") DruidDataSource druidDataSource) throws Exception{
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(druidDataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
//        bean.setTypeAliasesPackage("com.zjt.qas.mapper");
        Objects.requireNonNull(bean.getObject()).getConfiguration().setMapUnderscoreToCamelCase(true);
        return bean.getObject();
    }

    @Bean(name="transactionManager", value="transactionManager")
    @Primary
    public DataSourceTransactionManager testTransactionManager(@Qualifier("druidDataSource") DruidDataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name="sqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception{
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}


