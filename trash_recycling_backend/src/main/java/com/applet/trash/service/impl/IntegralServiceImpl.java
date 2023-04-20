package com.applet.trash.service.impl;

import com.applet.trash.entity.Integral;
import com.applet.trash.interceptor.UserInfoHolder;
import com.applet.trash.mapper.IntegralMapper;
import com.applet.trash.service.IntegralService;
import com.applet.trash.util.DateUtil;
import com.applet.trash.util.OrderNoUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IntegralServiceImpl extends ServiceImpl<IntegralMapper, Integral> implements IntegralService {

    @Override
    public Map<String,String> saveIntegralLog(String userCode, String type) {
        Map<String, String> resultMap = new HashMap<>();
        Integral integral = new Integral();
        integral.setId(UUID.randomUUID().toString());
        integral.setIntegralNo(OrderNoUtils.getIntegralNO());
        if (type.equals("exam")) {
            integral.setIntegralTitle("考试送积分");
            integral.setIntegralUpdate("+30.00");
        } else if (type.equals("sign")) {
            //查询当天是否已经签过到了
            Date start = DateUtil.strToDateLong(DateUtil.dateToStr(new Date(), Locale.CHINA) + " 00:00:00");
            Date end = DateUtil.strToDateLong(DateUtil.dateToStr(new Date(), Locale.CHINA) + " 23:59:59");
            LambdaQueryWrapper<Integral> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Integral::getUserCode, UserInfoHolder.userInfo.get());
            queryWrapper.ge(Integral::getCreateTime,start);
            queryWrapper.lt(Integral::getCreateTime,end);
            queryWrapper.eq(Integral::getIntegralTitle, "签到送积分");
            List<Integral> integrals = baseMapper.selectList(queryWrapper);
            if (integrals.size() > 0) {
                resultMap.put("code", "-1");
                resultMap.put("errorMsg", "今天已经签到过了");
                return resultMap;
            }
            integral.setIntegralTitle("签到送积分");
            integral.setIntegralUpdate("+10.00");
        }
        integral.setIsAdd(true);
        integral.setUserCode(userCode);
        resultMap.put("code", "0");
        baseMapper.insert(integral);
        return resultMap;
    }
}
