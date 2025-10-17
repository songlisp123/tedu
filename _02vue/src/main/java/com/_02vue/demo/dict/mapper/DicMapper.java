package com._02vue.demo.dict.mapper;

import com._02vue.demo.dict.pojo.dto.DictQuery;
import com._02vue.demo.dict.pojo.entity.Dict;
import com._02vue.demo.dict.pojo.vo.DictVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DicMapper {
    List<DictVo> query(DictQuery query);

    void save(Dict dict);

    void update(Dict dict);

    void delete(Long dictId);
}
