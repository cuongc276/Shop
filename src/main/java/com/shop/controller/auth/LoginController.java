package com.shop.controller.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.DTO.UserDTO;
import com.shop.entity.User;
import com.shop.repositories.UserRepoditory;
import com.shop.utils.CookieUtils;
import com.shop.utils.HashUtil;
import com.shop.utils.LoginUtils;

@Controller
public class LoginController {
	
	@Autowired
	private UserRepoditory userRepo;
	
	@Autowired
	private HttpServletRequest request;
	
	@GetMapping("/login")
	public String getLoginForm(
			@ModelAttribute("user") UserDTO user
	) {
		if(LoginUtils.getRole(request) == "admin") {
			return "redirect:/admin/home";
		}
		if(LoginUtils.getRole(request) == "user") {
			return "redirect:/home";
		}
		return "auth/login";
	}
	
	@PostMapping("/login")
	public String login(
		@ModelAttribute("user") UserDTO user,
		@RequestParam(value = "remember", required = false) String remember,
		HttpServletResponse response
	) {
		
		HttpSession sesion = request.getSession();
		User entity = this.userRepo.findUserByEmail(user.getEmail());
		
		
		if( entity == null) {
			sesion.setAttribute("error", "Sai email hoặc mật khẩu");
			return "redirect:/login";
		}
		
		if( entity.getActivate() == 2) {
			sesion.setAttribute("error", "Tài khoản bị khóa mau mau nộp tiền");
			return "redirect:/login";
		}
		
		boolean checkPass = HashUtil.verify( user.getPassword(), entity.getPassword());

		if(!checkPass) {
			sesion.setAttribute("error", "Sai email hoặc mật khẩu");
			return "redirect:/login";
		}
		
		HttpSession session = request.getSession();
		
		if(remember != null) {
			CookieUtils.addCookie("JSESSIONID", session.getId() , 24, response);		
		}else {
			CookieUtils.addCookie("JSESSIONID", session.getId() , -1, response);
		}
		
		sesion.setAttribute("user", entity);
		if(entity.getAdmin() == 1) {
			return "redirect:/admin/home";
		}
		return "redirect:/home";
	}
}
