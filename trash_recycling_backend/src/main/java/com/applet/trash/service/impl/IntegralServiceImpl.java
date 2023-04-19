package com.applet.trash.service.impl;

import com.applet.trash.entity.Integral;
import com.applet.trash.mapper.IntegralMapper;
import com.applet.trash.service.IntegralService;
import com.applet.trash.util.OrderNoUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IntegralServiceImpl extends ServiceImpl<IntegralMapper, Integral> implements IntegralService {

    @Override
    public void saveIntegralLog(String userCode,String type) {
        Integral integral = new Integral();
        integral.setId(UUID.randomUUID().toString());
        integral.setIntegralNo(OrderNoUtils.getIntegralNO());
        if (type.equals("exam")) {
            integral.setIntegralTitle("考试送积分");
            integral.setIntegralUpdate("+30.00");
        } else if (type.equals("sign")) {
            integral.setIntegralTitle("签到送积分");
            integral.setIntegralUpdate("+10.00");
        }
        integral.setIsAdd(true);
        integral.setUserCode(userCode);
        baseMapper.insert(integral);
    }
}
