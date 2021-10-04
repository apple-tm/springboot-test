package com.atguigu.boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WordController {

    @RequestMapping(value = "/w")
    public String sayWord() {
        return "word";
    }
}
