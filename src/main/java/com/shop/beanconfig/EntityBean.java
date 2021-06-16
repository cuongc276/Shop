package com.shop.beanconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shop.entity.Order;

@Configuration
public class EntityBean {

	@Bean("newOrder")
	public Order getOrder() {
		return new Order();
	}
}
