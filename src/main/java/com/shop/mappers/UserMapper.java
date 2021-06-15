package com.shop.mappers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.DTO.UserDTO;
import com.shop.entity.User;

@Service
public class UserMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public User convertToEntity(UserDTO userDTO) {
		User entity = modelMapper.map(userDTO, User.class);
		return entity;
	}
	
	public UserDTO convertToDTO(User user) {
		UserDTO dto = modelMapper.map(user, UserDTO.class);
		return dto;
	}
	
	public List<User> convertToListEntity(List<UserDTO> list){
		List<User> listEntity = new ArrayList<User>();
		for (int i = 0; i < list.size(); i++) {
			UserDTO userDTO = list.get(i);
			User entity = convertToEntity(userDTO);
			listEntity.add(entity);
		}
		return listEntity;
	}
	
	public List<UserDTO> convertToListDTO(List<User> list){
		List<UserDTO> listDTO = new ArrayList<UserDTO>();
		for (int i = 0; i < list.size(); i++) {
			User entity = list.get(i);
			UserDTO dto = convertToDTO(entity);
			listDTO.add(dto);
		}
		return listDTO;
	}
}
