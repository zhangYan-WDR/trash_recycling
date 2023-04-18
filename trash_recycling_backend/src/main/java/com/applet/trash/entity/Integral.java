package com.applet.trash.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("td_integral")
public class Integral {

    @TableId(value = "id", type = IdType.UUID)
    private String id;

    private String integralNo;

    private String integralTitle;

    private String integralUpdate;

    private Date createTime;

    private Boolean isAdd;

    private String userCode;

}
