package com.applet.trash.Do;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SaveAddressDo {

    @ApiModelProperty("用户名称")
    private String userName;
    @ApiModelProperty("用户电话")
    private String userPhone;
    @ApiModelProperty("定位地址")
    private String locationAddress;
    @ApiModelProperty("详细地址")
    private String detailedAddress;
    @ApiModelProperty("性别：男/女")
    private String sex;
    @ApiModelProperty("是否是默认：true/false")
    private Boolean isDefault;

}
