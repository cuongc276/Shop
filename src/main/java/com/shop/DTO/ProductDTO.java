package com.shop.DTO;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.shop.entity.Category;

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
public class ProductDTO {
	
	
	private Integer id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String image;
	
	@NotNull
	@Min(0)
	private Double price;
	
	@NotNull
	private String create_date;
	
	// Mapper co chuyen doi duoc id thanh doi tuong category
	
	@NotNull
	private Category category;
	
	@NotNull
	private Integer available;
	
}
