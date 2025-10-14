package com.ivos.demo.dicOptions.service.Implement;

import com.ivos.demo.dicOptions.mapper.DictOptMapper;
import com.ivos.demo.dicOptions.pojo.dto.DicOptQuery;
import com.ivos.demo.dicOptions.pojo.dto.DictOptSavePara;
import com.ivos.demo.dicOptions.pojo.entity.DictOpt;
import com.ivos.demo.dicOptions.pojo.vo.DicOptVo;
import com.ivos.demo.dicOptions.service.DicOptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class DIcOptImplement implements DicOptService {

    @Autowired
    private DictOptMapper mapper;

    @Override
    public List<DicOptVo> query(DicOptQuery query) {
        log.debug("业务层参数:{}",query);
         return mapper.query(query);

    }

    @Override
    public void save(DictOptSavePara para) {
        log.debug("业务层参数,{}",para);
        DictOpt dIcOpt = new DictOpt();
        BeanUtils.copyProperties(para,dIcOpt);
        if (dIcOpt.getId() != null) {
            dIcOpt.setUpdateTime(new Date());

        }
        else {
            dIcOpt.setCreateTime(new Date());
            mapper.save(dIcOpt);
        }
    }
}
