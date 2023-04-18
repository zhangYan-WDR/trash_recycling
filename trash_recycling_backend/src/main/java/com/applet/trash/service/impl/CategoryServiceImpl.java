package com.applet.trash.service.impl;

import com.applet.trash.entity.Category;
import com.applet.trash.mapper.CategoryMapper;
import com.applet.trash.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
