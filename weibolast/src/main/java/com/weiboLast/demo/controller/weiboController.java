package com.weiboLast.demo.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.weiboLast.demo.base.response.JsonResult;
import com.weiboLast.demo.base.response.StatusCode;
import com.weiboLast.demo.mapper.weiboMapper;
import com.weiboLast.demo.pojo.dto.WeiboAddParam;
import com.weiboLast.demo.pojo.dto.weiBoEditPara;
import com.weiboLast.demo.pojo.entity.tag;
import com.weiboLast.demo.pojo.entity.weiBo;
import com.weiboLast.demo.pojo.entity.weiBoTag;
import com.weiboLast.demo.pojo.vo.UserVO2;
import com.weiboLast.demo.pojo.vo.weiBoDetailVO;
import com.weiboLast.demo.pojo.vo.weiboIndexVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Slf4j
@Validated
@RestController
@RequestMapping("/v1/weibo/")
@Tag(name = "02微博模块",description = "02微博模块")
public class weiboController {

    @Autowired
    private weiboMapper weiboMapper;
    private static final Logger logger = Logger.getLogger("weibo");
    private static final int MAX_MESSAGE_IN_ONE_PAGE=2;

    @PostMapping("insert")
    @Operation(summary = "发布文章")
    @ApiOperationSupport(order = 100)
    public JsonResult insert(
            @RequestBody WeiboAddParam weiboAddParam,
            HttpSession session)
    {
        logger.info("进入到发布文章的页面……");
        List<tag> tags = weiboAddParam.getTags();
        List<Integer> tagIds = new ArrayList<>();
        tags.forEach(result->{
            String message = result.getMessage();
            Integer tagId = weiboMapper.selectTagId(message);
            tagIds.add(tagId);
        });
        UserVO2 user = (UserVO2) session.getAttribute("user");
        Long userId = user.getId();
        logger.info("当前用户：%s".formatted(user.getUsername()));
        weiBo weiBo = new weiBo();
        BeanUtils.copyProperties(weiboAddParam,weiBo);
        weiBo.setCreateTime(new Date());
        weiBo.setUpdateTime(new Date());
        weiBo.setUserId(userId);
        int i = weiboMapper.insertWeiBo(weiBo);
        if (i>0) {
            logger.info("发布成功");
            logger.info("主键id=%d".formatted(weiBo.getId()));
            tagIds.forEach(e->{
                weiBoTag weiBoTag = new weiBoTag(weiBo.getId(),e);
                weiboMapper.insertWeiAndTag(weiBoTag);
            });
            return JsonResult.ok();
        }
        logger.warning("发生未知错误！文章发布失败");
        return new JsonResult(StatusCode.OPERATION_ERROR);
    }

    @GetMapping("selectIndex")
    @Operation(summary = "微博主页面")
    @ApiOperationSupport(order = 300)
    public JsonResult list(
            @Schema(description = "主页面第几页",example = "10")
            Long pageNumber)
    {
        logger.info("进入到微博主页面……");
        Long number = weiboMapper.countAllWeiBo();
        Long l;
        if (number%MAX_MESSAGE_IN_ONE_PAGE==0)
            l = number / MAX_MESSAGE_IN_ONE_PAGE;
        else
            l=number /MAX_MESSAGE_IN_ONE_PAGE +1;
        if (pageNumber>l) {
            logger.info("暂无文章资源");
            return new JsonResult(StatusCode.ARTICLES_NOT_EXISTS);
        }
        logger.info("当前在[%d]页,一共[%d]页".formatted(pageNumber,l));
        pageNumber = MAX_MESSAGE_IN_ONE_PAGE*(pageNumber-1);
//        if (pageNumber!=0) pageNumber++;
        List<weiboIndexVo> list = weiboMapper.selectWeiBo(pageNumber,MAX_MESSAGE_IN_ONE_PAGE);
        return JsonResult.ok(list);
    }

    @GetMapping("selectById")
    @Operation(summary = "查询特定的微博信息")
    @ApiOperationSupport(order = 400)
    public JsonResult id(
            @Range(min = 100,max = 500,message = "超出索引范围！")
            @Schema(description = "微博id",example = "10")
            Long id)
    {
        logger.info("查询特定微博文章的代码");
        List<Long> ids = weiboMapper.countAllWeiBoIds();
        if (!ids.contains(id)) {
            logger.info("文章不存在");
            return new JsonResult(StatusCode.ARTICLES_NOT_EXISTS);
        }
        weiBoDetailVO weiboDetail = weiboMapper.selectWeiBoById(id);
        logger.info("找到文章，标题:《%s》".formatted(
                weiboDetail.getTitle()
        ));
        return JsonResult.ok(weiboDetail);
    }
        //需要验证用户是否登录而且修改的文章必须是用户的文章
    @PostMapping("edit")
    @Operation(summary = "更新微博信息")
    @ApiOperationSupport(order = 500)
    public JsonResult edit(
            @RequestBody weiBoEditPara weiBoEditPara,
            HttpSession  session)
    {
        logger.info("进入到用户更新微博界面……");
        UserVO2 user = (UserVO2) session.getAttribute("user");
        Long userId = user.getId();
        int i = weiboMapper.updateWeiBo(weiBoEditPara,userId);
        if (i>0) {
            logger.info("更新成功");
            return JsonResult.ok();
        }
        logger.warning("发送未知错误，请重试！");
        return new JsonResult(StatusCode.VALIDATION_ERROR);
    }

    @GetMapping("del")
    @Operation(summary = "删除微博文章")
    @ApiOperationSupport(order = 200)
    public JsonResult del(
            @Schema(description = "微博id数组",required = true,example = "[1,2,3]")
            @Size(min = 1,message = "索引不能为负")
            Long[] ids,
            HttpSession session)
    {
        logger.info("进入到删除微博文章的页面");
        UserVO2 user = (UserVO2) session.getAttribute("user");
        Long userId = user.getId();;
        weiboMapper.delete(ids,userId);
        logger.info("删除成功！");
        return JsonResult.ok();
    }

    @PostMapping("searchWeiBo")
    @Operation(summary = "搜索微博")
    @ApiOperationSupport(order = 800)
    @Parameter(name = "title",required = false)
    public JsonResult search(
            @NotBlank(message = "标题不能为空！")
            String title) {
        logger.info("进入搜索框……");
        List<weiboIndexVo> list =
                weiboMapper.searchWeiBo(title);
        logger.info("搜索完成，共发现 %d 篇帖子".formatted(
                list.size()
        ));
        return JsonResult.ok(list);
    }

    //通过标签查找文章
    //不过这里面会有几个问题，该怎么设置多个标签呢？
    //最简单的一种情况是设置单个标签,至于多标签的实力，我暂时还不会，以后修补
    @GetMapping("selectweibotag")
    @Operation(summary = "根据微博id挑选微博")
    @ApiOperationSupport(order = 900)
    public JsonResult selectweibotag(tag tag) {
        logger.info("进入到标签界面……");
        List<weiboIndexVo> list =
                weiboMapper.selectWeiBoByTag(tag.toString());
        logger.info("找到 %d 篇文章".formatted(list.size()));
        return JsonResult.ok(list);
    }

    //下一个，对微博表进行喜欢统计

}
