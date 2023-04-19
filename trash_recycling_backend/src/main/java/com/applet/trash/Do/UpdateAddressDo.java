package com.applet.trash.Do;

import lombok.Data;

@Data
public class UpdateAddressDo {

    private String id;

    private String userCode;

    private String userName;

    private String userPhone;

    private String locationAddress;

    private String detailedAddress;

    private String sex;

    private Boolean isDefault;

}
