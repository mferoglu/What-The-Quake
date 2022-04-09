package com.earthquake.whatthequake;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

    @RequestMapping("/")
    public String welcome(){
        return "index.html";
    }

}
