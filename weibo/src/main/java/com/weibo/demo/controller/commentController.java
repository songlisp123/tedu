package com.weibo.demo.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.weibo.demo.base.response.JsonResults;
import com.weibo.demo.base.response.StatusCode;
import com.weibo.demo.mapper.commentMapper;
import com.weibo.demo.pojo.dto.CommentAddParam;
import com.weibo.demo.pojo.entity.comment;
import com.weibo.demo.pojo.vo.CommentVO;
import com.weibo.demo.pojo.vo.UserVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/comment/")
@Tag(name = "03评论模块")
public class commentController {

    @Autowired
    private commentMapper commentMapper;

    @PostMapping("insert")
    @Operation(summary = "差如评论",description = "插入评论")
    @ApiOperationSupport(order = 100)
    public JsonResults add(
            @RequestBody CommentAddParam commentAddParam,
            HttpSession session)
    {
        UserVo user = (UserVo) session.getAttribute("user");
        if (user==null)
            return new JsonResults(StatusCode.NOT_LOGIN);
        comment comment = new comment();
        BeanUtils.copyProperties(commentAddParam,comment);
        comment.setCreateTime(new Date());
        comment.setUserId(user.getId());
        int i = commentMapper.insertComment(comment);
        if (i>0)
            return JsonResults.ok();
        return new JsonResults(StatusCode.OPERATION_FAILED);
    }

    @GetMapping("selectByWeiboId")
    @Operation(summary = "查询评论",description = "此函式查询评论")
    @ApiOperationSupport(order = 300)
    public JsonResults list(Long id)
    {
        List<CommentVO> list = commentMapper.selectAllcomments(id);
        return JsonResults.ok(list);
    }
}
