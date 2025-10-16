package com.ivos.demo.application.service.implement;

import com.ivos.demo.application.mapper.ApplicationMapper;
import com.ivos.demo.application.pojo.dto.ApplicationQuery;
import com.ivos.demo.application.pojo.dto.ApplicationSavePara;
import com.ivos.demo.application.pojo.entity.Application;
import com.ivos.demo.application.pojo.vo.ApplicationVo;
import com.ivos.demo.application.service.ApplicationService;
import com.ivos.demo.audit.mapper.AuditMapper;
import com.ivos.demo.audit.pojo.vo.AuditVo;
import com.ivos.demo.audit.service.AuditService;
import com.ivos.demo.base.enums.ApplicationStatusEnum;
import com.ivos.demo.user.mapper.userMapper;
import com.ivos.demo.user.pojo.vo.UserVo;
import com.ivos.demo.vehicle.mapper.vehicleMapper;
import com.ivos.demo.vehicle.pojo.entity.Vehicle;
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
public class ApplicationServiceImplement implements ApplicationService {

    @Autowired
    private ApplicationMapper mapper;

    @Autowired
    private AuditService service;

    @Autowired
    private AuditMapper auditMapper;

    @Autowired
    private userMapper userMapper;

    @Autowired
    private vehicleMapper vehicleMapper;;

    @Override
    public void save(ApplicationSavePara para) {
        log.debug("业务层参数：{}",para);
        Application application = new Application();
        BeanUtils.copyProperties(para,application);
//        application
        application.setCreateTime(new Date());
        application.setStatus(ApplicationStatusEnum.PENDING.getCode());
        mapper.save(application);

        //还需要生成对应的审核单
        service.saveAudit(application);
    }

    @Override
    public List<ApplicationVo> query(ApplicationQuery query) {
        log.debug("业务层参数:{}",query);
        List<ApplicationVo> query1 = mapper.query(query);
        for (int i=0;i<query1.size();i++) {
            ApplicationVo applicationVo = query1.get(i);
            assignAuditUserList(applicationVo);
        }
        return query1;
    }

    @Override
    public void cancel(Long id) {
        log.debug("业务层参数:{}",id);
        Application application = new Application();
        application.setId(id);
        application.setStatus(ApplicationStatusEnum.CANCEL.getCode());
        application.setUpdateTime(new Date());
        mapper.update(application);

        //除此之外，还应该有删除此申请单对应的所有审批单
        auditMapper.deleteAuditByApplicationId(id);

    }

    private void assignAuditUserList(ApplicationVo applicationVo) {
        //新建数组保存审批人的id列表
        List<Long> auditUserIdList = new ArrayList<>();
        //新建数组保存审批人姓名列表
        List<String> auditUsernameList = new ArrayList<>();


        List<AuditVo> voList =
                service.selectAuditByApplicationId(applicationVo.getId()); //这一步删除审核单

        for (int i =0 ;i<voList.size();i++) {
            AuditVo auditVo = voList.get(i);
            Long auditUserId = auditVo.getAuditUserId();
            auditUserIdList.add(auditUserId);
            UserVo userVo = userMapper.selectById(auditUserId);
            auditUsernameList.add(userVo.getUsername());
        }
        StringJoiner joiner = new StringJoiner(",");
        for (String s : auditUsernameList) {
            joiner.add(s);
        }

        applicationVo.setAuditUserIdList(auditUserIdList);
        applicationVo.setAuditUsernameList(joiner.toString());


    }

    @Override
    public void update(Long applicationId, Long vehicleId) {
        log.debug("业务层参数：申请单id={}，汽车id={}",applicationId,vehicleId);
        Application application = new Application();
        application.setId(applicationId);
        application.setVehicleId(vehicleId);
        application.setStatus(ApplicationStatusEnum.ALLOCATION.getCode());
        application.setUpdateTime(new Date());
        mapper.update(application);

        //更新汽车
        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleId);
        vehicle.setUpdateTime(new Date());
        vehicle.setStatus("2");
        vehicleMapper.update(vehicle);

    }

    @Override
    public void back(Long applicationId, Long vehicleId) {
        log.debug("业务层参数:申请单id={}，汽车id={}",applicationId,vehicleId);
        Application application = new Application();
        application.setId(applicationId);
        application.setUpdateTime(new Date());
        application.setStatus(ApplicationStatusEnum.END.getCode());
        application.setVehicleId(null);
        mapper.back(application);


        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleId);
        vehicle.setStatus("1");
        vehicle.setUpdateTime(new Date());
        vehicleMapper.update(vehicle);
    }
}
