package com.shop.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

@Service
public class CookieUtils {
	
    public static Cookie getCookie(String name, HttpServletRequest request) {
        Cookie[] cookies=request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }
    
    public static Cookie addCookie(String name, String value, int hours, HttpServletResponse response) {
        Cookie cookie=new Cookie(name, value);
        cookie.setMaxAge(60 * 60  * hours);
        cookie.setPath("/Shop");
        response.addCookie(cookie);
        return cookie;
    }
}
