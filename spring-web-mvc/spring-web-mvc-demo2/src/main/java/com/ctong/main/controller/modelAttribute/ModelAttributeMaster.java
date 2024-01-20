package com.ctong.main.controller.modelAttribute;

import com.ctong.main.controller.modelAttribute.model.Address;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ModelAttributeMaster {

    // <form id="anAddress" action="test5" method="post"> 可以使用GET或者POST提交表单
    // @ModelAttribute(value = "anAddress") 绑定提交到handler method方法的form表单数据 !!
    @RequestMapping(value = "/test5", method = RequestMethod.POST)
    public String modelAttributeTest5(@ModelAttribute(value = "anAddress") Address anAddress, ModelMap modelMap) {
        modelMap.addAttribute("data1", anAddress.getName());
        modelMap.addAttribute("data2", anAddress.getPostalCode());
        return "modelAttributeTest";
    }

    /**
     * 在不提供view name的情况下，如何Spring MVC如何判断 ?
     * Spring MVC 默认提供DefaultRequestToViewNameTranslator来处理 !!
     * 1. The RequestToViewNameTranslator interface determines the logical view name !!
     * 2. The DefaultRequestToViewNameTranslator class (接口唯一实现) maps request URL to logical view name !!
     */
    @RequestMapping(value = "/modelAttributeTest") // Spring将会(重新)渲染该请求的View页面
    @ModelAttribute("data3")
    public Address modelAttributeTest6() {
        return new Address("london", "1200");
    }
}
