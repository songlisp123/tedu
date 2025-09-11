package com.weiboLast.demo.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.weiboLast.demo.base.response.JsonResult;
import com.weiboLast.demo.base.response.StatusCode;
import com.weiboLast.demo.mapper.commentMapper;
import com.weiboLast.demo.pojo.dto.CommentAddParam;
import com.weiboLast.demo.pojo.entity.comment;
import com.weiboLast.demo.pojo.vo.UserVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.logging.Logger;

@Slf4j
@Tag(name = "03评论模块",description = "03评论模块")
@RestController
@RequestMapping("/v1/comment/")
public class commentController {
    @Autowired
    private commentMapper commentMapper;
    private static final Logger logger = Logger.getLogger("weibo");

    @PostMapping("insert")
    @Operation(summary = "发表评论")
    @ApiOperationSupport(order = 100)
    public JsonResult insert(
            @RequestBody CommentAddParam commentAddParam,
            HttpSession session)
    {
        UserVO userVO = (UserVO) session.getAttribute("user");
        logger.info("评论文章，当前用户:%s".formatted(userVO.getUsername()));
        Long userId = userVO.getId();
        comment comment = new comment();
        BeanUtils.copyProperties(commentAddParam,comment);
        comment.setUserId(userId);
        comment.setCreateTime(new Date());
        int i = commentMapper.insertComment(comment);
        if (i>0) {
            logger.info("评论成功！");
            return JsonResult.ok();
        }
        logger.info("发生未知错误！评论不成功！");
        return new JsonResult(StatusCode.OPERATION_ERROR);
    }

    @GetMapping("del/{id}")
    @Operation(summary = "删除微博评论")
    @ApiOperationSupport(order = 200)
    public JsonResult del(
            @Schema(description = "评论id",example = "500")
            @PathVariable Long id,
            HttpSession session)
    {
        logger.info("删除ID号为[%d]的文章".formatted(id));
        UserVO user = (UserVO) session.getAttribute("user");
        Long userId = user.getId();
        int i = commentMapper.deleteById(id,userId);
        if (i>0) {
            logger.info("删除成功！");
            return JsonResult.ok();
        }
        logger.info("删除失败！请重试！");
        return new JsonResult(StatusCode.OPERATION_ERROR);
    }
}
