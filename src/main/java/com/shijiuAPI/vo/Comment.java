package com.shijiuAPI.vo;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer shopId;

    //评论人名字
    private String name;

    private String content;

    private String reply;

    //店主是否已回复
    private Boolean isReply;

    /**
     * 创建时间（数据库自动生成，无需手动赋值）
     */
    @TableField(insertStrategy = FieldStrategy.NEVER)
    private String createDate;
    /**
     * 修改时间（数据库自动生成，无需手动赋值）
     */
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private String replyDate;





}
