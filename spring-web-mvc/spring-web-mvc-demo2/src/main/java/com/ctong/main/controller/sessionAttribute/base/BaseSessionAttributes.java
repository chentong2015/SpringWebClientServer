package com.ctong.main.controller.sessionAttribute.base;

import com.ctong.main.controller.dataModel.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @SessionAttributes Annotation
 * 1. 适合零时在Session中存储model attributes
 * 2. Class level annotation, on @Controller class 匹配@ModelAttributes中的名称
 */
@Controller
@SessionAttributes("product") // 指定存储在Session中的Model Attribute !!
@RequestMapping("/products")
public class BaseSessionAttributes {

    // 如果这里不提供model attribute, 可能导致Session中的attribute无法找到: HttpSessionRequiredException
    @ModelAttribute("product")
    public Product getModel() {
        return new Product();
    }

    /**
     * 但下面的方法被触发的时候
     * 1. Spring将查找@SessionAttributes("product"), Look for an attribute "product" in sessionAttribute (javax.servlet.http.HttpSession)
     * 2. If "product" is absent in sessionAttribute, invoke @ModelAttribute method getProduct() 使用Model attribute的值填充session中的属性值
     * 3. Spring bind the value of sessionAttribute attribute "product" to handler method argument "aProduct"
     */
    @RequestMapping("/productCatalogTest1")
    public String processProductTest1(@ModelAttribute("product") Product aProduct, Model model, HttpServletRequest request) {
        return "productCatalog1";
    }

    // 由于这里使用的是不同的model attribute, 因此Spring不会将session attribute中的值绑定过来 !!
    // 这里参数的model attribute将会process the submitted form
    @RequestMapping("/productCatalogTest2")
    public String processProductTest2(@ModelAttribute("anotherProduct") Product aProduct, Model model, HttpServletRequest request) {
        return "productCatalog2";
    }
}
