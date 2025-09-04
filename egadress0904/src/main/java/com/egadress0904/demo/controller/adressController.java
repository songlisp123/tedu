package com.egadress0904.demo.controller;

import com.egadress0904.demo.mapper.adressMapper;
import com.egadress0904.demo.pojo.DTO.addressDTO;
import com.egadress0904.demo.pojo.entity.Address;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/address/")
public class adressController {

    @Autowired
    private adressMapper adressMapper;

    @PostMapping("add/")
    public String add(@RequestBody addressDTO addressDTO) {
        Address address = new Address();
        BeanUtils.copyProperties(addressDTO,address);
        int  i = adressMapper.insertAddress(address);
        if (i > 0)
            return "插入成功";
        return "插入失败";
    }
}
