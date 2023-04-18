package com.applet.trash.controller;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.applet.trash.service.UserService;
import com.applet.trash.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
@Api(tags = "用户相关接口")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/login")
    @ApiOperation("微信登录接口")
    public R userLogin(@RequestParam("code")String code){
        Map<String,String> result = userService.login(code);
        return R.ok().data("result", result).setMessage("登录成功");
    }

    @GetMapping("/getUserPhone")
    @ApiOperation("获取用户手机号信息")
    public R getPhoneByUser(@RequestParam("code")String code){
        String result = userService.getPhoneByUser(code);
        return R.ok().data("result", result).setMessage("登录成功");
    }

}
