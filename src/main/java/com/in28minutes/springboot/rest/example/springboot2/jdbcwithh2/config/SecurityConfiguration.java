package com.in28minutes.springboot.rest.example.springboot2.jdbcwithh2.config;

import javax.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication()
		.withUser("franjg88").password("{noop}f07").roles("USER").and()
		.withUser("demo").password("adm").roles("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.authorizeRequests()
		.anyRequest()
		.fullyAuthenticated()
		.and()
//		.addFilterBefore(customFilter(), BasicAuthenticationFilter.class)
		.httpBasic();
		httpSecurity.csrf().disable();
	}

	@Bean
	public CustomFilter customFilter() {
		return new CustomFilter();
	}

}
