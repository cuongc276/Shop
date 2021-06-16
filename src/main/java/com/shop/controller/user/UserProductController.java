package com.shop.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.DTO.OrderDetailDTO;
import com.shop.entity.Product;
import com.shop.service.UserProductSevice;

@Controller
@RequestMapping("/user")
public class UserProductController {
	@Autowired
	private UserProductSevice userProductSevice;
	
	@Autowired
	private HttpServletRequest request;
	
	@GetMapping(value = "/order", params = "id")
	public String showproduct(
		@RequestParam("id") Product pro,
		Model model
	) {
		List<OrderDetailDTO> list = userProductSevice.addOrderDetailDTO(request, pro);
		model.addAttribute("listOrderDetails", list);
		return "redirect:/home";
	}
	
	@GetMapping(value="/cart/add", params="id")
	public String addProduct(
			@RequestParam("id") Product pro,
			Model model
	) {
		List<OrderDetailDTO> list = userProductSevice.addOrderDetailDTO(request, pro);
		model.addAttribute("listOrderDetails", list);
		return "redirect:/user/cart";
	}
	
	@GetMapping(value="/cart/remove", params="id")
	public String removeProduct(
			@RequestParam("id") Product pro,
			Model model
	) {
		List<OrderDetailDTO> list = userProductSevice.removeOrderDetailDTO(request, pro);
		model.addAttribute("listOrderDetails", list);
		return "redirect:/user/cart";
	}
	
}
