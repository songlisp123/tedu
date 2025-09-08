package com.cloudCar.demo.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class noticeVO {

    private Long id;
    private Long title;
    private String content;
    private Integer status;
    private Integer type;
    private Date createTime;

}
