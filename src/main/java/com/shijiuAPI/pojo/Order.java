package com.shijiuAPI.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @TableId( type = IdType.AUTO)
    private Integer id;

    private Integer shopId;

    private String openId;
    //用户名字
    private String name;
    //用户地址
    private String address;
    //用户电话
    private String phone;

    private Integer status;

    private Boolean appDelete;

    private Boolean adminDelete;

    private String created;

    private String updated;

    private Boolean commented;
}
