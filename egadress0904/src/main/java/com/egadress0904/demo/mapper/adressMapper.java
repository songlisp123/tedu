package com.egadress0904.demo.mapper;

import com.egadress0904.demo.pojo.VO.addressVO;
import com.egadress0904.demo.pojo.entity.Address;

import java.util.List;

public interface adressMapper {
    int insertAddress(Address address);

    List<addressVO> selectALL();

    int deleteById(Integer addressId);

    Integer[] sortAllId();
}
