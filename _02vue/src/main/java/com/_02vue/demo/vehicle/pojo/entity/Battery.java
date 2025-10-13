package com._02vue.demo.vehicle.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum Battery {

    ok("10","铅酸锂电池"),
    OK("20","锂电池");

    private String code;
    private String message;
}
