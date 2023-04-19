package com.applet.trash.controller;

import com.applet.trash.interceptor.UserInfoHolder;
import com.applet.trash.service.IntegralService;
import com.applet.trash.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/integral")
@CrossOrigin
@Api(tags = "积分相关接口")
public class IntegralController {

    @Resource
    private IntegralService integralService;

    @PostMapping("/save/sign")
    @ApiOperation("签到添加积分记录")
    public R signSaveIntegral(){
        integralService.saveIntegralLog(UserInfoHolder.userInfo.get(),"sign");
        return R.ok().setMessage("签到成功");
    }

}
