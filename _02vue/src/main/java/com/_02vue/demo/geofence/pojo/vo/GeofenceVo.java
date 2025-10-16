package com._02vue.demo.geofence.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeofenceVo {
    private Long id;
    private String name;
    private String position;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS",timezone = "GMT+8")
    private Date createTime;
    private String status;
}
