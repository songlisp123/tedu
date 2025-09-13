package com.weiboLast.demo.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class weiBoDetailVO extends weiboIndexVo {
    @Schema(description = "这是一个评论列表")
    private List<commentVO> comments;
}
