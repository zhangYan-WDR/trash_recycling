package com.applet.trash.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("td_order")
public class Order {

    @TableId(value = "id", type = IdType.UUID)
    private String id;

    private String orderNo;

    private Integer orderPrice;

    private String orderStatus;

    private String orderAddressNo;

}
