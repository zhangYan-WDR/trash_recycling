package com.applet.trash.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("td_product")
public class Product {

    private String id;
    private String productName;
    private int productIntegral;
    private int productNum;
    private String productTitle;
    private String productImage;
    private int productPrice;

}
