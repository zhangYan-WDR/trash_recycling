package com.applet.trash.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("td_address")
public class Address {

    private String id;

    private String userCode;

    private String userName;

    private String userPhone;

    private String locationAddress;

    private String detailedAddress;

    private String sex;

    private Boolean isDefault;

}
