package com._02vue.demo.dict.service.impl;

import com._02vue.demo.dict.mapper.DicMapper;
import com._02vue.demo.dict.pojo.dto.DIctSavePara;
import com._02vue.demo.dict.pojo.dto.DictQuery;
import com._02vue.demo.dict.pojo.entity.Dict;
import com._02vue.demo.dict.pojo.vo.DictVo;
import com._02vue.demo.dict.service.DicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class DictServiceImplement implements DicService {

    @Autowired
    private DicMapper dicMapper;

    @Override
    public List<DictVo> query(DictQuery query) {
        log.debug("业务层参数;{}",query);
        int pageSize = query.getPageSize();
        int currentPage = query.getCurrentPage();
        query.setCurrentPage((currentPage-1)*pageSize);
        return dicMapper.query(query);
    }

    @Override
    public void save(DIctSavePara para) {
        log.debug("业务层参数:{}",para);
        Dict dict = new Dict();
        BeanUtils.copyProperties(para,dict);
        if (dict.getId() !=null) {
            dict.setUpdateTime(new Date());
            dicMapper.update(dict);
        }
        else {
            dict.setStatus("1");
            dict.setCreateTime(new Date());
            dicMapper.save(dict);
        }
    }

    @Override
    public void updateStatus(Long dictId, String status) {
        log.debug("业务层参数：{}+{}",dictId,status);
        Dict dict = new Dict();
        dict.setId(dictId);
        dict.setStatus(status);
        dict.setUpdateTime(new Date());
        dicMapper.update(dict);
    }

    @Override
    public void delete(Long dictId) {
        log.debug("业务层参数");
        dicMapper.delete(dictId);
    }
}
