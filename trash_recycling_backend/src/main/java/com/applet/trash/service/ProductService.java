package com.applet.trash.service;

import com.applet.trash.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ProductService extends IService<Product> {
    List<Product> getListByTitle(String title);
}
