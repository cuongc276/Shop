package com.shop.utils;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.stereotype.Service;

import com.shop.entity.User;

@Service
public class RememberMeUtils {

	
	public static Boolean rememberMe( String remember, User user , HttpServletResponse response, HttpServletRequest request) {
		if(remember == "on") {
			System.out.println("a");
			SessionUtils.addSession(request, "user", user);
			CookieUtils.addCookie("rememberMe", "true", 24, response);
			CookieUtils.addCookie("ids", request.getSession().getId(), 24, response);
			return true;
		}else {
			return false;
		}
	}
}
