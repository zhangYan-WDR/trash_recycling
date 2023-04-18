package com.applet.trash.service.impl;

import com.applet.trash.entity.Address;
import com.applet.trash.mapper.AddressMapper;
import com.applet.trash.service.AddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {
}
