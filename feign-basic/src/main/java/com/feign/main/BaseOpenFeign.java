package com.feign.main;

import com.feign.main.networking.FeignRequestClient;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

// TODO: https://github.com/OpenFeign/feign
// Feign allows you to write your own code on top of http libraries such as Apache HC
// 构建在Http上层的类库，封装在Http类库基础上

// 通过将注解处理成模板请求，来指定HTTP Request
// 1. 可以使用常见的http client来build
// 2. 可以自定义编码和解码所使用的类库，Java层面只处理对象，不考虑数据的解析
// 3. 直接通过调用方法来执行Http Client请求，减少对请求的自定义配置和封装
// 4. 提供日志配置
// 5. 提供Http Client请求的Timeout
public class BaseOpenFeign {

    // 基本使用方式:
    // 1. 定义接口，通过注解来配置请求的模板
    // 2. 配置请求的参数和传递的数据(格式)
    // 3. 自定义接口中的default方法
    // 4. target()到指定的网址，通过调用接口方法来执行不同的Http请求

    public static void main(String[] args) {
        FeignRequestClient feignClient = Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(FeignRequestClient.class, "https://localhost/demo");
        feignClient.callChaosFast();
    }
}
