package com.michal.amigoscode.security.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/demo")
@RestController
public class DemoController {

    public String greeting(){
        return "Greeting";
    }
}
