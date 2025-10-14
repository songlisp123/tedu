package com.ivos.demo.dicOptions.service;

import com.ivos.demo.dicOptions.pojo.dto.DicOptQuery;
import com.ivos.demo.dicOptions.pojo.vo.DicOptVo;

import java.util.List;

public interface DicOptService {
    List<DicOptVo> query(DicOptQuery query);
}
