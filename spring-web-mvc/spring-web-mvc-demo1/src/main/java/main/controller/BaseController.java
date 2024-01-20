package main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Controller: specification of the component annotation
 * 1. 一种特殊的@Component类型, 作为Web Controller来使用, 和Component被处理的方式一致
 * 2. Scanned by spring 同样会被Spring扫描到container
 * 3. Scan the methods that use request mapping annotation 会扫描控制器中@RequestMapping标记的方法 !!!
 */
@Controller
public class BaseController {

    /**
     * @Controller : Handles Request Mapping, Request Input, Exception handling, and more ...
     * @RequestMapping: Map requests to com.ctong.springboot.controller methods
     * @GetMapping / @PostMapping / @PutMapping / @DeleteMapping / @PatchMapping
     * @ResponseBody: 方法返回的值将会绑定到Web response body
     * DispatcherServlet将会重定向请求到该方法(mapped Get Request to method), 以下方法将会被触发
     */
    @ResponseBody
    @GetMapping("index") // 如果提供具体的路径，则会接收所有的request
    public String test() {
        return "Index Page";
    }

    @RequestMapping(value = "main", method = RequestMethod.GET)
    public String main() {
        return "main";
    }

    /**
     * http://localhost:8080/spring-mvc/home
     * 1. 这里ViewResolver会自动补充前后缀(只使用view页面的名称), 补全文件路径 /WEB-INF/views/home.jsp
     * 2. 声明方法的名称可自定义, 不一定和view name保持一致
     * 3. 返回的string也会经过ViewResolver处理(找到), 代表logical views name, the name for the web page file
     */
    @GetMapping("home")
    public String home() {
        return "home";
    }

}
