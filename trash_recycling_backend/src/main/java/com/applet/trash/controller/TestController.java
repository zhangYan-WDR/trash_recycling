package com.applet.trash.controller;

import com.applet.trash.vo.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "测试相关接口")
@RequestMapping("/test")
@CrossOrigin
public class TestController {

    @GetMapping("/testController")
    public R test() {
        return R.ok().data("success", "测试成功");
    }

}
