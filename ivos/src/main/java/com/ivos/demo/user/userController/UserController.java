package com.ivos.demo.user.userController;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.ivos.demo.base.response.JsonResult;
import com.ivos.demo.user.impl.UserServe;
import com.ivos.demo.user.pojo.dto.UserLoginParam;
import com.ivos.demo.user.pojo.vo.UserVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Tag(name = "用户模块")
@RequestMapping("/v1/user/")
public class UserController {

    @Autowired
    private UserServe userService;

    @PostMapping("login")
    @Operation(summary = "用户登录功能")
    @ApiOperationSupport(order = 100)
    public JsonResult add(
            @Validated
            @RequestBody
            UserLoginParam userLoginParam)
    {
        log.debug("当前参数,{}",userLoginParam);
        UserVo userVo = userService.login(userLoginParam);
        return JsonResult.ok(userVo);
    }
}
