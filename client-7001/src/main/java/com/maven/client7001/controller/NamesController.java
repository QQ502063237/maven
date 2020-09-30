package com.maven.client7001.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller()
public class NamesController {

    @ResponseBody
    @PostMapping("/form")
    public String form(String domains[]){
        System.out.println("进入方法");
//        System.out.println(domains.toString());
        return  "ok";
    }
}
