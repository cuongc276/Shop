package com.shop.mappers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.DTO.CategoryDTO;
import com.shop.entity.Category;

@Service
public class CategoryMapper {
	@Autowired
	private ModelMapper modelMapper;
	
	public Category convertToEntity(CategoryDTO CategoryDTO) {
		Category entity = modelMapper.map(CategoryDTO, Category.class);
		return entity;
	}
	
	public CategoryDTO convertToDTO(Category Category) {
		CategoryDTO dto = modelMapper.map(Category, CategoryDTO.class);
		return dto;
	}
	
	public List<Category> convertToListEntity(List<CategoryDTO> list){
		List<Category> listEntity = new ArrayList<Category>();
		for (int i = 0; i < list.size(); i++) {
			CategoryDTO CategoryDTO = list.get(i);
			Category entity = convertToEntity(CategoryDTO);
			listEntity.add(entity);
		}
		return listEntity;
	}
	
	public List<CategoryDTO> convertToListDTO(List<Category> list){
		List<CategoryDTO> listDTO = new ArrayList<CategoryDTO>();
		for (int i = 0; i < list.size(); i++) {
			Category entity = list.get(i);
			CategoryDTO dto = convertToDTO(entity);
			listDTO.add(dto);
		}
		return listDTO;
	}
}
