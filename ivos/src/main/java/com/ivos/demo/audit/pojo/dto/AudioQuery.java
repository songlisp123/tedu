package com.ivos.demo.audit.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AudioQuery {
    @Schema(description = "审核单id")
    private Long id;
    @Schema(description = "申请人姓名")
    private String username;
    @Schema(description = "审核单状态")
    private Integer auditStatus;
    @Schema(description = "审核用户id")
    private Long auditUserId;
    @Schema(description = "申请单id")
    private Long applicationId;
    @Schema(description = "审核单排序")
    private Integer auditSort;

}
