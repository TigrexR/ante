package com.tigrex.user.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.tigrex.api.constant.ContextConst;
import com.tigrex.user.utils.DynamicDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@MapperScan("com.tigrex.user.mapper")
public class DataConfig {

    /**
     * 用户数据源
     * @return
     */
//    @Bean(name = "userDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.user")
//    public DataSource userDataSource(){
//        return new DruidDataSource();
//    }

    /**
     * 后台数据源
     * @return
     */
//    @Bean(name = "adminDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.admin")
//    public DataSource adminDataSource(){
//        return new DruidDataSource();
//    }

    /**
     * 书本数据源
     * @return
     */
//    @Bean(name = "bookDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.book")
//    public DataSource bookDataSource(){
//        return new DruidDataSource();
//    }

    @Bean(name = "testDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.test")
    public DataSource testDataSource(){
        return new DruidDataSource();
    }

    @Bean(name = "phoneDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.phone")
    public DataSource phoneDataSource(){
        return new DruidDataSource();
    }

    /**
     * 初始化数据库链接池
     * 先以主数据库作为数据库链接
     * 如果数据库链接替换不关闭链接池，而是关闭之前的数据库链接，新建新的数据库链接
     * @return
     */
    @Primary
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        //配置默认数据源
        dynamicDataSource.setDefaultTargetDataSource(testDataSource());

        //配置多数据源
        Map<Object, Object> dataSourceMap = new HashMap();
        dataSourceMap.put(ContextConst.DataSourceType.TEST.name(), testDataSource());
        dataSourceMap.put(ContextConst.DataSourceType.PHONE.name(), phoneDataSource());
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        return dynamicDataSource;
    }

    /**
     * user数据库事物管理器
     * @return
     */
    @Bean(name = "transactionManagerUser")
    public PlatformTransactionManager transactionManagerUser () {
        return new DataSourceTransactionManager(userDataSource());
    }

    /**
     * book数据库事物管理器
     * @return
     */
    @Bean(name = "transactionManagerBook")
    public PlatformTransactionManager transactionManagerBook () {
        return new DataSourceTransactionManager(bookDataSource());
    }

    /**
     * admin数据库事物管理器
     * @return
     */
    @Bean(name = "transactionManagerAdmin")
    public PlatformTransactionManager transactionManagerAdmin () {
        return new DataSourceTransactionManager(adminDataSource());
    }

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * SQL执行效率插件
     */
//    @Profile({"dev","test"})// 设置 dev test 环境开启
//    @Bean
//    public PerformanceInterceptor performanceInterceptor() {
//        return new PerformanceInterceptor();
//    }

}
