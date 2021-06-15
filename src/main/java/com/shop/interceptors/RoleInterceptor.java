package com.shop.interceptors;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.shop.entity.User;

@Component
public class RoleInterceptor implements HandlerInterceptor {

	@Override
    public boolean preHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler
    ) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println("role");
        if ( user.getAdmin() != 1  ) {
        	response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
        return true;
    }
}
