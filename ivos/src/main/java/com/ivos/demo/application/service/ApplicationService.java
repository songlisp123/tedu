package com.ivos.demo.application.service;


import com.ivos.demo.application.pojo.dto.ApplicationQuery;
import com.ivos.demo.application.pojo.dto.ApplicationSavePara;
import com.ivos.demo.application.pojo.vo.ApplicationVo;

import java.util.List;

public interface ApplicationService {
    void save(ApplicationSavePara para);

    List<ApplicationVo> query(ApplicationQuery query);

    void cancel(Long id);

    void update(Long applicationId, Long vehicleId);

    void back(Long applicationId, Long vehicleId);
}
