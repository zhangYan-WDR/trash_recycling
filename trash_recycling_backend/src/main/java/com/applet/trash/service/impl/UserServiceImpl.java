package com.applet.trash.service.impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.applet.trash.entity.User;
import com.applet.trash.mapper.UserMapper;
import com.applet.trash.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Value("${wx.miniapp.appid}")
    private String appId;

    @Value("${wx.miniapp.appsecret}")
    private String appSecret;

    //https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code

    @Override
    public Map<String, String> login(String code) {
        String baseUrl = "https://api.weixin.qq.com/sns/jscode2session?";
        String url = baseUrl.concat("appid=").concat(appId).concat("&secret=").concat(appSecret).concat("&js_code=").concat(code).concat("&grant_type=authorization_code");
        log.info("当前请求小程序登录的url为:-->{}",url);
        String json = HttpUtil.get(url);
        /**
         * {
         * "openid":"xxxxxx",
         * "session_key":"xxxxx",
         * "unionid":"xxxxx",
         * "errcode":0,
         * "errmsg":"xxxxx"
         * }
         */
        JSONObject loginObject = JSONObject.parseObject(json);
        Map<String, String> result = new HashMap<>();
        String openid = (String) loginObject.get("openid");
        String sessionKey = (String) loginObject.get("session_key");
        String unionid = (String) loginObject.get("unionid");
        result.put("openid", openid);
        result.put("session_key", sessionKey);
        result.put("unionid", unionid);
        //查询登录用户信息
        User user = saveOrUpdateUserByOpenId(openid);
        Gson gson = new Gson();
        String userJson = gson.toJson(user);
        result.put("user", userJson);
        return result;
    }

    private User saveOrUpdateUserByOpenId(String openid) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserCode, openid);
        User user = baseMapper.selectOne(wrapper);
        if (user == null) {
            //用户首次登录需要入库
            user = new User();
            user.setId(UUID.randomUUID().toString());
            user.setUserCode(openid);
            //TODO 查询用户名称和用户手机号入库
            user.setUserName("");
            user.setPhone("");
            user.setPersonalPoints(0);
            baseMapper.insert(user);
        }
        return user;
    }

    @Override
    public String getPhoneByUser(String code) {
        String baseUrl = "https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token=ACCESS_TOKEN";
        Map<String, String> bodyMap = new HashMap<>();
        bodyMap.put("code", code);
        Gson gson = new Gson();
        String bodyJson = gson.toJson(bodyMap);
        String responseJson = HttpUtil.post(baseUrl, bodyJson);
        return responseJson;
    }

    @Override
    public void addPointByExam(String userCode) {
        //查询当前用户的积分
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserCode, userCode);
        User user = baseMapper.selectOne(wrapper);
        Integer personalPoints = user.getPersonalPoints();
        //考试积分添加30
        personalPoints = personalPoints + 30;
        user.setPersonalPoints(personalPoints);
        baseMapper.updateById(user);
    }
}
