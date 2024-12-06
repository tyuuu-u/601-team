package com.shijiu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserVo {
    Integer userId;

    Integer shopId;

    // 1就是管理员，0就是店主
    Boolean isAdmin;


}
