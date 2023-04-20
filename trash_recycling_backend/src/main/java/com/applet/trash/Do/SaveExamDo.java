package com.applet.trash.Do;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SaveExamDo {

    @ApiModelProperty("传过去的当前的小类的id")
    private int id;
    @ApiModelProperty("垃圾分类大类的id")
    private int categoryId;
    @ApiModelProperty("垃圾分类小类详细名称")
    private String detailName;
    @ApiModelProperty("垃圾分类大类名称")
    private String categoryName;

}
