package com.applet.trash.controller;

import com.applet.trash.Do.SaveAddressDo;
import com.applet.trash.Do.UpdateAddressDo;
import com.applet.trash.entity.Address;
import com.applet.trash.service.AddressService;
import com.applet.trash.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/address")
@Api(tags = "地址相关接口")
@CrossOrigin
public class AddressController {

    @Resource
    private AddressService addressService;

    @PostMapping("/save")
    @ApiOperation("保存地址")
    public R saveAddress(@RequestBody SaveAddressDo saveAddressDo){
        Address address = addressService.saveAddressByUserCode(saveAddressDo);
        return R.ok().data("address",address).setMessage("添加成功");
    }

    @GetMapping("/list")
    @ApiOperation("查询当前用户下的地址")
    public R getAddressByUserCode(){
        List<Address> list = addressService.getAddressByUserCode();
        return R.ok().data("list",list).setMessage("查询成功");
    }

    @PostMapping("/update")
    @ApiOperation("修改地址")
    public R saveAddress(@RequestBody UpdateAddressDo updateAddressDo){
        Address address = addressService.updateAddress(updateAddressDo);
        return R.ok().data("address",address).setMessage("修改成功");
    }

}
