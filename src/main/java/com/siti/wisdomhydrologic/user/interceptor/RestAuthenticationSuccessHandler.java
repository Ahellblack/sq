package com.siti.wisdomhydrologic.user.interceptor;

import com.alibaba.fastjson.JSON;
import com.siti.wisdomhydrologic.user.entity.User;
import com.siti.wisdomhydrologic.user.mapper.LoginLogMapper;
import com.siti.wisdomhydrologic.util.DateOrTimeTrans;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class RestAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    @Autowired
    LoginLogMapper loginLogMapper;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //System.out.println(request.getCookies()[0].getName() + "-" + request.getCookies()[0].getValue());
        //response.addHeader(request.getCookies()[0].getName(), request.getCookies()[0].getValue());

        //获得授权后可得到用户信息
        User user = (User) authentication.getPrincipal();
        //输出登录提示信息
        String ipAddress = getIpAddress(request);
        String userAgent = request.getHeader("User-Agent"); // 请求信息（终端类型(1Android,2iPhone,3iPad,4PC)）
        loginLogMapper.saveLoginLog(user.getId(),user.getUserName(),ipAddress,userAgent,(DateOrTimeTrans.nowTimetoString()));

        response.setCharacterEncoding("UTF-8");
        Map<String, Object> map = new HashMap<>();
        map.put("status", "success");
        //map.put("cookieName", request.getCookies()[0].getName());//JSESSIONID
        //map.put("cookieValue", request.getCookies()[0].getValue());//48F3BC2ED285777FE4DE33EFAFC73473
        map.put("info", authentication.getPrincipal());
        String authstr = JSON.toJSONString(map);
        response.getWriter().write(authstr);
        //response.getWriter().write("sucess");
    }

    private String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
