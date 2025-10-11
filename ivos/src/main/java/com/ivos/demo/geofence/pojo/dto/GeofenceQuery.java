package com.ivos.demo.geofence.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeofenceQuery {

    private Long id;
    private String name;
    private String status;
}
