package com.applet.trash.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.applet.trash.Do.SaveOrderDo;
import com.applet.trash.entity.Order;
import com.applet.trash.entity.Product;
import com.applet.trash.entity.Recovery;
import com.applet.trash.interceptor.UserInfoHolder;
import com.applet.trash.mapper.OrderMapper;
import com.applet.trash.service.OrderService;
import com.applet.trash.service.ProductService;
import com.applet.trash.service.RecoveryService;
import com.applet.trash.util.OrderNoUtils;
import com.applet.trash.vo.OrderVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper,Order> implements OrderService {

    @Resource
    private RecoveryService recoveryService;

    @Resource
    private ProductService productService;

    @Override
    public Order saveOrderByType(SaveOrderDo saveOrderDo, String type) {
        if (type.equals("product")) {
            return saveProductOrder(saveOrderDo);
        } else if (type.equals("recovery")) {
            return saveRecoveryOrder(saveOrderDo);
        }
        return null;
    }

    /**
     * 保存回收订单
     * @param saveOrderDo
     * @return
     */
    private Order saveRecoveryOrder(SaveOrderDo saveOrderDo) {
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setOrderNo(OrderNoUtils.getOrderNo());
        order.setOrderPrice(saveOrderDo.getOrderPrice());
        order.setOrderStatus("未完成");
        order.setOrderAddressNo(saveOrderDo.getOrderAddressNo());
        order.setProductId(saveOrderDo.getProductId());
        order.setOrderType("product");
        order.setUserCode(UserInfoHolder.userInfo.get());
        baseMapper.insert(order);
        return order;
    }

    /**
     * 保存积分兑换商品订单
     * @param saveOrderDo
     * @return
     */
    private Order saveProductOrder(SaveOrderDo saveOrderDo) {
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setOrderNo(OrderNoUtils.getOrderNo());
        order.setOrderPrice(saveOrderDo.getOrderPrice());
        order.setOrderStatus("未完成");
        order.setOrderAddressNo(saveOrderDo.getOrderAddressNo());
        order.setRecoveryId(saveOrderDo.getRecoveryId());
        order.setRecoveryHight(saveOrderDo.getRecoveryHight());
        order.setOrderType("recovery");
        order.setUserCode(UserInfoHolder.userInfo.get());
        baseMapper.insert(order);
        return order;
    }

    @Override
    public List<OrderVO> selectOrderDetailByType(String type) {
        if (type.equals("product")) {
            return getProductOrder();
        } else if (type.equals("recovery")) {
            return getRecoveryOrder();
        } else if (type.equals("all")) {
            return allOrder();
        }
        return null;
    }

    private List<OrderVO> allOrder() {
        List<OrderVO> orderVOS = new ArrayList<>();
        List<OrderVO> recoveryOrder = getRecoveryOrder();
        orderVOS.addAll(recoveryOrder);
        List<OrderVO> productOrder = getProductOrder();
        orderVOS.addAll(productOrder);
        return orderVOS;
    }

    private List<OrderVO> getRecoveryOrder() {
        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Order::getUserCode, UserInfoHolder.userInfo.get());
        wrapper.eq(Order::getOrderType, "recovery");
        List<Order> orders = baseMapper.selectList(wrapper);
        List<OrderVO> orderVOS = new ArrayList<>();
        for (Order order : orders) {
            OrderVO orderVO = new OrderVO();
            BeanUtil.copyProperties(order,orderVO);
            //查询回收订单详细信息
            LambdaQueryWrapper<Recovery> recoveryLambdaQueryWrapper = new LambdaQueryWrapper<>();
            recoveryLambdaQueryWrapper.eq(Recovery::getId, order.getRecoveryId());
            Recovery recovery = recoveryService.getOne(recoveryLambdaQueryWrapper);
            orderVO.setRecoveryName(recovery.getRecoveryName());
            orderVOS.add(orderVO);
        }
        return orderVOS;
    }

    private List<OrderVO> getProductOrder() {
        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Order::getUserCode, UserInfoHolder.userInfo.get());
        wrapper.eq(Order::getOrderType, "product");
        List<Order> orders = baseMapper.selectList(wrapper);
        List<OrderVO> orderVOS = new ArrayList<>();
        for (Order order : orders) {
            OrderVO orderVO = new OrderVO();
            BeanUtil.copyProperties(order,orderVO);
            //查询商品兑换订单的详细信息
            LambdaQueryWrapper<Product> productLambdaQueryWrapper = new LambdaQueryWrapper<>();
            productLambdaQueryWrapper.eq(Product::getId, order.getProductId());
            Product product = productService.getOne(productLambdaQueryWrapper);
            orderVO.setProductName(product.getProductName());
            orderVO.setProductIntegral(product.getProductIntegral());
            orderVO.setProductNum(product.getProductNum());
            orderVO.setProductTitle(product.getProductTitle());
            orderVO.setProductImage(product.getProductImage());
            orderVO.setProductPrice(product.getProductPrice());
            orderVOS.add(orderVO);
        }
        return orderVOS;
    }
}
