package com._02vue.demo.dict.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DictVo {
    private Long id;
    private String name;
    private String code;
    private String remark;
    private String status;
    private Date createTime;
}
