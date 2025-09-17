package com._09secutity.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ResourceController {

    /**
     * 1.不需要认证可以直接访问的资源;
     */
    @GetMapping("/public/hello")
    public String helloPublic(){
        return "public";
    }

    /**
     * 2.需要认证后才能访问的资源;
     */
    @PreAuthorize("hasAuthority('sys:private:view')")
    @GetMapping("/private/hello")
    public String helloPrivate(){
        return "private";
    }
}










