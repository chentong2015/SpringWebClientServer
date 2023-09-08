package controller;

import controller.beans.BaseBeanRequestScope;
import controller.beans.BaseBeanSessionScope;
import controller.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class HomeController {

    @ResponseBody
    @GetMapping("index")
    public String test() {
        return "Index Page";
    }

    @GetMapping("home")
    public String getStaticPage() {
        return "home.html";
    }

    @Autowired
    private HomeService homeService;

    // /WEB-INF/views/home.jsp 使用servlet处理找到指定的非静态的页面 !!!
    @RequestMapping("/")
    public String helloWebPage(Model model) {
        model.addAttribute("message", homeService.getHomeMessage());
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
