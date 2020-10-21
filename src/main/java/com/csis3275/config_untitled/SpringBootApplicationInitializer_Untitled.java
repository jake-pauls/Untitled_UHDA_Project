package com.csis3275.config_untitled;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Jacob Pauls
 * @date 10/20/20
 * SpringBootApplicationInitializer_Untitled.java
 */

@SpringBootApplication
@ComponentScan(basePackages = "com.csis3275")
public class SpringBootApplicationInitializer_Untitled extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootApplicationInitializer_Untitled.class, args);
	}
}
