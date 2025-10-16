package com.ivos.demo.geofence.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ivos.demo.vehicle.pojo.vo.VehicleVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeofenceVo {
    private Long id;
    private String name;
    private String status;
    private String position;
    private Integer totalNum ;
    private Integer availableNum ;
    private List<VehicleVo> voList;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
}
