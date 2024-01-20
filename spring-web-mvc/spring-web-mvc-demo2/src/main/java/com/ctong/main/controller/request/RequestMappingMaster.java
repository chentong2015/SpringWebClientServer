package com.ctong.main.controller.request;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @RequestMapping: 两种使用层级
 * 1. At Class Level: maps a specific request path(or path pattern) to a com.ctong.springboot.controller
 * 2. At method level: 在具体的方法上提供精确的请求路径
 */
@Controller
@RequestMapping(value = "/requestMappingMaster") // Controller中所有的路径都是相对于这个全局的路径
public class RequestMappingMaster {

    // 两个Handler method方法可以共享一个请求的URL, 但是提供的参数不同
    // 必须声明params变量的名称，否则会报错 Ambiguous mapping, cannot map "..Controller" method !!
    @RequestMapping(value = "/subTest", params = "name1")
    public String homeSubTest1(@RequestParam String name1) {
        return "name";
    }

    @RequestMapping(value = "/subTest", params = "name2")
    public String homeSubTest2(@RequestParam String name2) {
        return "name";
    }


    // 提供过个请求的参数，同时也作为Handler method的参数传递
    // http://localhost:8080/spring-mvc-master/requestMappingMaster/subTest/multiParam1?name1=Test1&name2=Test2
    @RequestMapping(value = "/subTest/multiParam1", method = RequestMethod.GET, params = {"name1", "name2"})
    public String homeSubTest3(@RequestParam String name1, @RequestParam String name2, Model model) {
        return "home";
    }

    // params = {"name1", "name2"} 必须提供指定的请求参数(值可以为空), Handler method只会取相应的参数值 !!
    @RequestMapping(value = "/subTest/multiParam2", method = RequestMethod.GET, params = {"name1", "name2"})
    public String homeSubTest4(@RequestParam String name1, Model model) {
        return "home";
    }

    // Handler method 同时处理两种URL请求
    @RequestMapping(value = {"/subTest/multiUrl1", "/subTest/multiUrl2"}, method = RequestMethod.GET)
    public String homeSubTest5(@RequestParam String name1, Model model, HttpServletRequest request) {
        if (request.getRequestURL().toString().contains("multiUrl1")) {
            model.addAttribute("test", "multiUrl1");
        } else {
            model.addAttribute("test", "multiUrl2");
        }
        if (request.getMethod().equalsIgnoreCase("POST")) {
            System.out.println("This is a post request");
        } else {
            System.out.println("This is a get request");
        }
        return "home";
    }
}
