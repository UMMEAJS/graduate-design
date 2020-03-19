package com.oncb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    @RequestMapping("/index")
    public  String index() {
        return "home/index";
    }

    @RequestMapping("/msg")
    public  String msg() {
        return "home/msg";
    }
}