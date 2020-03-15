package com.oncb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("/index")
    public  String index(HttpServletRequest request){
        if (request.getSession().getAttribute("isAdmin") != null) {
            return "admin/index";
        } else {
            request.setAttribute("msg", "你没有管理员权限!");
            return "home/msg";
        }
    }
}
