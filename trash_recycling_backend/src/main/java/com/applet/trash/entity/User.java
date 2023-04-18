package com.applet.trash.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("td_user")
public class User {

    @TableId(value = "id", type = IdType.UUID)
    private String id;

    private String userCode;

    private String userName;

    private String phone;

    private Integer personalPoints;

}
