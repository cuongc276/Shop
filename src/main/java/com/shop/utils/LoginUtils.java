package com.shop.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.shop.entity.User;

@Service
public class LoginUtils {

	public static User isLogin( HttpServletRequest request ) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if( user != null) {
			if(user.getActivate() == 1) {
				if(session.getAttribute("user") == null) {
					session.setAttribute("error", "Chưa đăng nhập");
					return null;
				}
			}else {
				session.setAttribute("error", "Tài khoản bị khóa");
				return null;
			}
		}
		
		return user;
	}
	
	public static String getRole( HttpServletRequest request ) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(isLogin(request) != null) {
			if(user.getAdmin() == 1) {
				return "admin";
			}else {
				return "user";
			}
		}else {
			return "auth";
		}
	}
}
