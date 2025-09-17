package com.weiboLast.demo.pojo.vo;

import com.weiboLast.demo.pojo.entity.CustomTag;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserVO3 extends UserVO2 {
    private List<CustomTag> tags;
}
