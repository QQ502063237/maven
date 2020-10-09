package com.maven.chinapay7006.controller;


import com.maven.common.vo.HttpResult;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/chinaPay")
public class ChinaPayController {


    @GetMapping("/up/{keyWord}")
    public HttpResult<Object> up(@PathVariable("keyWord") String keyWord) throws IOException {
        HttpResult<Object> result = new HttpResult<>();
//        try {

            result.setMessage("更新指定"+keyWord+"成功");
            return result;
//        } catch (Exception e) {
//            result.setMessage("更新指定"+keyWord+"失败");
//            return result;
//        }
    }


}
