package com.weiboLast.demo.controller;

import com.fasterxml.jackson.databind.annotation.EnumNaming;
import  com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.weiboLast.demo.base.response.JsonResult;
import com.weiboLast.demo.base.response.StatusCode;
import com.weiboLast.demo.mapper.userMapper;
import com.weiboLast.demo.pojo.dto.UserLoginParam;
import com.weiboLast.demo.pojo.dto.UserRegParam;
import com.weiboLast.demo.pojo.dto.updateUserInfo;
import com.weiboLast.demo.pojo.dto.userChangePassword;
import com.weiboLast.demo.pojo.entity.Action;
import com.weiboLast.demo.pojo.entity.CustomTag;
import com.weiboLast.demo.pojo.entity.User;
import com.weiboLast.demo.pojo.vo.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Slf4j
@RestController
@Tag(name = "01用户模块",description = "01用户模块")
@RequestMapping("/v1/user/")
public class userController {

    @Autowired
    private  userMapper userMapper;
    private static final Logger logger = Logger.getLogger("weibo");
    private static final int MAX_USERS_IN_ONE_PAGE = 2;

    @PostMapping("reg")
    @Operation(summary = "注册用户")
    @ApiOperationSupport(order = 100)
    public JsonResult reg(
            @RequestBody UserRegParam userRegParam) {
        //首先验证用户是否存在
        logger.info("进入用户注册界面……");
        Long id  =
                userMapper.selectUserByUserNameAndPassword(userRegParam);
        if (id!=null) {
            logger.info("用户已经存在，请重试！");
            return new JsonResult(StatusCode.USER_EXISTS);
        }
        User user = new User();
        BeanUtils.copyProperties(userRegParam,user);
        user.setCreateTime(new Date());
        int i = userMapper.insertUser(user);
        if (i>0) {
            logger.info("注册用户成功！");
            logger.info("新用户:%s".formatted(user.getUsername()));
            return JsonResult.ok();
        }
        logger.warning("发生未知异常，导致注册失败，请重试！");
        return new JsonResult(StatusCode.OPERATION_ERROR);
    }

    @PostMapping("login")
    @Operation(summary = "用户登录")
    @ApiOperationSupport(order = 200)
    public JsonResult login(
            @RequestBody UserLoginParam userLoginParam,
            HttpSession session)
    {
        logger.info("进入用户登录界面……");
        UserVO2 user = userMapper.selectUser(userLoginParam);
        if (user==null) {
            logger.warning("用户名或者密码错误");
            return new JsonResult(StatusCode.USERNAME_PASSWORD_ERROR);
        }
        session.setAttribute("user",user);
        logger.info("登录成功");
        return JsonResult.ok(user);
    }

    @GetMapping("currentUser")
    @Operation(summary = "获取当前用户")
    @ApiOperationSupport(order = 300)
    public JsonResult current(
            HttpSession session
    ) {
        logger.info("进入到用户登录认证界面……");
        UserVO2 user = (UserVO2) session.getAttribute("user");
        UserVO3 userVO3 = userMapper.selectUserByUser(user);

        logger.info("用户已登录，用户：%s".formatted(user.getUsername()));
        return JsonResult.ok(userVO3);
    }

    @GetMapping("logout")
    @Operation(summary = "退出登录")
    @ApiOperationSupport(order = 400)
    public JsonResult logout(HttpSession session) {
        logger.info("进入到用户退出登录功能块……");
        session.removeAttribute("user");
        logger.info("用户退出登录成功");
        return JsonResult.ok();
    }

    @GetMapping("userList")
    @Operation(summary = "用户列表")
    @ApiOperationSupport(order = 101)
    public JsonResult listAllUsers(
            @Positive
            @Schema(description = "查询路径参数",example = "10")
            Long pageNumber)
    {
        logger.info("用户列表");
        Long users = userMapper.collectAllUserNumber();
        Long l;
        if (users%MAX_USERS_IN_ONE_PAGE==0)
            l = users / MAX_USERS_IN_ONE_PAGE;
        else
            l=users / MAX_USERS_IN_ONE_PAGE + 1;
        if (pageNumber > l) {
            logger.info("超出范围，将重定向到最后一页");
            pageNumber=l;//如果超出范围，则定向到最后一页
        }
        logger.info("一共 %d 页,当前第 %d 页".formatted(l,pageNumber));
        pageNumber = MAX_USERS_IN_ONE_PAGE * (pageNumber-1);
        List<UserVO> list = userMapper.showUserLists(pageNumber, MAX_USERS_IN_ONE_PAGE);
        return JsonResult.ok(list);
    }

    //用户修改信息ok
    //用户头像--不ok
    //用户性别ok
    //用户更新信息ok

    /*
    更新个人信息，需要验证用户是否登录
     */
    @PostMapping("update")
    @Operation(summary="更新个人资料")
    @ApiOperationSupport(order = 150)
    public JsonResult update(
            @RequestBody updateUserInfo updateUserInfo,
            HttpSession session
    )
    {
        logger.info("更新个人资料界面……");
        List<CustomTag> tags = updateUserInfo.getTags();
        UserVO2 user = (UserVO2) session.getAttribute("user");
        Long userId = user.getId();
        tags.forEach(tag->{
            userMapper.insertUserAndTag(tag,userId);
        });
        int i =
                userMapper.updateInfo(updateUserInfo,userId);
        if (i>0){
            logger.info("个人资料更新成功！");
            return JsonResult.ok();
        }
        logger.info("资料更新失败");
        return new JsonResult(StatusCode.OPERATION_ERROR);
    }

    @GetMapping("del/{id}")
    @Operation(summary = "删除用户")
    @ApiOperationSupport(order = 120)
    public JsonResult del(
            @Schema(description = "路径参数",required = true,example = "10")
            @PathVariable Long id) {
        logger.info("进入到删除用户功能块……");
        int i = userMapper.deleteUserById(id);
        if (i>0) {
            logger.info("删除成功");
            return JsonResult.ok();
        }
        logger.warning("删除失败");
        return new JsonResult(StatusCode.OPERATION_ERROR);
    }

    /*
    修改用户密码模块
     */
    @PostMapping("changePassword")
    @Operation(summary = "修改用户密码")
    @ApiOperationSupport(order = 160)
    public JsonResult changePwd(@RequestBody userChangePassword pwd,
                                HttpSession session)
    {
        logger.info("进入到修改密码阶段……");
        UserVO2 user = (UserVO2) session.getAttribute("user");
        Long userId = user.getId();
        int i = userMapper.updateUserPassword(pwd,userId);
        if (i>0) {
            logger.info("修改密码成功");
            return JsonResult.ok();
        }
        logger.info("修改密码失败！");
        return new JsonResult(StatusCode.OPERATION_ERROR);
    }

    //根据用户id选择用户发表的帖子
    @GetMapping("selectArticleByUserId")
    @Operation(summary = "根据用户id挑选用户发表的帖子")
    @ApiOperationSupport(order = 175)
    public JsonResult sel(
            @Schema(description = "用户id",required = true,example = "12")
            Long userId)
    {
        logger.info("进入到微博个人用户界面……查询个人用户发表的帖子");
        userHasWeiBoVO userHasWeiBoVO = userMapper.countAllArticles(userId);
        logger.info("当前用户 %s 一共发表了 %d 篇帖子".formatted(
                userHasWeiBoVO.getUsername(),
                userHasWeiBoVO.getCount()
        ));
        List<weiboIndexVo> weiBos =
                userMapper.selectAllArticlesByUserId(userId);
        logger.info("查询成功！");
        return JsonResult.ok(weiBos);
    }

    //接下来是用户的跟随系统
    @PostMapping("follows")
    @Operation(summary = "跟随用户")
    @ApiOperationSupport(order = 900)
    public JsonResult follows(
            @Positive(message = "用户名不能为负")
            @Schema(description = "用户id",required = true,example = "10")
            Long follows_user_id,
            HttpSession session
    ) {
        logger.info("进入到用户跟随界面……");
        UserVO2 user = (UserVO2) session.getAttribute("user");
        Long userId = user.getId();
        if (Objects.equals(userId,follows_user_id)) {
            logger.warning("不能关注自己");
            return new JsonResult(StatusCode.OPERATION_ERROR);
        }
        int i =
                userMapper.insertIntoFollows(userId,follows_user_id);
        if (i>0) {
            logger.info("用户 %s 跟随成功".formatted(user.getUsername()));
            return JsonResult.ok();
        }
        logger.warning("用户跟随失败！请稍后再试");
        return new JsonResult(StatusCode.OPERATION_ERROR);
    }

    @PostMapping("unfollow")
    @Operation(summary = "取消跟随")
    @ApiOperationSupport(order = 900)
    public JsonResult unfollow(
            @Positive(message = "用户id不能为负")
            @Schema(description = "要取消跟随的用户id",required = true,example = "10")
            Long userId,
            HttpSession session
    ) {
        logger.info("进入到用户取消跟随的操作……");
        UserVO2 user = (UserVO2) session.getAttribute("user");
        Long id = user.getId();
        int i = userMapper.unFolllowers(id,userId);
        if (i>0) {
            logger.info("取消关注成功！");
            return JsonResult.ok();
        }
        logger.info("取消关注失败！");
        return new JsonResult(StatusCode.OPERATION_ERROR);
    }

    /*
    接下来需要的是用户排序
    1、按照用户id排序
     */

    //测试一个静态方法
    @GetMapping("test")
    @Operation(summary = "测试程序")
    @ApiOperationSupport(order = 700)
    public  JsonResult qualified(HttpSession session) {
        UserVO2 user = (UserVO2) session.getAttribute("user");
        Long id = user.getId();
        String[] strings = userMapper.selectPermession(id);
        if (strings==null)
            return new JsonResult(StatusCode.OPERATION_ERROR);
        return JsonResult.ok(strings);
    }

    @GetMapping("info")
    @Operation(summary = "用户详情")
    @ApiOperationSupport(order = 1000)
    public JsonResult info(
            @Schema(description = "用户id",required = true,example = "100")
            Long userId
    ) {
        logger.info("进入到用户详情页");
        UserDetailInfo userDetailInfo =
                userMapper.selectUserInfo(userId);
        return JsonResult.ok(userDetailInfo);
    }


    //喜欢或者不喜欢或不在乎这篇文章？
    @GetMapping("action")
    @Operation(summary = "用户评价")
    @ApiOperationSupport(order = 1200)
    public JsonResult likes(
            @Schema(description = "用户操作")
            Action action,
            @Positive(message = "微博id不能为负")
            @Schema(description = "操作的微博id",required = true,example = "200")
            Long weiboId,
            HttpSession session)
    {
        logger.info("进入到用户操作界面……");
        UserVO2 user = (UserVO2) session.getAttribute("user");
        Long userId = user.getId();
        int action1 = action.getAction();
        int i =
                userMapper.insertIntoArticleAndLikes(userId,weiboId,action1);
        if (i>0) {
            logger.info("用户喜欢这篇帖子");
            return JsonResult.ok();
        }
        logger.warning("用户操作失败，请重试！");
        return new JsonResult(StatusCode.OPERATION_ERROR);

    }

    //喜欢或者不喜欢这条评论？
    @GetMapping("likeComment")
    @Operation(summary = "喜欢评论")
    @ApiOperationSupport(order = 1200)
    public JsonResult comment(
            @Schema(description = "对此评论进行的操作")
            Action action,
            @Schema(description = "评论id",example = "300")
            @Positive(message = "评论文章id错误！")
            Long commentId,
            HttpSession session
    ) {
        logger.info("进入到评论操作界面……");
        UserVO2 user = (UserVO2) session.getAttribute("user");
        Long userId = user.getId();
        int action1 = action.getAction();
        int i =
                userMapper.insertIntoUserAndComment(userId,commentId,action1);
        if (i>0) {
            logger.info("用户喜欢这个评论");
            return JsonResult.ok();
        }
        logger.info("用户不喜欢这条评论");
        return new JsonResult(StatusCode.OPERATION_ERROR);
    }

}
