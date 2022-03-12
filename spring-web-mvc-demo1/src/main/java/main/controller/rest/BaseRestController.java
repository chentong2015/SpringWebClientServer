package main.controller.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @RestController annotation from Spring MVC is composed of @Controller and @ResponseBody
 * 针对RESTful controller控制器
 */
// @RestController
public class BaseRestController {

    @GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    public String index() {
        return "This is Home page";
    }

    @GetMapping(value = "/hello", produces = MediaType.TEXT_PLAIN_VALUE)
    public String sayHello() {
        return "Hello there!";
    }
}
