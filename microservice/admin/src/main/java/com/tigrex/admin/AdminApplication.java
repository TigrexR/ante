package com.tigrex.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * (๑乛◡乛๑)
 */
@EnableTransactionManagement
@SpringBootApplication
public class AdminApplication implements TransactionManagementConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}

	@Resource(name="master")
	private PlatformTransactionManager master;

	// 创建事务管理器master
	@Bean(name = "master")
	public PlatformTransactionManager txManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return master;
	}

}
