package com.shop.controller.admin;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shop.DTO.UserDTO;
import com.shop.entity.Order;
import com.shop.entity.User;
import com.shop.mappers.UserMapper;
import com.shop.repositories.OrderRepository;
import com.shop.repositories.UserRepoditory;
import com.shop.utils.HashUtil;
import com.shop.utils.UploadFileUtils;


@Controller
@RequestMapping("/admin/users")
public class UserController {

	@Autowired
	private UserRepoditory userRepo;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UploadFileUtils uploadfile;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private OrderRepository orderRepository;
	
	@GetMapping
	public String listUsers(
		Model model
	) {
		String sortBy = request.getParameter("sort_by");
        String sortDirection = request.getParameter("sort_direction");
        String pageParam = request.getParameter("page");
        String limitParam = request.getParameter("limit");
        String sortField = sortBy == null ? "id" : sortBy;
        Sort sort = ( sortDirection == null || sortDirection.equals("asc") ) ?
            Sort.by(Direction.ASC, sortField):
            Sort.by(Direction.DESC, sortField);
        int page = pageParam == null ? 0 : Integer.parseInt(pageParam);
        int limit = limitParam == null ? 2 : Integer.parseInt(limitParam);
        Pageable pageable = PageRequest.of(page, limit, sort);
        Page<User> pageData = this.userRepo.findAll(pageable);
        model.addAttribute("listUsers", pageData);
        return "admin/users/index";
	}

	@GetMapping(value = "id")
	public String show(
			@PathVariable("id") User user,
			Model model
	) {
		UserDTO userDTO =  userMapper.convertToDTO(user);
		model.addAttribute("user", userDTO);
		return "admin/users/formcreate";
	}
	
	@GetMapping(value = "/create")
	public String create(
		@ModelAttribute("user") UserDTO user
	) {
		return "admin/users/formcreate";
	}
	
	@PostMapping("/store")
	public String store(
		Model model,
		@Valid @ModelAttribute("user") UserDTO user,
		BindingResult result,
		@RequestParam("upload_file") MultipartFile uploadFile
	) {
		if( uploadFile.getOriginalFilename() == "" ) {
			result.rejectValue("photo", "errors", "Chọn ảnh");
		}
		if(result.hasErrors()) {
			model.addAttribute("errors", result.getAllErrors());
			return "admin/users/formcreate";
		}else {
			User entity = userMapper.convertToEntity(user);
			String hashpassword = HashUtil.hash(entity.getPassword());
			entity.setPassword(hashpassword);
			
			File file = uploadfile.handleUploadFile(uploadFile);
			entity.setPhoto(file.getName());
			
			User userFind = userRepo.findUserByEmail(entity.getEmail());
			HttpSession session = request.getSession();
			
			if(userFind == null) {
				userRepo.save(entity);
				session.setAttribute("status", "Thêm thành công");
				return "admin/users/formcreate";
			}else {
				session.setAttribute("error", "Email không được trùng");
				return "admin/users/formcreate";
			}	
		}
	}
	
	@GetMapping(value = "/edit", params = "id")
	public String edit(
		Model model,
		@RequestParam("id") User user
	) {
		List<Order> listOrders =  orderRepository.searchByIDUser(user.getId());
		model.addAttribute("listOrders", listOrders);
		
		UserDTO dto = userMapper.convertToDTO(user);
		model.addAttribute("user", dto);
		return "admin/users/formupdate";
	}
	
	@PostMapping(value = "/update", params = "id")
	public String update(
		Model model,
		@Valid @ModelAttribute("user") UserDTO user,
		BindingResult result,
		@RequestParam("id") User us,
		@RequestParam("upload_file") MultipartFile uploadFile
	) {
		if(result.hasErrors()) {
			model.addAttribute("errors", result.getAllErrors());
			return "admin/users/formupdate";
		}else {
			User entity = userMapper.convertToEntity(user);
			String password = userRepo.getById(entity.getId()).getPassword();
			entity.setPassword(password);
			
			if( uploadFile.getOriginalFilename() != "") {
				File file = uploadfile.handleUploadFile(uploadFile);
				entity.setPhoto(file.getName());
			}else {
				entity.setPhoto(us.getPhoto());
			}
			
			HttpSession session = request.getSession();
			User check = userRepo.searchByName(entity.getEmail(), entity.getId());
			
			if(check == null) {
				session.setAttribute("status", "Update thành công");
				userRepo.save(entity);
				return "admin/users/formupdate";
			}else {
				session.setAttribute("error", "Email không được trùng");
				return "admin/users/formupdate";
			}
			
		}
	}
	
	@RequestMapping(value = "/delete", params = "id")
	public String delete(
		@RequestParam("id") Integer id
	) {
		HttpSession session = request.getSession();
		session.setAttribute("status", "Xoá thành công");
		userRepo.deleteById(id);
		return "redirect:/admin/users";
	}
	
	@RequestMapping(value = "/soft_delete", params = "id")
	public String softDelete(
		@RequestParam("id") User user
	) {
		System.out.println(user.getId());
		user.setActivate(2);
		userRepo.save(user);
		return "redirect:/admin/users";
	}
	
	@GetMapping(value = "/restore")
	public String showListRestore(
		Model model
	) {
		List<User> listUsers = userRepo.selectByActivate(2);
		List<UserDTO> listDTO = userMapper.convertToListDTO(listUsers);
		model.addAttribute("listUsers", listDTO);
		return "admin/users/restore";
	}
	
	@GetMapping(value = "/restore", params = "id")
	public String restore(
		@RequestParam("id") User user
	) {
		user.setActivate(1);
		userRepo.save(user);
		return "redirect:/admin/users";
	}
	
}
