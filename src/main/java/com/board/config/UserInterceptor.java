package com.board.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.HandlerInterceptor;

@Log4j2
public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("UserInterceptor - preHandle");
        HttpSession session = request.getSession();
        if(session.getAttribute("loginedUser") == null){
            response.sendRedirect("/main");
            return false;
        }

        return true;
    }
}









