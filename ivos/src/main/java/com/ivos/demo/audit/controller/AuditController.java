package com.ivos.demo.audit.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "审核单模块")
@RestController
@RequestMapping("/v1/audit/")
public class AuditController {
}
