package com.shijiu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Food {
    @TableId( type = IdType.AUTO)
    private Integer id;
    private Integer shopId;
    private Integer categoryId;
    private String name;
    private BigDecimal price;
    private Boolean isRecommend;
    private String imgSrc;
    private String description;
}
