package com.ivos.demo.audit.service.imp;

import com.ivos.demo.application.mapper.ApplicationMapper;
import com.ivos.demo.application.pojo.entity.Application;
import com.ivos.demo.audit.mapper.AuditMapper;
import com.ivos.demo.audit.pojo.dto.AudioQuery;
import com.ivos.demo.audit.pojo.entity.Audit;
import com.ivos.demo.audit.pojo.vo.AuditVo;
import com.ivos.demo.audit.service.AuditService;
import com.ivos.demo.base.enums.AuditStatusEnum;
import com.ivos.demo.user.mapper.userMapper;
import com.ivos.demo.user.pojo.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

@Transactional
@Slf4j
@Service
public class AuditServiceImplement implements AuditService {

    @Autowired
    private AuditMapper mapper;

    @Autowired
    private userMapper userMapper;

    @Override
    public void saveAudit(Application application) {
        log.debug("审核单业务层参数:{}",application);
        List<Long> auditUserIdList = application.getAuditUserIdList();

        for (int i=0;i<auditUserIdList.size();i++) {
            Audit audit = new Audit();
            audit.setApplicationId(application.getId());
            audit.setAuditUserId(auditUserIdList.get(i));
            audit.setAuditSort(i);
            if (i == 0) {
                audit.setAuditStatus(AuditStatusEnum.MY_PENDING.getCode());
            }
            else {
                audit.setAuditStatus(AuditStatusEnum.PENDING.getCode());
            }
            audit.setCreateTime(new Date());
            mapper.save(audit);

        }

    }

    @Override
    public List<AuditVo> selectAuditByApplicationId(Long id) {
        //注意这个id是申请单id
        log.debug("业务层参数:{}",id);
        List<AuditVo> list =
                mapper.selectAuditByApplicationId(id);
        return list;
    }

    @Override
    public List<AuditVo> query(AudioQuery query) {
        log.debug("业务层参数:{}",query);
        List<AuditVo> list = mapper.query(query);
        for (int i = 0;i<list.size();i++) {
            //获取到每个auditvo
            AuditVo auditVo = list.get(i);
            assignAuditUserList(auditVo);
        }
        return list;
    }

    private void assignAuditUserList(AuditVo auditVo) {
        List<Long> auditUserIdList = new ArrayList<>();
        List<String> auditUsernameList = new ArrayList<>();

        //获取到audiovo中的申请单id
        List<AuditVo> list =
                mapper.selectAuditByApplicationId(auditVo.getApplicationId());

        //遍历寻找到的auditvo
        for (int i=0;i<list.size();i++) {
            AuditVo auditVo1 = list.get(i);
            Long userId = auditVo1.getUserId();

            UserVo userVo = userMapper.selectById(userId);
            auditUserIdList.add(userId);

            auditUsernameList.add(userVo.getUsername());
        }

        StringJoiner joiner = new StringJoiner(",");

        for (String username : auditUsernameList) {
            joiner.add(username);
        }

        auditVo.setAuditUserIdList(auditUserIdList);
        auditVo.setAuditUsernameList(joiner.toString());

    }
}
