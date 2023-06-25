package com.human.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NaverLoginController {
    @GetMapping("/auth/naver_login")
    public String showNaverLogin() {
        return "auth/naver_login"; // naver_login.html을 반환
    }
    
    @GetMapping("/auth/naver_callback")
    public String showNaverCallback() {
        return "auth/naver_callback";
    }
}

