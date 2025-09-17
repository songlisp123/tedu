package com.weiboLast.demo.pojo.vo;

import com.weiboLast.demo.pojo.entity.CustomTag;
import com.weiboLast.demo.pojo.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailInfo {
    private Long id;
    private String username;
    private String nickname;
    private Date createTime;
    private List<CustomTag> tags;
    private List<UserVO> followers;
    private List<UserVO> followings;

}
