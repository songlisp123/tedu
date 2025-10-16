package com.ivos.demo.application.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationQuery {
    private Long id;
    private String departureAddr;
    private String destinationAddr;
    private String status;
    private String username;
}
