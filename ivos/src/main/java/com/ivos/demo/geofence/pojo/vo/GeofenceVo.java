package com.ivos.demo.geofence.pojo.vo;

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
    private String status;
    private String position;
    private Date createTime;
    private Date updateTime;
}
