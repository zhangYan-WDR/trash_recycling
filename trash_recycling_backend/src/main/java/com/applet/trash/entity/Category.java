package com.applet.trash.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("td_category")
public class Category {

    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    private String categoryName;

    private String categoryDetail;

}
