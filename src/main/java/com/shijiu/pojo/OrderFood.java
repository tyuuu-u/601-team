package com.shijiu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderFood {

    @TableId( type = IdType.AUTO)
    private Integer id;

    private Integer orderId;

    private Integer foodId;

    private Integer count;
}
