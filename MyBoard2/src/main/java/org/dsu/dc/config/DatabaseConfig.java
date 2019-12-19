package org.dsu.dc.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@MapperScan(basePackages = {"org.dsu.dc.mapper"})
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
public class DatabaseConfig {
	@Autowired
	private Environment env;
	
	@Bean
	@Primary
	public HikariDataSource dataSource() {
		
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(env.getProperty("driver-class-name"));
		hikariConfig.setJdbcUrl(env.getProperty("url"));
		hikariConfig.setUsername(env.getProperty("username"));
		hikariConfig.setPassword(env.getProperty("password"));
		HikariDataSource dataSource =  new HikariDataSource(hikariConfig);
		
		return dataSource;
	}
	
	@Bean
	public SqlSessionFactory sessionFactory(ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource());
		sqlSessionFactory.setMapperLocations(applicationContext.getResources("classpath:mappers/*.xml"));
		return sqlSessionFactory.getObject();
	}
}
