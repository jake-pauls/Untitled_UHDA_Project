package com.csis3275.config_untitled;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author Jacob Pauls Student ID 300273666
 * @date Nov 3, 2020
 * AuthenticationServiceConfiguration_untitled.java
 * com.csis3275.config_untitled
 * CSIS 3275 Group Project
 * Group Name: Untitled
 */

@Configuration
@EnableWebSecurity
public class AuthenticationServiceConfiguration_untitled extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder authentication) throws Exception {
		// Point Spring Security to the h2 database
		authentication.jdbcAuthentication()
					  .dataSource(dataSource)
					  // Retrieve usernames and authorities from our preconfigured user table
					  .usersByUsernameQuery("SELECT username, password, 'true' as enabled FROM users WHERE username = ?")
					  .authoritiesByUsernameQuery("SELECT username, authority FROM authorities WHERE username = ?")
					  // Decrypt all passwords from the database using a BCrypt hashing algorithm
					  .passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			// Only users with the ADMIN role can access admin user management
			.antMatchers("/AdminUserManagement").hasRole("ADMIN")
			// All users can access the root directory, registration, forgot password, and resources
			.antMatchers("/", "/register", "/forgotpassword", "/resources/**").permitAll()
			// All other requests require authentication
			.anyRequest().authenticated()
			.and()
				.formLogin()
					.loginPage("/login")
					.defaultSuccessUrl("/UserHomePage")
					.permitAll()
			.and()
				// Redirect back to login form on logout success
				.logout()
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.logoutSuccessUrl("/login");
	}
	
}
