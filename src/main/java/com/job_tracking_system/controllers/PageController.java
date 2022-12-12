package com.job_tracking_system.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/api/pages")
public class PageController {

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

}