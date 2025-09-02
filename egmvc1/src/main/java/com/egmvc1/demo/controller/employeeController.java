package com.egmvc1.demo.controller;

import com.egmvc1.demo.pojo.dto.employee;
import com.egmvc1.demo.pojo.vo.employeeVO1;
import com.egmvc1.demo.pojo.vo.employeeVO2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class employeeController {
    @GetMapping("/emp/confirm/add")
    public String confirmAdd() {
        return "confirmAdd";
    }

    @PostMapping("/emp/add")
    public String add(employee employee) {
        System.out.println(employee);
        return "add";
    }

    @GetMapping("/emp/list")
    public String list() {
        return "list";
    }

    @GetMapping("/emp/updateInformationView")
    public String updateInformationView() {
        return "updateInformationView";
    }

    @PostMapping("/emp/updateInformationView/complete")
    public String updateInformationViewComplete(employeeVO1 employeeVO1) {
        System.out.println(employeeVO1);
        return "updateInformationViewComplete";
    }

    @GetMapping("/emp/delete/confirm")
    public String deleteEmployee() {
        return "deleteConfirm";
    }

    @PostMapping("/emp/delete/complete")
    public String deleteEmployeeComplete(employeeVO2 employeeVO2) {
        System.out.println("删除"+employeeVO2);
        return "deleteComplete";
    }
}
