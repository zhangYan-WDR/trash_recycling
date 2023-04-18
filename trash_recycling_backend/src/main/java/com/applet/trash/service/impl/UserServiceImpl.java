package com.applet.trash.service.impl;

import com.applet.trash.entity.User;
import com.applet.trash.mapper.UserMapper;
import com.applet.trash.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
