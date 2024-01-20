package com.ctong.main.cookie;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class BaseCookie {

    /**
     * 创建Session：context 用于在clients和servers直接交换信息的上下文
	 * 记录用户活动和状态(角色，登陆时间...), 可以跨request获取到设置的属性
     */
    @RequestMapping("/testCookie")
    public String testCookie(HttpServletRequest request,
                             @RequestParam(value = "cookie", required = false) String cookie,
                             HttpServletResponse response) {
        String forwardPage = filterRequestToPage(request.getSession());
        response.addCookie(getCookie(cookie));
        return forwardPage;
    }

    /**
     * 根据创建的Session, 过滤掉指定页面的访问, 执行重定向跳转  ====> 推荐: Spring MVC中的拦截器Interceptor
     */
    private String filterRequestToPage(HttpSession session) {
        boolean status = (boolean) session.getAttribute("login");
        return status ? "successPage" : "anotherPage";
    }

    /**
     * Cookie: 依赖于浏览器的Session认证，使用Cookie可以授权用户的身份  ===> session_id 数据保留在客户端，请求时将该id传回服务器 ==> Json Web Token
     * 根据用户的选择，设置Cookie认证的预留时间: 1天有效期或者立刻过期  ===> 不同的软件授权时间不同 !!
     */
    private Cookie getCookie(String cookie) {
        int expire = -1;
        String cookieValue = "Off";
        if (cookie != null && cookie.equalsIgnoreCase("enabled")) {
            expire = 3600 * 24;
            cookieValue = "On";
        }
        Cookie newCookie = new Cookie("cookie", cookieValue);
        newCookie.setMaxAge(expire);
        return newCookie;
    }
}
