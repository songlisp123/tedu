package com.weibo.demo.pojo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentAddParam {
    @NotBlank(message = "内容不能为空")
    private String content;
    @Positive(message = "输入参数错误")
    @Range(min = 200,message = "超出索引范围")
    private Long weiBoId;
}
