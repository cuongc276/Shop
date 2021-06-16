package com.shop.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.entity.Product;
import com.shop.repositories.ProductRepository;

@Controller
@RequestMapping("/home")
public class UserHomeController {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ProductRepository productRepo;
	
	@GetMapping()
	public String login(
		Model model
	) {
		
		String sortBy = request.getParameter("sort_by");
        String sortDirection = request.getParameter("sort_direction");
        String pageParam = request.getParameter("page");
        String limitParam = request.getParameter("limit");
        String sortField = sortBy == null ? "id" : sortBy;
        String productName = request.getParameter("productName");
        Sort sort = ( sortDirection == null || sortDirection.equals("asc") ) ?
            Sort.by(Direction.ASC, sortField):
            Sort.by(Direction.DESC, sortField);
        int page = pageParam == null ? 0 : Integer.parseInt(pageParam);
        int limit = limitParam == null ? 2 : Integer.parseInt(limitParam);
        Pageable pageable = PageRequest.of(page, limit, sort);
        
        Page<Product> pageProducts = productRepo.selectByActivatePagination(1, pageable);
        if(productName != null) {
        	pageProducts = productRepo.selectByName(productName, 1, pageable);
        }

		model.addAttribute("pageProducts", pageProducts);
		model.addAttribute("sort_by", sortBy);
		model.addAttribute("sort_direction", sortDirection);
		model.addAttribute("limit", limit);
		model.addAttribute("productName", productName);
		return "user/home";
	}
	
	
	
}
