package com.shop.utils;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ListToArray {

	public String[] changeString(List<String> list) {
		String[] ds = new String[list.size()];
		for(int i=0; i<list.size(); i++) {
			ds[i] = list.get(i);
		}
		return ds;
	}
	
	public Integer[] changeInteger(List<Integer> list) {
		Integer[] ds = new Integer[list.size()];
		for(int i=0; i<list.size(); i++) {
			ds[i] = list.get(i);
		}
		return ds;
	}
}
