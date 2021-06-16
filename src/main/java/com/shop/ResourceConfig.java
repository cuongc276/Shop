package com.shop;



import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class ResourceConfig {
	
	@Bean("messageSource")
	public MessageSource getMessageSource() {
		ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
		ms.addBasenames("classpath:messages/product");
		ms.addBasenames("classpath:messages/user");
		ms.addBasenames("classpath:messages/category");
		ms.addBasenames("classpath:messages/order");
		ms.setDefaultEncoding("UTF-8");
		return ms;
	}
	
}
