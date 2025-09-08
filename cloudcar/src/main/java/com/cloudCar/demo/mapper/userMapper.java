package com.cloudCar.demo.mapper;

import com.cloudCar.demo.pojo.DTO.UserLoginParam;
import com.cloudCar.demo.pojo.DTO.UserRegParam;
import com.cloudCar.demo.pojo.entity.user;
import com.cloudCar.demo.pojo.vo.userVO;

import java.util.List;

public interface userMapper {

    Long[] Login(UserRegParam userRegParam);

    int insertUser(user user);

    userVO Login(UserLoginParam userLoginParam);

    List<user> selectAllUsers();

    List<userVO> searchUserByUserName(String username);

    user selectUserById(Long id);

    List<userVO> sortById();

    List<userVO> sortByTime();
}
