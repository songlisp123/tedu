package com._02vue.demo.user.service.imp;

import com._02vue.demo.user.mapper.userMapper;
import com._02vue.demo.user.pojo.dto.UserLoginPara;
import com._02vue.demo.user.pojo.dto.UserQuery;
import com._02vue.demo.user.pojo.dto.UserRegPara;
import com._02vue.demo.user.pojo.dto.UserResetPassword;
import com._02vue.demo.user.pojo.entity.Gender;
import com._02vue.demo.user.pojo.entity.Level;
import com._02vue.demo.user.pojo.entity.Status;
import com._02vue.demo.user.pojo.entity.User;
import com._02vue.demo.user.pojo.vo.UserVo;
import com._02vue.demo.user.service.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Slf4j
@org.springframework.stereotype.Service
public class ServiceImp implements Service {

    @Autowired
    private userMapper mapper;

    @Override
    public UserVo login(UserLoginPara loginPara) {
        log.debug("业务层:{}",loginPara);
        UserVo user = mapper.login(loginPara);
        //这里面有逻辑代码
//        if ()
        return user;
    }

    @Override
    public List<UserVo> selectUsers(UserQuery query) {
        log.debug("查询参数:{}",query);
        int pageSize = query.getPageSize()==null?4: query.getPageSize();
        int currentPage = query.getCurrentPage() == null ?1: query.getCurrentPage();
        query.setCurrentPage((currentPage-1)*pageSize);
        query.setPageSize(pageSize);
        List<UserVo> users = mapper.selectUsers(query);
        return users;
    }

    @Override
    public void save(UserRegPara para) {
        log.debug("参数:{}",para);
        User user = new User();
        //提取到性别的字符串转化为性别美剧

        BeanUtils.copyProperties(para,user);
        user.setGender(toGender(para));
        user.setLevel(toLevel(para));
        user.setStatus(toStatus(para));
        if (user.getId() != null) {
            user.setUpdateTime(new Date());
            mapper.update(user);
        }
        else {
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            mapper.saved(user);
        }

    }

    @Override
    public void delete(Long userId) {
        log.debug("业务层：{}",userId);
        mapper.delete(userId);
    }

    @Override
    public void updatePaasword(UserResetPassword password) {
        log.debug("业务层数据接受：{}",password);
        mapper.updatePassword(password);
    }

    private Gender toGender(UserRegPara reg) {
        return Arrays.stream(Gender.values())
                .filter(g->g.getMessage().equals(reg.getGender()))
                .findFirst()
                .orElseGet(null);

    }

    private Level toLevel(UserRegPara para) {
        return Arrays.stream(Level.values())
                .filter(g->g.getCode().equals(para.getLevel()))
                .findFirst()
                .orElseGet(null);
    }

    private Status toStatus(UserRegPara para) {
        return Arrays.stream(Status.values())
                .filter(g->g.getMessage().equals(para.getStatus()))
                .findFirst()
                .orElseGet(null);
    }

}
