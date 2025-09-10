package com._06article.demo.controller;

import com._06article.demo.base.response.JsonResults;
import com._06article.demo.base.response.StatusCode;
import com._06article.demo.mapper.userMapper;
import com._06article.demo.pojo.DTO.UserArticleParam;
import com._06article.demo.pojo.DTO.UserLoginParam;
import com._06article.demo.pojo.VO.articleVO;
import com._06article.demo.pojo.VO.userVo;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@Tag(name = "01用户管理模块")
@RestController
@RequestMapping("/v1/user/")
public class userController {

    @Autowired
    private userMapper userMapper;

    @PostMapping("login")
    @Operation(summary = "进行用户登录")
    @ApiOperationSupport(order = 100)
    public JsonResults login(
           @RequestBody UserLoginParam userLoginParam,
           HttpSession session
    ) {
        log.debug("userLoginParam:"+userLoginParam);
        userVo userVO = userMapper.selectUser(userLoginParam);
        if (userVO == null) {
            log.debug("用户不存在");
            return new JsonResults(StatusCode.USER_PASSWORD_ERROR);
        }
        session.setAttribute("user",userVO);
        return JsonResults.ok(userVO);
    }

    @PostMapping("article")
    @Operation(summary = "发表文章")
    @ApiOperationSupport(order = 200)
    public JsonResults put(
            @RequestBody @Validated UserArticleParam userArticleParam,
            HttpSession session)
    {
        userVo userVo = (userVo) session.getAttribute("user");
        //获取用户ID
        Long userId = userVo.getId();
        int i =
                userMapper.insertArticle(userArticleParam,userId);
        if (i>0)
            return JsonResults.ok();
        return new JsonResults(StatusCode.OPERATION_FAILED);
    }

    @GetMapping("selectByArticleId")
    @Operation(summary = "查询指定文章")
    @ApiOperationSupport(order = 300)
    public JsonResults selectByArticleId(
            @Range(min = 1,max = 100,message = "超出文章范围")
            @Schema(description = "文章id",required = true,example = "1")
            Long id
    )
    {
        articleVO a = userMapper.selectArticleById(id);
        if (a==null)
            return new JsonResults(StatusCode.ARTICLE_NOT_EXISTS);
        return JsonResults.ok(a);
    }

    @GetMapping("del/{id}")
    @Operation(summary = "删除指定ID的文章")
    @ApiOperationSupport(order = 400)
    public JsonResults del(
            @PathVariable
            @Range(min = 1,max = 100,message = "超出文章范围")
            @Schema(description = "文章id",example = "1")
            Long id,
            HttpSession session
    )
    {
        userVo userVo = (userVo) session.getAttribute("user");
        Long  userId =  userVo.getId();
        int i = userMapper.deleteArticleById(id,userId);
        if (i>0)
            return JsonResults.ok();
        return new JsonResults(StatusCode.OPERATION_FAILED);
    }
}
