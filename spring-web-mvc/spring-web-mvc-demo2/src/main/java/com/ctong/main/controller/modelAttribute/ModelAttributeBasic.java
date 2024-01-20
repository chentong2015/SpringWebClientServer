package com.ctong.main.controller.modelAttribute;

import com.ctong.main.controller.modelAttribute.model.Address;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ModelAttributeBasic {

    /**
     * 1. 使用@ModelAttribute注解的方法(可以有多个)会优先于@RequestMapping注解的方法被触发invoke
     * 2. @ModelAttribute和@RequestMapping拥有同样的参数设置，但是不能用来直接map request
     */

    // 1. Add global Model Attribute for all handler methods
    // 所有标记@RequestMapping的方法都能使用 !!
    @ModelAttribute
    public void modelAttributeTest1(Model model) {
        model.addAttribute("data1", "test data 1");
    }

    // 2. Define name to access the value of Model Attribute
    // 使用名称来访问指定model的值
    @ModelAttribute(name = "data2")
    public String modelAttributeTest2() {
        return "test data 2";
    }

    // 3. 提供所有handler method需要的公共model attribute属性(相当于全局的)
    @ModelAttribute(name = "data3")
    public Address modelAttributeTest3() {
        return new Address("paris", "75100");
    }

    // 4. 默认根据类型来定义model attribute的名称 Address -> name = "address" !!
    @ModelAttribute
    public Address modelAttributeTest4() {
        return new Address("paris", "75100");
    }

    @RequestMapping(value = "/home")
    public String home() {
        return "modelAttributeHome";
    }

    /**
     * ModelAndView is a container of both the view and model ==> 以下4个方法功能等效 !!
     */
    @RequestMapping(value = "/home2")
    public ModelAndView home2() {
        ModelAndView modelAndView = new ModelAndView();
        // 定义要跳转的VIEW页面
        modelAndView.setViewName("modelAttributeHome");
        // 这里添加的对象是会在新的view中作为model attribute来使用
        modelAndView.addObject("anAddress", new Address("paris", "1000"));
        return modelAndView;
    }

    @RequestMapping(value = "/home3")
    public ModelAndView home3() {
        ModelAndView modelAndView = new ModelAndView("modelAttributeHome");
        modelAndView.addObject("anAddress", new Address("paris", "1000"));
        return modelAndView;
    }

    @RequestMapping(value = "/home4")
    public ModelAndView home4() {
        Address address = new Address("paris", "1000");
        return new ModelAndView("modelAttributeHome", "anAddress", address);
    }

    @RequestMapping(value = "/home5")
    public String home5(Model model) {
        Address address = new Address("paris", "1000");
        model.addAttribute("anAddress", address);
        return "modelAttributeHome";
    }
}
