package com.applet.trash.service;

import com.applet.trash.entity.CategoryDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface CategoryDetailService extends IService<CategoryDetail> {
    List<CategoryDetail> getListByNameAndId(int id, String detailName);

    List<CategoryDetail> getRandomExamList();

    String getCategoryDetailByImage(MultipartFile file) throws IOException;

    String getBaiduAuthToken(String apiKey,String secretKey);

}
