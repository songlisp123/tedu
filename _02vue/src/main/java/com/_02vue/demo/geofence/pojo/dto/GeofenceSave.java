package com._02vue.demo.geofence.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeofenceSave {
    private String name;
    private String position;
}
