package com.shop.controller.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.DTO.ProductDTO;
import com.shop.entity.Category;
import com.shop.entity.Product;
import com.shop.mappers.ProductMapper;
import com.shop.repositories.CategoryRepository;
import com.shop.repositories.ProductRepository;
import com.shop.utils.DateFormat;

@Controller
@RequestMapping("/admin/product")
public class ProductController {
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private DateFormat dateformat;
	
	@ModelAttribute("listCategories")
	public List<Category> getCategories(){
		List<Category> list = categoryRepo.findAll();
		return list;
	}
	
	@ModelAttribute("getNow")
	public String getNow() {
		Date now = new Date();
		String dateStr = dateformat.toString(now, "yyyy-MM-dd");
		return dateStr;
	}
	
	@GetMapping
	public String listProducts(
		Model model
	) {
		List<Product> listProducts = productRepo.selectByActivate(1);
		List<ProductDTO> listDTO = productMapper.convertToListDTO(listProducts);
		model.addAttribute("listProducts", listDTO);
		return "admin/product/listproducts";
	}
	
	@GetMapping(value = "id")
	public String show(
			@PathVariable("id") Product product,
			Model model
	) {
		ProductDTO productDTO =  productMapper.convertToDTO(product);
		model.addAttribute("product", productDTO);
		return "admin/product/formcreate";
	}
	
	@GetMapping(value = "/create")
	public String create(
		@ModelAttribute("product") ProductDTO product
	) {
		return "admin/product/formcreate";
	}
	
	@PostMapping("/store")
	public String store(
		Model model,
		@Valid @ModelAttribute("product") ProductDTO product,
		BindingResult result
	) {
		System.out.println(result);
		if(result.hasErrors()) {
			model.addAttribute("errors", result.getAllErrors());
			return "admin/product/formcreate";
		}else {
			Product entity = productMapper.convertToEntity(product);
			HttpSession session = request.getSession();
			System.out.println(entity);
			productRepo.save(entity);
			session.setAttribute("status", "Thêm thành công");
			return "admin/product/formcreate";
				
		}
	}
	
	@GetMapping(value = "/edit", params = "id")
	public String edit(
		Model model,
		@RequestParam("id") Product product
	) {
		ProductDTO dto = productMapper.convertToDTO(product);
		model.addAttribute("product", dto);
		return "admin/product/formupdate";
	}
	
	
	@PostMapping(value = "/update", params = "id")
	public String update(
		Model model,
		@Valid @ModelAttribute("product") ProductDTO product,
		BindingResult result
	) {
		if(result.hasErrors()) {
			model.addAttribute("errors", result.getAllErrors());
			return "admin/product/formupdate";
		}else {
			Product entity = productMapper.convertToEntity(product);
			
			HttpSession session = request.getSession();
			session.setAttribute("status", "Update thành công");
			productRepo.save(entity);
			model.addAttribute("product", product);
			return "admin/product/formupdate";
		}
	}
	
	@RequestMapping(value = "/delete", params = "id")
	public String delete(
		@RequestParam("id") Integer id
	) {
		HttpSession session = request.getSession();
		session.setAttribute("status", "Xoá thành công");
		productRepo.deleteById(id);
		return "redirect:/product/users";
	}
	
	@RequestMapping(value = "/soft_delete", params = "id")
	public String softDelete(
		@RequestParam("id") Product product
	) {
		product.setAvailable(2);
		productRepo.save(product);
		return "redirect:/admin/product";
	}
	
	@GetMapping(value = "/restore")
	public String showListRestore(
		Model model
	) {
		List<Product> listProducts = productRepo.selectByActivate(2);
		List<ProductDTO> listDTO = productMapper.convertToListDTO(listProducts);
		model.addAttribute("listProducts", listDTO);
		return "admin/product/restore";
	}
	
	@GetMapping(value = "/restore", params = "id")
	public String restore(
		@RequestParam("id") Product product
	) {
		product.setAvailable(1);
		productRepo.save(product);
		return "redirect:/admin/product";
	}
	
}
