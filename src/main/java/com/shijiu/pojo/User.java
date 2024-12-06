package com.shijiu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId( type = IdType.AUTO)
    Integer id;

    String username;

    String password;
    // 1就是管理员，0就是店主
    Boolean isAdmin;
}