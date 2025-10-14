package com.ivos.demo.application.controller;

import com.ivos.demo.application.service.ApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "申请单模块")
@RestController
@RequestMapping("/v1/app/")
public class ApplicationController {

    @Autowired
    private ApplicationService service;
}
