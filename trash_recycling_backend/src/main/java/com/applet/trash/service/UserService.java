package com.applet.trash.service;

import com.applet.trash.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

public interface UserService extends IService<User> {
    Map<String, String> login(String code);

    String getPhoneByUser(String code);

    void addPointByExam(String userCode);

}
