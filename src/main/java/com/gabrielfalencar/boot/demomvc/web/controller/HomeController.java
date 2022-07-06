package com.gabrielfalencar.boot.demomvc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    //Método para abertura do página home.html
    @GetMapping("/")
    public String home() {
        return "/home";
    }
}
