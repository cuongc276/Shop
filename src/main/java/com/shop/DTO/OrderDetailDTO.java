package com.shop.DTO;

import com.shop.entity.Order;
import com.shop.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetailDTO {
	
	private Integer id;
	
	private Integer quantity;
	
	private Double price;
	
	private Order order;

	private Product product;
	
}
