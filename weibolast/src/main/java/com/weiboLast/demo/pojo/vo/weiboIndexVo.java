package com.weiboLast.demo.pojo.vo;

import com.weiboLast.demo.pojo.entity.tag;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class weiboIndexVo {
    @Schema(description = "微博Id")
    private Long id;
    @Schema(description = "微博标题")
    private String title;
    @Schema(description = "微博正文")
    private String content;
    @Schema(description = "微博发布者")
    private String nickname;
    @Schema(description = "微博创建时间")
    private Date createTime;
    @Schema(description = "最后一次修改")
    private Date updateTime;
    @Schema(description = "文章标签")
    private List<tag> tags;
}
