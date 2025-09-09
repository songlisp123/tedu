package com.weibo.demo.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.weibo.demo.base.response.JsonResults;
import com.weibo.demo.base.response.StatusCode;
import com.weibo.demo.mapper.weiBoMapper;
import com.weibo.demo.pojo.dto.WeiboAddParam;
import com.weibo.demo.pojo.entity.weiBo;
import com.weibo.demo.pojo.vo.UserVo;
import com.weibo.demo.pojo.vo.WeiBoDetailVO;
import com.weibo.demo.pojo.vo.weiBoIndex;
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
@RequestMapping("/v1/weibo/")
@Tag(name = "02微博模块")
public class weiBoController {
    @Autowired
    private weiBoMapper weiBoMapper;

    @PostMapping("add")
    @Operation(summary = "添加微博咨询",description = "此函数将添加微波通信")
    @ApiOperationSupport(order = 100)
    public JsonResults insert(
            @RequestBody WeiboAddParam weiboAddParam,
            HttpSession session
    )
    {
        UserVo user = (UserVo) session.getAttribute("user");
        if (user==null)
            return new JsonResults(StatusCode.NOT_LOGIN);
        var weibo = new weiBo();
        BeanUtils.copyProperties(weiboAddParam,weibo);
        weibo.setUserID(user.getId());
        weibo.setCreateTime(new Date());
        int i = weiBoMapper.insertWeiBo(weibo);
        if (i>0)
            return JsonResults.ok();
        return new JsonResults(StatusCode.VALIDATED_ERROR);
    }

    @GetMapping("selectIndex")
    @Operation(summary = "微博首页",description = "微博首页")
    @ApiOperationSupport(order = 300)
    public JsonResults list() {
        List<weiBoIndex> list = weiBoMapper.selectWeiBo();
        return JsonResults.ok(list);
    }

    @GetMapping("selectById")
    @Operation(summary = "微博详情",description = "查询特定id的微博详情")
    @ApiOperationSupport(order = 301)
    public JsonResults detail(Long id) {
        List<Long> list1 = weiBoMapper.selectAllWeiBoId();
        if (!list1.contains(id))
            return new JsonResults(StatusCode.OPERATION_FAILED);
        List<WeiBoDetailVO> list =
                weiBoMapper.selectWeiBoById(id);
        return JsonResults.ok(list);
    }


}
