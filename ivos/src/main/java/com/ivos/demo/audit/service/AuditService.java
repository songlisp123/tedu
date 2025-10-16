package com.ivos.demo.audit.service;

import com.ivos.demo.application.pojo.entity.Application;
import com.ivos.demo.audit.pojo.dto.AudioQuery;
import com.ivos.demo.audit.pojo.dto.AuditSavePara;
import com.ivos.demo.audit.pojo.vo.AuditVo;

import java.util.List;

public interface AuditService {
    void saveAudit(Application application);

    List<AuditVo> selectAuditByApplicationId(Long id);

    List<AuditVo> query(AudioQuery query);

    void update(AuditSavePara para);
}
