package com.weiboLast.demo.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.weiboLast.demo.base.response.JsonResult;
import com.weiboLast.demo.base.response.StatusCode;
import com.weiboLast.demo.mapper.weiboMapper;
import com.weiboLast.demo.pojo.dto.WeiboAddParam;
import com.weiboLast.demo.pojo.entity.weiBo;
import com.weiboLast.demo.pojo.vo.UserVO;
import com.weiboLast.demo.pojo.vo.weiboIndexVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
        UserVO user = (UserVO) session.getAttribute("user");
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
            return JsonResult.ok();
        }
        logger.warning("发生未知错误！文章发布失败");
        return new JsonResult(StatusCode.OPERATION_ERROR);
    }

    @GetMapping("selectIndex")
    @Operation(summary = "微博详情页面")
    @ApiOperationSupport(order = 300)
    public JsonResult list(
            @Schema(description = "主页面第几页",example = "10")
            Long pageNumber)
    {
        logger.info("进入到微博主页面……");
        Long number = weiboMapper.countAllWeiBo();
        Long l;
        if (number%3==0)
            l = number / 3;
        else
            l=number /3 +1;
        if (pageNumber>l) {
            logger.info("暂无文章资源");
            return new JsonResult(StatusCode.ARTICLES_NOT_EXISTS);
        }
        logger.info("当前在[%d]页,一共[%d]页".formatted(pageNumber,l));
        pageNumber = MAX_MESSAGE_IN_ONE_PAGE*(pageNumber-1);
        if (pageNumber!=0) pageNumber++;
        List<weiboIndexVo> list = weiboMapper.selectWeiBo(pageNumber);
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
        weiboIndexVo weiboDetail = weiboMapper.selectWeiBoById(id);
        logger.info("找到文章，标题:《%s》".formatted(
                weiboDetail.getTitle()
        ));
        return JsonResult.ok(weiboDetail);
    }

}
