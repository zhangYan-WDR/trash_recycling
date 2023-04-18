package com.applet.trash.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("td_exam")
public class Exam {

    @TableId(value = "id", type = IdType.UUID)
    private String id;

    private String examRecordNo;

    private Integer score;

    private Integer testSuccessNum;

    private Integer testWrongNum;

    private Date createTime;

    private String userCode;

}
