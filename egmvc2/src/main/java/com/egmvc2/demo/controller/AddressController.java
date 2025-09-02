package com.egmvc2.demo.controller;

import com.egmvc2.demo.pojo.dto.addressInfo;
import com.egmvc2.demo.pojo.vo.adressInfoVO1;
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

    /**
     * 查询所有的地址信息的函数
     * @return {@code list}s视图模板
     */
    @GetMapping("/adress/list")
    public String list() {
        System.out.println("打印出所有的信息列表");
        return "list";
    }

    /**
     * 删除信息的确认视图，只接受GET请求
     * @return {@code confirm}视图模板
     */
    @GetMapping("/adress/delete/confirm")
    public String delete_confirm() {
        return "confirm";
    }

    /**
     * 删除地址信息后的重定向函数，只接受POST请求
     * @param id 地址id，用来标识地址信息
     * @return {@code delete_complete}视图模板
     */
    @PostMapping("/adress/delete/complete")
    public String deleteComplete(Integer id) {
        System.out.println("删除成功");
        return "delete_complete";
    }

    /**
     * 更新确认视图函数，只接受GET请求
     * @return {@code update_confirm}视图模板
     */
    @GetMapping("/adress/update/confirm")
    public String updateConfirm() {
        return "update_confirm";
    }

    /**
     * 更新试图后重定向视图，只接受POST请求
     * @param adressInfoVO1 {@code addressInfo}的视图类，用来封装部分属性
     * @return {@code update_complete}视图模板
     */
    @PostMapping("/adress/update/complete")
    public String updateComplete(adressInfoVO1 adressInfoVO1) {
        System.out.println(adressInfoVO1);
        return "update_complete";
    }


}
