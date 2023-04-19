package com.applet.trash.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.applet.trash.Do.SaveAddressDo;
import com.applet.trash.Do.UpdateAddressDo;
import com.applet.trash.entity.Address;
import com.applet.trash.interceptor.UserInfoHolder;
import com.applet.trash.mapper.AddressMapper;
import com.applet.trash.service.AddressService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

    @Override
    public Address saveAddressByUserCode(SaveAddressDo saveAddressDo) {
        Address address = new Address();
        BeanUtil.copyProperties(saveAddressDo,address);
        address.setId(UUID.randomUUID().toString());
        address.setUserCode(UserInfoHolder.userInfo.get());
        baseMapper.insert(address);
        return address;
    }

    @Override
    public List<Address> getAddressByUserCode() {
        LambdaQueryWrapper<Address> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Address::getUserCode, UserInfoHolder.userInfo.get());
        List<Address> addresses = baseMapper.selectList(wrapper);
        return addresses;
    }

    @Override
    public Address updateAddress(UpdateAddressDo updateAddressDo) {
        Address address = new Address();
        BeanUtil.copyProperties(updateAddressDo,address);
        baseMapper.updateById(address);
        return address;
    }
}
