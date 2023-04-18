package com.applet.trash.service;

import com.applet.trash.entity.CategoryDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface CategoryDetailService extends IService<CategoryDetail> {
    List<CategoryDetail> getListByNameAndId(int id, String detailName);

    List<CategoryDetail> getRandomExamList();

}
