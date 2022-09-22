package com.spring.mvc.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.mvc.beans.BaseBeanRequestScope;
import com.spring.mvc.beans.BaseBeanSessionScope;
import com.spring.mvc.service.HomeService;

@Controller
public class HomeController {

	@ResponseBody
	@GetMapping("index")
	public String test() {
		return "Index Page";
	}

	/**
	 * TODO: 如何返回不需要servlet处理静态的页面
	 * <mvc:resources location="/static/" mapping="/static/**" /> 配置静态资源的控制器
	 * https://stackoverflow.com/questions/15479213/how-to-serve-html-files-with-spring
	 */
	@GetMapping("home")
	public String getStaticPage() {
		return "home.html";
	}

	// @Autowired 使Spring自动从Application Context找到满足依赖的bean, 完成注入
	@Autowired
	private HomeService homeService;

	/**
	 * /WEB-INF/views/home.jsp 使用servlet处理找到指定的非静态的页面 !!!
	 */
	@RequestMapping("/")
	public String helloWebPage(Model model) {
		model.addAttribute("message", homeService.getHomeMessge());
		// view name将会被view resolver解析, 到指定的view页面
		return "home";
	}

	// Request Scope is created, each time page created or rendered 不保留状态，随着请求进行更新
	@Autowired
	private BaseBeanRequestScope requestScope;

	// Session Scope keeps state even refreshes 保留bean对象的前一个(记录的)状态
	@Autowired
	private BaseBeanSessionScope sessionScope;

	@RequestMapping("/scope")
	public void testScope(HttpServletResponse reponse) {
		try {
			reponse.getWriter().write("Request Scope");
			reponse.getWriter().write(requestScope.getCount()); // -1 每个HTTP请求会创建新的bean对象
			reponse.getWriter().write(requestScope.generateRandomCount()); // new random count 全新随机数
			reponse.getWriter().write("Session Scope");
			// Session scope Bean对象会预留
			reponse.getWriter().write(sessionScope.getCount()); // -1 & old random count 第一次为-1，之后为前面出现的随机数
			reponse.getWriter().write(sessionScope.generateRandomCount()); // new random count
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
