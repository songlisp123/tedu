package com.ivos.demo.application.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationSavePara {
    private Long id;
    private Long userId;
    private String username;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    private String imgUrl;
    private String departureAddr;
    private String destinationAddr;
    private String reason;
    private String remark;
    private String status;
    private Long vehicleId;
    private String rejectReason;

    /* 瞬态属性 审核人id列表 */

    private List<Long> auditUserIdList;

}
