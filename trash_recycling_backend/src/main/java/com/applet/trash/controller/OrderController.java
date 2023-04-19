package com.applet.trash.controller;

import com.applet.trash.Do.SaveOrderDo;
import com.applet.trash.entity.Order;
import com.applet.trash.service.OrderService;
import com.applet.trash.vo.OrderVO;
import com.applet.trash.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
@Api(tags = "订单相关接口")
@CrossOrigin
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("/save/{type}")
    @ApiOperation("下单接口")
    public R saveProductOrder(@RequestBody SaveOrderDo saveOrderDo,@PathVariable("type")String type){
        Map<String,Object> result = orderService.saveOrderByType(saveOrderDo,type);
        if (!result.get("code").equals(0)) {
            return R.error().setMessage((String) result.get("errorMsg"));
        }
        Order order = (Order) result.get("order");
        if (order != null) {
            return R.ok().setMessage("添加成功").data("order", order);
        }else {
            return R.error().setMessage("请检查添加的类别是否正确");
        }
    }

    @GetMapping("list/{type}")
    @ApiOperation("查询当前用户下的所有订单")
    public R orderDetailList(@PathVariable("type") String type) {
        List<OrderVO> list = orderService.selectOrderDetailByType(type);
        return R.ok().setMessage("查询成功").data("list",list);
    }

}
