package com._02vue.demo.dicOpt.mapper;

import com._02vue.demo.dicOpt.pojo.dto.DictOptQuery;
import com._02vue.demo.dicOpt.pojo.entity.DictOpt;
import com._02vue.demo.dicOpt.pojo.vo.DictOptVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DictOptMapper {
    List<DictOptVo> query(DictOptQuery query);

    void insert(DictOpt dictOpt);

    void update(DictOpt dictOpt);

    void delete(Long optionID);
}
