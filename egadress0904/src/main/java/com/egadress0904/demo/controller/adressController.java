package com.egadress0904.demo.controller;

import com.egadress0904.demo.mapper.adressMapper;
import com.egadress0904.demo.pojo.DTO.addressDTO;
import com.egadress0904.demo.pojo.VO.addressVO;
import com.egadress0904.demo.pojo.entity.Address;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("list/")
    public List<addressVO> list() {
        return adressMapper.selectALL();
    }

    @PostMapping("del/{addressId}/")
    public String del(@PathVariable Long addressId) {
        int num = adressMapper.deleteById(addressId);
        if (num >0)
            return "删除成功！";
        return "删除失败";
    }
}
