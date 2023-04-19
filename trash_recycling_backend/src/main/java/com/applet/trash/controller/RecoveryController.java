package com.applet.trash.controller;

import com.applet.trash.entity.Recovery;
import com.applet.trash.service.RecoveryService;
import com.applet.trash.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/recovery")
@Api(tags = "回收相关接口")
@CrossOrigin
public class RecoveryController {

    @Resource
    private RecoveryService recoveryService;

    @RequestMapping("/list")
    @ApiOperation("回收分类列表")
    public R list(){
        List<Recovery> list = recoveryService.list();
        return R.ok().data("list",list).setMessage("查询成功");
    }

}
