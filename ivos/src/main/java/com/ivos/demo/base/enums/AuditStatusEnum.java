package com.ivos.demo.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum AuditStatusEnum {
    MY_PENDING("10","待我审核"),
    PENDING("20","待他人审核"),
    AUDITED("30","已审核"),
    REJECT("40","驳回");

    private String code;
    private String msg;
}
