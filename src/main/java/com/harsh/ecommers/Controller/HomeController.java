package com.harsh.ecommers.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "<h1>This is Home Controller with H1 Tag</h1>" +
                "<h2>This is h2 </h2>";
    }
    @GetMapping("/about")
    public String about() {
        return "This is About Page";
    }

}
