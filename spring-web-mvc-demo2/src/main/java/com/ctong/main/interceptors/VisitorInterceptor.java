package com.ctong.main.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

// Interceptor 拦截器：Spring Web提供的实现
// 构建全局的Session Attribute和Request Attribute, 绑定Handler Method中的参数
public class VisitorInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("currentTime", LocalDateTime.now());

        // Create a http session object, true: if there is no http session, create a new one !!
        HttpSession session = request.getSession(true);
        if (session.getAttribute("sessionStartTime") == null) {
            // Create a session attribute for using by @SessionAttribute Annotation !!
            session.setAttribute("sessionStartTime", LocalDateTime.now());
        }
        return true;
    }
}
