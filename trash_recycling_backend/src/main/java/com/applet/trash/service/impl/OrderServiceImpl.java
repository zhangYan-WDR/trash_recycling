package com.applet.trash.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.applet.trash.Do.SaveOrderDo;
import com.applet.trash.entity.*;
import com.applet.trash.interceptor.UserInfoHolder;
import com.applet.trash.mapper.OrderMapper;
import com.applet.trash.service.*;
import com.applet.trash.util.OrderNoUtils;
import com.applet.trash.vo.OrderVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper,Order> implements OrderService {

    @Resource
    private RecoveryService recoveryService;

    @Resource
    private ProductService productService;

    @Resource
    private UserService userService;

    @Resource
    private IntegralService integralService;

    @Override
    public Map<String,Object> saveOrderByType(SaveOrderDo saveOrderDo, String type) {
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
    private Map<String,Object> saveRecoveryOrder(SaveOrderDo saveOrderDo) {
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setOrderNo(OrderNoUtils.getOrderNo());
        order.setOrderPrice(saveOrderDo.getOrderPrice());
        order.setOrderStatus("未完成");
        order.setOrderAddressNo(saveOrderDo.getOrderAddressNo());
        order.setRecoveryId(saveOrderDo.getRecoveryId());
        order.setRecoveryHight(saveOrderDo.getRecoveryHight());
        order.setOrderType("product");
        order.setUserCode(UserInfoHolder.userInfo.get());
        baseMapper.insert(order);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("order", order);
        resultMap.put("code", 0);
        return resultMap;
    }

    /**
     * 保存积分兑换商品订单
     * @param saveOrderDo
     * @return
     */
    private Map<String,Object> saveProductOrder(SaveOrderDo saveOrderDo) {
        Map<String, Object> resultMap = new HashMap<>();
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setOrderNo(OrderNoUtils.getOrderNo());
        order.setOrderPrice(saveOrderDo.getOrderPrice());
        order.setOrderStatus("未完成");
        order.setOrderAddressNo(saveOrderDo.getOrderAddressNo());
        order.setProductId(saveOrderDo.getProductId());
        order.setOrderType("recovery");
        order.setUserCode(UserInfoHolder.userInfo.get());
        //积分商城兑换商品
        //1.判断当前商品是否有库存
        LambdaQueryWrapper<Product> productWrapper = new LambdaQueryWrapper<>();
        productWrapper.eq(Product::getId, saveOrderDo.getProductId());
        Product product = productService.getOne(productWrapper);
        if (product.getProductNum() <= 0) {
            resultMap.put("code", -1);
            resultMap.put("errorMsg", "当前商品库存不足");
            return resultMap;
        }
        //2.先判断当前用户是否有这么多积分
        LambdaQueryWrapper<User> userWrapper = new LambdaQueryWrapper<>();
        userWrapper.eq(User::getUserCode, UserInfoHolder.userInfo.get());
        User user = userService.getOne(userWrapper);
        if (user.getPersonalPoints() < product.getProductIntegral()) {
            resultMap.put("code", -1);
            resultMap.put("errorMsg", "当前用户积分不足");
            return resultMap;
        }
        //3.如果有库存，下单了之后将库存减1
        product.setProductNum(product.getProductNum() - 1);
        productService.updateById(product);
        baseMapper.insert(order);
        //4.更新用户剩余的积分
        user.setPersonalPoints(user.getPersonalPoints()-product.getProductIntegral());
        userService.updateById(user);
        //5.生成一条积分减少记录
        Integral integral = new Integral();
        integral.setId(UUID.randomUUID().toString());
        integral.setIntegralNo(OrderNoUtils.getIntegralNO());
        integral.setIntegralTitle("购买"+product.getProductTitle());
        integral.setIsAdd(false);
        integral.setIntegralUpdate("-"+product.getProductIntegral()+".00");
        integral.setUserCode(UserInfoHolder.userInfo.get());
        integral.setCreateTime(new Date());
        integralService.save(integral);
        //6.构建返回参数
        resultMap.put("order", order);
        resultMap.put("code", 0);
        return resultMap;
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
