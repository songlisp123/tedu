package com.ivos.demo.audit.mapper;

import com.ivos.demo.audit.pojo.dto.AudioQuery;
import com.ivos.demo.audit.pojo.entity.Audit;
import com.ivos.demo.audit.pojo.vo.AuditVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditMapper {
    void save(Audit audit);

    List<AuditVo> selectAuditByApplicationId(Long id);

    void deleteAuditByApplicationId(Long id);

    List<AuditVo> query(AudioQuery query);

    void update(Audit audit);

    Integer selectRestAuditController(AudioQuery audioQuery);

    void delete(Long id);
}
