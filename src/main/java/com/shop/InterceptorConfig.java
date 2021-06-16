package com.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.shop.interceptors.AuthenticateInterceptor;
import com.shop.interceptors.RoleInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{
	
	@Autowired
	private AuthenticateInterceptor authenTnterceptor;
	
	@Autowired
	private RoleInterceptor roleInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(authenTnterceptor)
		.addPathPatterns("/admin/**", "/user/**")
		.excludePathPatterns("/login", "/register", "/home");

		registry.addInterceptor(roleInterceptor)
		.addPathPatterns("/admin/**")
		.excludePathPatterns("/user/**");

		
	}
}
