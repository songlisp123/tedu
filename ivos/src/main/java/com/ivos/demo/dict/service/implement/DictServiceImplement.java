package com.ivos.demo.dict.service.implement;

import com.ivos.demo.dict.pojo.dto.DictRegPara;
import com.ivos.demo.dict.mapper.DictMapper;
import com.ivos.demo.dict.pojo.dto.DictQuery;
import com.ivos.demo.dict.pojo.entity.Dict;
import com.ivos.demo.dict.pojo.vo.DictVo;
import com.ivos.demo.dict.service.DictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class DictServiceImplement implements DictService {

    @Autowired
    private DictMapper mapper;

    @Override
    public List<DictVo> query(DictQuery query) {
        log.debug("业务层参数:{}",query);
        List<DictVo> list = mapper.query(query);
        return list;
    }

    @Override
    public void save(DictRegPara para) {
        log.debug("业务层参数{}",para);
        Dict dict = new Dict();
        BeanUtils.copyProperties(para,dict);
        if(dict.getId() != null ) {
            dict.setUpdateTime(new Date());
            mapper.update(dict);
        }
        else {
            dict.setCreateTime(new Date());
            dict.setStatus("1");
            mapper.save(dict);
        }
    }

    @Override
    public void delete(Long dictId) {
        log.debug("业务层参数:{}",dictId);
        mapper.delete(dictId);
    }
}
