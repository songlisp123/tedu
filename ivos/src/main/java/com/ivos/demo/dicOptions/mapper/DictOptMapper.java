package com.ivos.demo.dicOptions.mapper;

import com.ivos.demo.dicOptions.pojo.dto.DicOptQuery;
import com.ivos.demo.dicOptions.pojo.entity.DictOpt;
import com.ivos.demo.dicOptions.pojo.vo.DicOptVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DictOptMapper {
    List<DicOptVo> query(DicOptQuery query);

    void save(DictOpt dIcOpt);
}
