package com.ivos.demo.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ApplicationStatusEnum {
    PENDING("10","已发起"),
    CANCEL("20","撤销"),
    AUDIT("30","审核中"),
    REJECT("40","驳回"),
    AUDITED("50","已审核"),
    ALLOCATION("60","已分配用车"),
    END("70","工单结束");

    private String code;
    private String msg;
}
