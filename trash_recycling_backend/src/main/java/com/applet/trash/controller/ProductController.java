package com.applet.trash.controller;

import com.applet.trash.entity.Product;
import com.applet.trash.service.ProductService;
import com.applet.trash.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/product")
@Api(tags = "商品相关接口")
@CrossOrigin
public class ProductController {

    @Resource
    private ProductService productService;

    @GetMapping("/list")
    @ApiOperation("商品列表")
    public R list(@RequestParam("title")String title){
        List<Product> products = productService.getListByTitle(title);
        return R.ok().data("list", products).setMessage("查询成功");
    }

}
