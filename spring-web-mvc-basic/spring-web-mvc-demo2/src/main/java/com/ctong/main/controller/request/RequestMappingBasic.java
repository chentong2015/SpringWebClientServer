package com.ctong.main.controller.request;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 使用JSP View会在第一次请求时, 被Web Container(Tomcat)编译, 造成延迟 !!
 * /home: 等效的访问路径/home.html /home.php /home.jsp
 * 只使用第一部分作为Mapping路径 ?? .setSuffixPatternMatch() 已经废弃
 */
@Controller
@RequestMapping(value = "/requestMappingBasic") // Controller中所有的路径都是相对于这个全局的路径
public class RequestMappingBasic {

    /**
     * @RequestParam Annotation
     * access specific Servlet request parameters, 使用在Controller中的方法参数上面, 绑定请求的参数
     * 1. 默认必须提供参数, 可设置optional : @RequestParam(value="id", required=false)
     * 2. 提供参数的名称就是申明变量的名称  : @RequestParam("petId") int petId
     * 3. 通过URL来提供参数给Controller  : http://localhost:8080/spring-mvc/hello?user=chen
     */

    @RequestMapping(value = "/test1")
    public String homeTest1(@RequestParam("name") String name, Model model) { // 这里的名称大小写严格铭感
        model.addAttribute("urlName", name);
        model.addAttribute("status", "Get name value from URL");
        return "home";
    }

    // Restrict the handler method activity 默认情况下方法可以同时用于GET和POST请求 !!
    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public String homeTest2(@RequestParam("name") String name) {
        return "name";
    }

    // 可以不用提供@RequestParam的值名称, 直接使用"参数变量的名称" !!
    @RequestMapping(value = "/test3")
    public String homeTest3(@RequestParam String name) {
        return "name";
    }

    // 提供请求参数的默认值, 在没有提供RequestParam值时候不会报错
    @RequestMapping(value = "/test4")
    public String homeTest4(@RequestParam(value = "name", defaultValue = "Default Name") String name, Model model) {
        model.addAttribute("name", name);
        return "home";
    }

    // 这里处理其余所有"没有定义的路径"的处理方法, 并提供指定的反馈信息, 而不是使用默认的(404 error)错误信息
    // 可以同时应用在不同的请求上面
    @RequestMapping(value = "*", method = {RequestMethod.GET, RequestMethod.POST})
    public String homeTest5() {
        return "fallback";
    }
}
