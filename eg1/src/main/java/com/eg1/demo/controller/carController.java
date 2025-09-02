package com.eg1.demo.controller;

import com.eg1.demo.pojo.DTO.carDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class carController {

    @PostMapping("/v1/car/add")
    public String add(String brand,String color ,double price) {
        System.out.printf("""
                品牌:%s
                颜色：%s
                价格：%8.2f
                %n""", brand,color,price);
        return "carTemplates/addCarComplete";
    }

    @GetMapping("/v1/car/addCarConfirm")
    public String addCarConfirm() {
        return "carTemplates/addCarConfirm";
    }

    @PostMapping("/v1/car/add2")
    public String add2(carDto carDto) {
        System.out.println("添加车辆"+carDto);
        return "carTemplates/addCarComplete";
    }

    /*
    RequestMapping标签与GetMapping标签的区别
     RequestMapping：可以接受get。post请求
     GetMapping：只可以接受get请求
     要指定单独的接受方法请使用：
     PostMapping-->只接受post请求
     */



}
