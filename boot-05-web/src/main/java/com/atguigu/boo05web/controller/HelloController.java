package com.atguigu.boo05web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    /**
     * 默认所有请求先走动态资，再走静态资源，所有 URI 一致的动静态资源会优先返回动态资源
     * 为避免动静态资源URI冲突，使用配置给静态资源uri加上前缀
     * spring.mvc.static-path-pattern=/res/**,注意并不是将static资源放到res下
     *
     * @return
     */
    @GetMapping("/fool.jpg")
    public String get() {
        // 需返回 JSON，否则默认为交给视图解析器的参数
        return "fool.jpg";
    }
}
