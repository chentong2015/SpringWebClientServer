package com.ctong.main.controller.sessionAttribute.base;

import com.ctong.main.controller.dataModel.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

/**
 * @SessionAttribute annotation: name / value / required (default is true)
 * 1. Bind a session attribute
 *   (retrieved form javax.servlet.http.HttpSession) to a handler method parameter
 * 2. Can access pre-existing request attributes added in servlet filter or interceptor
 */
@Controller
public class BaseSessionAttribute {

    // 可以访问预先存在的请求中的attributes
    // The session attribute "product" gets bound to handler method argument product
    @RequestMapping("/testProduct")
    public String testProduct(@SessionAttribute(name = "product") Product product) {
        return "testProduct";
    }
}
