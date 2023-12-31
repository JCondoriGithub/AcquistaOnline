package com.acquistionline.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    
    @RequestMapping("/")
    public String login() {
        return "login";
    }

    @RequestMapping("client")
    public String createClient() {
        return "createClient";
    }

    @RequestMapping("home")
    public String home() {
        return "home";
    }

    @RequestMapping("products")
    public String products() {
        return "products";
    }

    @RequestMapping("order")
    public String order() {
        return "createOrder";
    }
}
