package com.shop.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.shop.entity.Product;
import com.shop.repositories.OrderDetailRepository;

@Controller
public class StatisticsController {

	@Autowired
	private OrderDetailRepository orderDetailtRepo;
	
	@GetMapping("/admin/statistics")
	public String showChart() {
		List<Object[]> list = orderDetailtRepo.getTop5();
		for(int i=0; i < list.size(); i++) {
			Product product = (Product) list.get(i)[0];
			System.out.println(list.get(i)[1]);
			System.out.println(product.getName());
		}
		
		return "admin/statistics/chart";
	}
}
