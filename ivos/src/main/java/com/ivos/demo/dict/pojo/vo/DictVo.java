package com.ivos.demo.dict.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GTM+8")
    private Date createTime;

}
