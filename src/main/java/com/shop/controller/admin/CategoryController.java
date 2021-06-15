package com.shop.controller.admin;

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

import com.shop.DTO.CategoryDTO;
import com.shop.entity.Category;
import com.shop.mappers.CategoryMapper;
import com.shop.repositories.CategoryRepository;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {
 
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private CategoryMapper categoryMapper;
	
	@Autowired
	private HttpServletRequest request;
	
	@GetMapping
	public String listCategories(
		Model model
	) {
		List<Category> list = categoryRepo.findAll();
		List<CategoryDTO> listDsTO = categoryMapper.convertToListDTO(list);
		model.addAttribute("listCategory", listDsTO);
		return "admin/category/listcategory";
	}
	
	@GetMapping(value = "id")
	public String showACategory(
			@PathVariable("id") Category category,
			Model model
	) {
		CategoryDTO cateDTO = categoryMapper.convertToDTO(category);
		model.addAttribute("category", cateDTO);
		return "admin/category/formcreate";
	}
	
	@GetMapping(value = "/create")
	public String createCategory(
		@ModelAttribute("category") CategoryDTO cate
	) {
		return "admin/category/formcreate";
	}
	
	@PostMapping("/store")
	public String store(
		Model model,
		@Valid @ModelAttribute("category") CategoryDTO cate,
		BindingResult result
		
	) {
		
		if(result.hasErrors()) {
			model.addAttribute("errors", result.getAllErrors());
			return "admin/category/formcreate";
		}else {
			
			Category entity = categoryMapper.convertToEntity(cate);
			
			Category cateFind = categoryRepo.getCategoryByName(entity.getName());
			HttpSession session = request.getSession();
			
			if(cateFind == null) {
				categoryRepo.save(entity);
				session.setAttribute("status", "Thêm thành công");
				return "admin/category/formcreate";
			}else {
				session.setAttribute("error", "Name không được trùng");
				return "admin/category/formcreate";
			}	
		}
	}
	
	@GetMapping(value = "/edit", params = "id")
	public String edit(
		Model model,
		@RequestParam("id") Category category
	) {
		CategoryDTO dto = categoryMapper.convertToDTO(category);
		model.addAttribute("category", dto);
		return "admin/category/formupdate";
	}
	
	@PostMapping(value = "/update", params = "id")
	public String update(
		Model model,
		@Valid @ModelAttribute("category") CategoryDTO category,
		BindingResult result
	) {
		if(result.hasErrors()) {
			model.addAttribute("errors", result.getAllErrors());
			return "admin/category/formupdate";
		}else {
			Category entity =  categoryMapper.convertToEntity(category);

			HttpSession session = request.getSession();
			Category check = categoryRepo.searchCategory(entity.getName(), entity.getId());
			
			if(check == null) {
				session.setAttribute("status", "Update thành công");
				categoryRepo.save(entity);
				return "admin/category/formupdate";
			}else {
				session.setAttribute("error", "Name không được trùng");
				return "admin/category/formupdate";
			}
			
		}
	}
	
	@RequestMapping(value = "/delete", params = "id")
	public String delete(
		@RequestParam("id") Integer id
	) {
		HttpSession session = request.getSession();
		session.setAttribute("status", "Xoá thành công");
		categoryRepo.deleteById(id);
		return "redirect:/admin/category";
	}
	
}
