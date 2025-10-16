package com.ivos.demo.dicOptions.service;

import com.ivos.demo.dicOptions.pojo.dto.DicOptQuery;
import com.ivos.demo.dicOptions.pojo.dto.DictOptSavePara;
import com.ivos.demo.dicOptions.pojo.vo.DicOptVo;

import java.util.List;

public interface DicOptService {
    List<DicOptVo> query(DicOptQuery query);

    void save(DictOptSavePara para);

    void delete(Long dicOptId);

    List<DicOptVo> selectDictOptionByDictCode(String code);
}
