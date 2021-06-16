package com.shop.controller.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.shop.utils.CookieUtils;

@Controller
public class LogoutController {
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpServletResponse response;
	
	@GetMapping("/logout")
	public String logout() {
		HttpSession session =  request.getSession();
		session.removeAttribute("user");
		session.removeAttribute("listOrderDetails");
		CookieUtils.addCookie("JSESSIONID", session.getId() , -1, response);
		return "redirect:/login";
	}
}
