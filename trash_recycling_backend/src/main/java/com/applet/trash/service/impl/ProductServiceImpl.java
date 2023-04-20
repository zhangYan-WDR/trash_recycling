package com.applet.trash.service.impl;

import com.applet.trash.entity.Product;
import com.applet.trash.mapper.ProductMapper;
import com.applet.trash.service.ProductService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    @Override
    public List<Product> getListByTitle(String title) {
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Product::getProductTitle, title);
        List<Product> products = baseMapper.selectList(wrapper);
        for (Product product : products) {
            product.setProductImage("http://localhost:8080"+product.getProductImage());
        }
        return products;
    }
}
