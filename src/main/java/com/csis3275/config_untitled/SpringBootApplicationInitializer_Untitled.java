package com.csis3275.config_untitled;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * @author Jacob Pauls Student ID 300273666
 * @date Oct 20, 2020
 * SpringBootApplicationInitializer_Untitled.java
 * com.csis3275.config_untitled
 * CSIS 3275 Group Project
 * Group Name: Untitled
 */

@SpringBootApplication
@ComponentScan(basePackages = "com.csis3275")
@PropertySource("classpath:database.properties")
public class SpringBootApplicationInitializer_Untitled extends SpringBootServletInitializer {
	@Autowired
	Environment environment;
	private final String URL = "url";
	private final String USER = "dbuser";
	private final String DRIVER = "driver";
	private final String PASSWORD = "dbpassword";
	
	@Bean
	DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUrl(environment.getProperty(URL));
		driverManagerDataSource.setUsername(environment.getProperty(USER));
		driverManagerDataSource.setPassword(environment.getProperty(PASSWORD));
		driverManagerDataSource.setDriverClassName(environment.getProperty(DRIVER));
		return driverManagerDataSource;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootApplicationInitializer_Untitled.class, args);
	}
}
