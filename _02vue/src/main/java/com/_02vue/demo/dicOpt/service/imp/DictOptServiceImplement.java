package com._02vue.demo.dicOpt.service.imp;

import com._02vue.demo.dicOpt.mapper.DictOptMapper;
import com._02vue.demo.dicOpt.pojo.dto.DicOptSave;
import com._02vue.demo.dicOpt.pojo.dto.DictOptQuery;
import com._02vue.demo.dicOpt.pojo.entity.DictOpt;
import com._02vue.demo.dicOpt.pojo.vo.DictOptVo;
import com._02vue.demo.dicOpt.service.DictOptService;
import com._02vue.demo.dict.pojo.dto.DictQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class DictOptServiceImplement implements DictOptService {

    @Autowired
    private DictOptMapper dictOptMapper;

    @Override
    public List<DictOptVo> query(DictOptQuery query) {
        log.debug("业务层参数:{}",query);
        return dictOptMapper.query(query);
    }

    @Override
    public void save(DicOptSave saved) {
        log.debug("业务层参数:{}",saved);
        DictOpt dictOpt = new DictOpt();
        BeanUtils.copyProperties(saved,dictOpt);
        if (dictOpt.getId() != null ){
            dictOpt.setUpdateTime(new Date());
            dictOptMapper.update(dictOpt);
        }
        else {
            dictOpt.setCreateTime(new Date());
            dictOptMapper.insert(dictOpt);
        }
    }

    @Override
    public void delete(Long optionID) {
        log.debug("业务层参数：{}",optionID);
        dictOptMapper.delete(optionID);
    }
}
