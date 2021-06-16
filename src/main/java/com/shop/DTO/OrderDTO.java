package com.shop.DTO;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.shop.entity.OrderDetail;
import com.shop.entity.User;

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
public class OrderDTO {
	
	private Integer id;
	
	private String create_date;

	@NotBlank
	private String address;
	
	private User user;
	
	private List<OrderDetail> order_details;
}
