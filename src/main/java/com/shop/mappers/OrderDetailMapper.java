package com.shop.mappers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.DTO.OrderDetailDTO;
import com.shop.entity.OrderDetail;


@Service
public class OrderDetailMapper {
	@Autowired
	private ModelMapper modelMapper;
	
	public OrderDetail convertToEntity(OrderDetailDTO OrderDetailDTO) {
		OrderDetail entity = modelMapper.map(OrderDetailDTO, OrderDetail.class);
		return entity;
	}
	
	public OrderDetailDTO convertToDTO(OrderDetail OrderDetail) {
		OrderDetailDTO dto = modelMapper.map(OrderDetail, OrderDetailDTO.class);
		return dto;
	}
	
	public List<OrderDetail> convertToListEntity(List<OrderDetailDTO> list){
		List<OrderDetail> listEntity = new ArrayList<OrderDetail>();
		for (int i = 0; i < list.size(); i++) {
			OrderDetailDTO OrderDetailDTO = list.get(i);
			OrderDetail entity = convertToEntity(OrderDetailDTO);
			listEntity.add(entity);
		}
		return listEntity;
	}
	
	public List<OrderDetailDTO> convertToListDTO(List<OrderDetail> list){
		List<OrderDetailDTO> listDTO = new ArrayList<OrderDetailDTO>();
		for (int i = 0; i < list.size(); i++) {
			OrderDetail entity = list.get(i);
			OrderDetailDTO dto = convertToDTO(entity);
			listDTO.add(dto);
		}
		return listDTO;
	}
}