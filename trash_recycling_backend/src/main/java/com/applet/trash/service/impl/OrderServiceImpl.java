package com.applet.trash.service.impl;

import com.applet.trash.entity.Order;
import com.applet.trash.mapper.OrderMapper;
import com.applet.trash.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper,Order> implements OrderService {
}
