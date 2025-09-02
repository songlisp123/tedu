package com.eg1.demo.controller;

import jdk.jfr.Name;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class carController {

    @RequestMapping("/v1/car/add")
    public String add(String brand,String color ,double price) {
        System.out.printf("""
                品牌:%s
                颜色：%s
                价格：%8.2f
                %n""", brand,color,price);
        return "addCarComplete";
    }

    @GetMapping("/v1/car/addCarConfirm")
    public String addCarConfirm() {
        return "addCarConfirm";
    }

}
