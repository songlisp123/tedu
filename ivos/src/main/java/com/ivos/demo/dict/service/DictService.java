package com.ivos.demo.dict.service;

import com.ivos.demo.dict.pojo.dto.DictRegPara;
import com.ivos.demo.dict.pojo.dto.DictQuery;
import com.ivos.demo.dict.pojo.vo.DictVo;

import java.util.List;

public interface DictService {
    List<DictVo> query(DictQuery query);

    void save(DictRegPara para);

    void delete(Long dictId);
}
