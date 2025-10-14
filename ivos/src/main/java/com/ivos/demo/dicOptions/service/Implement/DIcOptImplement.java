package com.ivos.demo.dicOptions.service.Implement;

import com.ivos.demo.dicOptions.mapper.DictOptMapper;
import com.ivos.demo.dicOptions.pojo.dto.DicOptQuery;
import com.ivos.demo.dicOptions.pojo.vo.DicOptVo;
import com.ivos.demo.dicOptions.service.DicOptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
