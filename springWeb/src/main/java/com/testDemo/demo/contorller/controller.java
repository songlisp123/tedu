package com.testDemo.demo.contorller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user/")
public class controller {

    @PostMapping("add")
    public String add() {
        return "1";
    }

    @PostMapping("login")
    public String login() {
        return "2";
    }

    @PostMapping("changePassword")
    public String change() {
        return "3";
    }
}
