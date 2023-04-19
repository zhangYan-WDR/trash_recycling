package com.applet.trash.service;

import com.applet.trash.Do.SaveOrderDo;
import com.applet.trash.entity.Order;
import com.applet.trash.vo.OrderVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface OrderService extends IService<Order> {
    Order saveOrderByType(SaveOrderDo saveOrderDo,String type);

    List<OrderVO> selectOrderDetailByType(String type);
}
