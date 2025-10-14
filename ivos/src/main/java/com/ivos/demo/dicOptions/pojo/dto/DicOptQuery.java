package com.ivos.demo.dicOptions.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DicOptQuery {
    private Long dictId;
    private Long id;
    private String label;
}
