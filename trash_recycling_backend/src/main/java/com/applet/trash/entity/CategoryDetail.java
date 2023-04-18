package com.applet.trash.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("td_category_detail")
public class CategoryDetail {

    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    private int categoryId;

    private String detailName;

    private String categoryName;

}
