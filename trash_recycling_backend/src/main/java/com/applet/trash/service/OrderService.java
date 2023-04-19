package com.applet.trash.service;

import com.applet.trash.Do.SaveOrderDo;
import com.applet.trash.entity.Order;
import com.applet.trash.vo.OrderVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface OrderService extends IService<Order> {
    Map<String,Object> saveOrderByType(SaveOrderDo saveOrderDo, String type);

    List<OrderVO> selectOrderDetailByType(String type);
}
