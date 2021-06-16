package com.shop.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shop.entity.Product;
import com.shop.repositories.OrderDetailRepository;
import com.shop.utils.ListToArray;

@Controller
public class StatisticsController {

	@Autowired
	private OrderDetailRepository orderDetailtRepo;
	@Autowired
	private ListToArray listToArray;
	
	@GetMapping("/admin/statistics")
	public String showChart(
		Model model
	) {
		List<Integer> quantity = new ArrayList<Integer>();
		List<Object[]> list = orderDetailtRepo.getTop5();
		String name = "[";
		for(int i=0; i < list.size(); i++) {
			Product product = (Product) list.get(i)[0];
			name = name +"'"+product.getName()+"',";
			Long sl = (Long) list.get(i)[1];
			Integer s = (int) (long) sl;
			quantity.add(s);
		}
		name.substring(name.length()-2, name.length()-1);
		name = name+"]";
		Integer[] quantities = listToArray.changeInteger(quantity);
		
		model.addAttribute("listProduct", name);
		model.addAttribute("listQuantity", Arrays.toString(quantities));
		
		return "admin/statistics/chart";
	}
}
