package com.applet.trash.service;

import com.applet.trash.entity.Integral;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IntegralService extends IService<Integral> {
    void saveIntegralLog(String userCode,String type);
}
