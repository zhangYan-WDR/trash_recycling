package com.applet.trash.service;

import com.applet.trash.entity.Integral;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

public interface IntegralService extends IService<Integral> {
    Map<String,String> saveIntegralLog(String userCode, String type);
}
