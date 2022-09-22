package main.controller;

import main.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class BaseControllerModel {

    private final BaseService baseService;

    // 使用构造器自动注入相应的Service, 在Controller中使用服务提供的数据和结果
    @Autowired
    public BaseControllerModel(BaseService baseService) {
        this.baseService = baseService;
    }

    /**
     * @ModelAttribute: 给model添加(key - > value), 在所有的request methods之前调用, (每一次)配置公共的数据信息 !!
     * 1. 在view页面同样使用${modelMessage}来获取值
     * 2. 如果不提供key的名称, Attribute会自动根据value的值来生成key(首字母小写) !!
     */
    @ModelAttribute("modelMessage")
    public String modelMessage() {
        return "This is the model message";
    }

    /**
     * Model interface: holder for model attributes, exposed to the views, access attributes in Web Page file
     * 1. 在调用方法前，Dispatcher servlet创建Model ==> Model可以看做是一种Map(key, value) !!!
     * 2. Spring将会自动的暴露model给view page
     * 3. 可以在Model中添加attributes，在前端的页面可以使用到Model中的attributes, 通过mapKey获取 !!!
     */
    @GetMapping("model")
    public String model(Model model) { // model代表要从controller传递到view的数据 + 从view返回到controller的数据(forms) !!
        // model = {user=victor}
        model.addAttribute("user", "victor");
        return "model";
    }

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("helloMessage", baseService.getMessage("chen"));
        return "hello";
    }
}
