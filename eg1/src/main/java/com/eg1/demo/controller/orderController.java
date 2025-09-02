package com.eg1.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class orderController {
//
//    @RequestMapping("/v1/orders/add")
//    @ResponseBody
//    public String add() {
//        return "创建订单成功！";
//    }
//
//    @RequestMapping("/v1/orders/list")
//    @ResponseBody
//    public String list() {
//        return "创建订单视图";
//    }
//    @RequestMapping("/v1/orders/delete")
//    @ResponseBody
//    public String delete() {
//        return "删除订单";
//    }
//    @RequestMapping("/v1/orders/update")
//    @ResponseBody
//    public String update() {
//        return "更新订单成功";
//    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/v1/orders/add")
    public String add() {
        return "add";
    }

    @GetMapping("/v1/orders/list")
    public String list() {
        return "list";
    }

    @GetMapping("/v1/orders/delete")
    public String delete() {
        return "delete";
    }

    @GetMapping("/v1/orders/update")
    public String update() {
        return "update";
    }
}
