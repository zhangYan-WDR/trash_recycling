package com.applet.trash.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("td_recovery")
public class Recovery {

    @TableId(value = "id", type = IdType.UUID)
    private String id;

    private String recoveryName;

    private Date createTime;

    private Date updateTime;

}
