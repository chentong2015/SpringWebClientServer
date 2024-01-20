package com.ctong.main.controller.request;

import com.ctong.main.controller.dataModel.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @RequestAttribute annotation: name / value / required (default is true)
 * 1. Bind a request attribute to a handler method parameter
 * 2. Can access pre-existing request attributes added in servlet filter or interceptor 可以访问预先存在于过滤器或者拦截器中的请求属性 !!
 * .
 * @RequestParam annotation
 * Bind parameters in a "query" string 将方法参数绑定到query中参数
 */
@Controller
public class BaseRequestAttribute {

    // 将请求的Attribute绑定到处理器的方法product参数
    @RequestMapping("/testProduct")
    public String testProduct(@RequestAttribute(name = "product") Product product) {
        return "testProduct";
    }
}
