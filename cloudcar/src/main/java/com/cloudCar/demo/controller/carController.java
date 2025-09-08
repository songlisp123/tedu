package com.cloudCar.demo.controller;

import com.cloudCar.demo.base.response.JsonResult;
import com.cloudCar.demo.pojo.DTO.UserRegParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/car/")
public class carController {
    @PostMapping("add")
    public JsonResult add() {
        return new JsonResult();
    }
    @PostMapping("login")
    public JsonResult login() {
        return JsonResult.ok();
    }
}
