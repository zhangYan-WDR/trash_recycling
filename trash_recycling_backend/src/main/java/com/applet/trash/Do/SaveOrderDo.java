package com.applet.trash.Do;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SaveOrderDo {
    @ApiModelProperty("订单金额")
    private Integer orderPrice;
    @ApiModelProperty("下单地址：从地址谱中选择的地址的地址编号：accessNo")
    private String orderAddressNo;
    @ApiModelProperty("下单的商品id：和回收的2选一")
    private int productId;
    @ApiModelProperty("回收的id：和商品订单2选一")
    private String RecoveryId;
    @ApiModelProperty("回收预估重量：和商品订单2选一")
    private String RecoveryHight;

}
