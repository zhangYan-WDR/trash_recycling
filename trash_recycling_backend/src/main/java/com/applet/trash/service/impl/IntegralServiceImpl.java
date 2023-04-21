package com.applet.trash.service.impl;

import com.applet.trash.entity.Integral;
import com.applet.trash.entity.User;
import com.applet.trash.interceptor.UserInfoHolder;
import com.applet.trash.mapper.IntegralMapper;
import com.applet.trash.service.IntegralService;
import com.applet.trash.service.UserService;
import com.applet.trash.util.DateUtil;
import com.applet.trash.util.OrderNoUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class IntegralServiceImpl extends ServiceImpl<IntegralMapper, Integral> implements IntegralService {


    @Resource
    private UserService userService;

    @Override
    public Map<String,String> saveIntegralLog(String userCode, String type) {
        Map<String, String> resultMap = new HashMap<>();
        //查询当前用户
        LambdaQueryWrapper<User> userWrapper = new LambdaQueryWrapper<>();
        userWrapper.eq(User::getUserCode, UserInfoHolder.userInfo.get());
        User user = userService.getOne(userWrapper);
        Integral integral = new Integral();
        integral.setId(UUID.randomUUID().toString());
        integral.setIntegralNo(OrderNoUtils.getIntegralNO());
        if (type.equals("exam")) {
            integral.setIntegralTitle("考试送积分");
            integral.setIntegralUpdate("+30.00");
            user.setPersonalPoints(user.getPersonalPoints()+30);
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
            user.setPersonalPoints(user.getPersonalPoints()+10);
        }
        integral.setIsAdd(true);
        integral.setUserCode(userCode);
        integral.setCreateTime(new Date());
        baseMapper.insert(integral);
        //用户添加积分
        userService.updateById(user);
        resultMap.put("code", "0");
        return resultMap;
    }

    @Override
    public List<Integral> getListByUser() {
        LambdaQueryWrapper<Integral> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Integral::getCreateTime);
        queryWrapper.eq(Integral::getUserCode, UserInfoHolder.userInfo.get());
        List<Integral> integrals = baseMapper.selectList(queryWrapper);
        return integrals;
    }
}
