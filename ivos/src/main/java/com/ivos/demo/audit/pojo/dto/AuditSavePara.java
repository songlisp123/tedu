package com.ivos.demo.audit.pojo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AuditSavePara {
    private Long id;//审批单id
    private Long applicationId;//申请单id
    private Long auditUserId;//审批人id
    private String auditStatus;//审批状态
    private Integer auditSort;//审批排序
    private String rejectReason;//驳回原因

}
