package com.applet.trash.interceptor;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserInfoInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userCode = request.getHeader("userCode");
        if (StringUtils.isNotBlank(userCode)) {
            UserInfoHolder.userInfo.set(userCode);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserInfoHolder.userInfo.remove();
    }

}
