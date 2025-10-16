package com.ivos.demo.dicOptions.service.Implement;

import com.ivos.demo.base.exception.serviceException;
import com.ivos.demo.base.response.StatusCode;
import com.ivos.demo.dicOptions.mapper.DictOptMapper;
import com.ivos.demo.dicOptions.pojo.dto.DicOptQuery;
import com.ivos.demo.dicOptions.pojo.dto.DictOptSavePara;
import com.ivos.demo.dicOptions.pojo.entity.DictOpt;
import com.ivos.demo.dicOptions.pojo.vo.DicOptVo;
import com.ivos.demo.dicOptions.service.DicOptService;
import com.ivos.demo.dict.mapper.DictMapper;
import com.ivos.demo.dict.pojo.dto.DictQuery;
import com.ivos.demo.dict.pojo.vo.DictVo;
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

    @Autowired
    private DictMapper dictMapper;

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
            mapper.update(dIcOpt);
        }
        else {
            dIcOpt.setCreateTime(new Date());
            mapper.save(dIcOpt);
        }
    }

    @Override
    public void delete(Long dicOptId) {
        log.debug("业务员参数：{}",dicOptId);
        mapper.delete(dicOptId);
    }

    @Override
    public List<DicOptVo> selectDictOptionByDictCode(String code) {
        log.debug("参数:{}",code);
        DictQuery dictQuery = new DictQuery();
        dictQuery.setCode(code);
        List<DictVo> query = dictMapper.query(dictQuery);
        if (query != null || query.size()>0) {
            DictVo dictVo = query.get(0);
            //设置新的dictopt查询参数
            DicOptQuery dicOptQuery = new DicOptQuery();
            dicOptQuery.setDictId(dictVo.getId());
            return mapper.query(dicOptQuery);
        }
        else  {
            throw new serviceException(StatusCode.DATA_UNEXISTS);
        }
    }
}
