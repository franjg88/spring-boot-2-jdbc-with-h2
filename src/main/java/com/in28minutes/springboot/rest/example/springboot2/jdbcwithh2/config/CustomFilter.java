package com.in28minutes.springboot.rest.example.springboot2.jdbcwithh2.config;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class CustomFilter implements Filter {


	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("Init::called");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		System.out.println("doFilter::called");
		
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		
		Principal userPrincipal = request.getUserPrincipal();
		System.out.println("userPrinciple::"+userPrincipal);
		filterChain.doFilter(servletRequest, servletResponse);
		
		
	}

	@Override
	public void destroy() {
		System.out.println("Destroy::called");
		
	}

}
