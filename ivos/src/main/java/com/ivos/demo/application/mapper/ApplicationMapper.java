package com.ivos.demo.application.mapper;

import com.ivos.demo.application.pojo.dto.ApplicationQuery;
import com.ivos.demo.application.pojo.entity.Application;
import com.ivos.demo.application.pojo.vo.ApplicationVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationMapper {
    void save(Application application);

    List<ApplicationVo> query(ApplicationQuery query);

    void update(Application id);

    void back(Application application);
}
