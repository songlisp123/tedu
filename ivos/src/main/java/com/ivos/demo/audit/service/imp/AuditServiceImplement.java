package com.ivos.demo.audit.service.imp;

import com.ivos.demo.application.mapper.ApplicationMapper;
import com.ivos.demo.application.pojo.entity.Application;
import com.ivos.demo.audit.mapper.AuditMapper;
import com.ivos.demo.audit.pojo.dto.AudioQuery;
import com.ivos.demo.audit.pojo.dto.AuditSavePara;
import com.ivos.demo.audit.pojo.entity.Audit;
import com.ivos.demo.audit.pojo.vo.AuditVo;
import com.ivos.demo.audit.service.AuditService;
import com.ivos.demo.base.enums.ApplicationStatusEnum;
import com.ivos.demo.base.enums.AuditStatusEnum;
import com.ivos.demo.user.mapper.userMapper;
import com.ivos.demo.user.pojo.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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

    @Autowired
    private ApplicationMapper applicationMapper;

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

    @Override
    public void update(AuditSavePara para) {

        /****************** 修改当前审批单（通过、驳回） **************/
        log.debug("业务层参数:{}",para);
        Audit audit = new Audit();
        BeanUtils.copyProperties(para,audit);
        audit.setUpdateTime(new Date());
        mapper.update(audit);

        //修改审核单的申请单
        Application application = new Application();
        application.setId(para.getApplicationId());
        application.setUpdateTime(new Date());
        //通过或者驳回
        if (audit.getAuditStatus().equals(AuditStatusEnum.AUDITED.getCode()))
        {
            //通过申请
            AudioQuery audioQuery = new AudioQuery();
            audioQuery.setApplicationId(audit.getApplicationId());
            Integer count = mapper.selectRestAuditController(audioQuery);

            if (count > 0) {
                //还存在需要审批的审批单
                //将审批单顺序增加1
                audioQuery.setAuditSort(audit.getAuditSort()+1);
                List<AuditVo> query = mapper.query(audioQuery);
                //查找下一级申请单并更新审批单
                if (query != null && query.size() > 0) {
                    //创建第二个审批对象，更新第二个审批单
                    Audit audit2 = new Audit();
                    AuditVo auditVo = query.get(0);
                    audit2.setId(auditVo.getId());
                    audit2.setAuditStatus(AuditStatusEnum.MY_PENDING.getCode());
                    audit2.setUpdateTime(new Date());
                    mapper.update(audit2);
                }
                //更新申请单状态，由于还有上级领导尚未审批因此需要设置为审核中
                application.setStatus(ApplicationStatusEnum.AUDIT.getCode());
                applicationMapper.update(application);
            }
            else  {
                //没有未审核的审批单，表示审核单都已经通过，申请单将设置为审核完毕
                application.setStatus(ApplicationStatusEnum.AUDITED.getCode());
                applicationMapper.update(application);
            }



        }
        else {
            //驳回申请
            //设置一个审批单查询对象，遍历所有的申请对象
            AudioQuery audioQuery = new AudioQuery();
            audioQuery.setApplicationId(audit.getApplicationId());
            List<AuditVo> query = mapper.query(audioQuery);
            for (int i = 0;i<query.size();i++) {
                AuditVo auditVo = query.get(i);
                if (auditVo.getAuditStatus().equals(AuditStatusEnum.PENDING.getCode())) {
                    mapper.delete(auditVo.getId());
                }
            }
            application.setStatus(ApplicationStatusEnum.REJECT.getCode());
            application.setRejectReason(audit.getRejectReason());
            applicationMapper.update(application);
        }

//        applicationMapper.update(application);
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
            Long userId = auditVo1.getAuditUserId();

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
