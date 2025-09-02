package com.egmvc2.demo.controller;

import com.egmvc2.demo.pojo.dto.addressInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddressController {
    /**
     * 添加地址的操作
     * @return {@code add}视图函数
     */
    @GetMapping("/adress/add")
    public String addAddress() {
        return "add";
    }

    /**
     * 添加地址后的重定向操作
     * @param addressInfo 对象视图类，封装了{@code addressInfo}的属性
     * @return {@code addComplete}的视图模板
     */
    @PostMapping("/adress/add/complete")
    public String addAddressComplete(addressInfo addressInfo) {
        System.out.println(addressInfo);
        return "addComplete";
    }




}
