package com.eg1.demo.controller;

import com.eg1.demo.pojo.DTO.BmiDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BMIcontroller {

    @GetMapping("/bmi/confirm")
    public String test()  {
        return "bmiTemplates/bmi";
    }

    @PostMapping("/bmi")
    public String bmi(BmiDTO bmiDTO) {
        double height = bmiDTO.getHeight();;
        double weight = bmiDTO.getWeight();
        double result = weight / (height*height);
        String answer;
        if (result>=27)
            answer = "该减肥了";
        else if (result>24)
            answer = "偏胖";
        else if (result>18.5)
            answer = "正常";
        else
            answer = "偏瘦";
        return "bmiTemplates/bmiCalucator";
    }
}
