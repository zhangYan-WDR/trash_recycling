package com.applet.trash.controller;

import com.applet.trash.entity.Category;
import com.applet.trash.entity.CategoryDetail;
import com.applet.trash.service.CategoryDetailService;
import com.applet.trash.service.CategoryService;
import com.applet.trash.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("/category")
@Api(tags = "垃圾分类相关接口")
@CrossOrigin
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @Resource
    private CategoryDetailService categoryDetailService;

    @GetMapping("/list")
    @ApiOperation("查询垃圾分类大类数据和对应描述")
    public R getCategoryList(){
        List<Category> list = categoryService.list();
        return R.ok().data("list", list);
    }

    @GetMapping("/detail/list")
    @ApiOperation("根据关键字和大类别模糊查询小类信息")
    public R getCategoryDetailByCategoryId(@RequestParam("id") int id, @RequestParam("detailName") String detailName){
        List<CategoryDetail> categoryDetails = categoryDetailService.getListByNameAndId(id, detailName);
        return R.ok().data("list", categoryDetails);
    }

    @PostMapping("/image")
    @ApiOperation("拍照识别照片物品")
    public R getCategoryDetailByImage(@RequestParam(value = "file") MultipartFile file) throws IOException {
        String keyword = categoryDetailService.getCategoryDetailByImage(file);
        return R.ok().data("keyword", keyword);
    }

    @GetMapping("/authToken")
    @ApiOperation("测试百度云鉴权token")
    public R getBaiduCloudToken(){
        String keyword = categoryDetailService.getBaiduAuthToken();
        return R.ok().data("data", keyword);
    }

}
