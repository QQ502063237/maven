package com.maven.client7001.controller;

import com.maven.client7001.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    @Resource
    UserMapper userMapper;

    @GetMapping("/ok")
    public String test(){
        return "ok 7001";
    }

}
