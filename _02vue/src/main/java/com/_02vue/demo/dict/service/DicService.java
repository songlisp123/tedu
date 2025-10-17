package com._02vue.demo.dict.service;

import com._02vue.demo.dict.pojo.dto.DIctSavePara;
import com._02vue.demo.dict.pojo.dto.DictQuery;
import com._02vue.demo.dict.pojo.vo.DictVo;

import java.util.List;

public interface DicService {
    List<DictVo> query(DictQuery query);

    void save(DIctSavePara para);

    void updateStatus(Long dictId, String status);

    void delete(Long dictId);
}
