package com.applet.trash.Do;

import lombok.Data;

@Data
public class SaveOrderDo {

    private Integer orderPrice;

    private String orderAddressNo;

    private int productId;

    private String RecoveryId;

    private String RecoveryHight;

}
