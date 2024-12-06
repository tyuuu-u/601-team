package com.shijiuAPI.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopVo {
    //店铺id
//    @TableId( type = IdType.AUTO)
    Integer id;
    //店主id
    Integer userId;
    //店主名字
    String ownerName;

    String shopName;

    String shopSrc;
    //店铺联系方式
    String phone;
    //店铺简介
    String summary;
    //店铺评分，管理员控制
    Integer stars;
}
