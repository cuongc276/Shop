package com.shop.mappers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.DTO.ProductDTO;
import com.shop.entity.Product;

@Service
public class ProductMapper {
	@Autowired
	private ModelMapper modelMapper;
	
	public Product convertToEntity(ProductDTO ProductDTO) {
		Product entity = modelMapper.map(ProductDTO, Product.class);
		return entity;
	}
	
	public ProductDTO convertToDTO(Product Product) {
		ProductDTO dto = modelMapper.map(Product, ProductDTO.class);
		return dto;
	}
	
	public List<Product> convertToListEntity(List<ProductDTO> list){
		List<Product> listEntity = new ArrayList<Product>();
		for (int i = 0; i < list.size(); i++) {
			ProductDTO ProductDTO = list.get(i);
			Product entity = convertToEntity(ProductDTO);
			listEntity.add(entity);
		}
		return listEntity;
	}
	
	public List<ProductDTO> convertToListDTO(List<Product> list){
		List<ProductDTO> listDTO = new ArrayList<ProductDTO>();
		for (int i = 0; i < list.size(); i++) {
			Product entity = list.get(i);
			ProductDTO dto = convertToDTO(entity);
			listDTO.add(dto);
		}
		return listDTO;
	}
}
