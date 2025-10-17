package com._02vue.demo.dicOpt.service;

import com._02vue.demo.dicOpt.pojo.dto.DicOptSave;
import com._02vue.demo.dicOpt.pojo.dto.DictOptQuery;
import com._02vue.demo.dicOpt.pojo.vo.DictOptVo;
import com._02vue.demo.dict.pojo.dto.DictQuery;

import java.util.List;

public interface DictOptService {
    List<DictOptVo> query(DictOptQuery query);

    void save(DicOptSave saved);

    void delete(Long optionID);
}
