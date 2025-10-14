package com.ivos.demo.dict.mapper;

import com.ivos.demo.dict.pojo.dto.DictQuery;
import com.ivos.demo.dict.pojo.entity.Dict;
import com.ivos.demo.dict.pojo.vo.DictVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DictMapper {
    List<DictVo> query(DictQuery query);

    void save(Dict dict);

    void update(Dict dict);

    void delete(Long dictId);
}
