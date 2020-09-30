package client7002.controller;

import com.maven.common.entity.namesilo7005.TbDomain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/ok")
    public String test(){
        TbDomain comA = new TbDomain();
        return "ok 7002"+comA.toString();
    }
}
