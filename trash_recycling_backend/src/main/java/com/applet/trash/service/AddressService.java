package com.applet.trash.service;

import com.applet.trash.Do.SaveAddressDo;
import com.applet.trash.Do.UpdateAddressDo;
import com.applet.trash.entity.Address;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface AddressService extends IService<Address> {
    Address saveAddressByUserCode(SaveAddressDo saveAddressDo);

    List<Address> getAddressByUserCode();

    Address updateAddress(UpdateAddressDo updateAddressDo);
}
