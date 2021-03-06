package com.siti.wisdomhydrologic.user.interceptor;

import com.siti.wisdomhydrologic.user.entity.User;
import com.siti.wisdomhydrologic.user.service.UserService;
import com.siti.wisdomhydrologic.util.BASE64Util;
import com.siti.wisdomhydrologic.util.Md5Utils;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String usernameTemp = authentication.getName();
        String logPwd = BASE64Util.decode((String) authentication.getCredentials());
        if (logPwd == null) {
            throw new BadCredentialsException("登录失败，请重新登录！");
        }
        String username;
        String password;
        //String pushId = null;
        if (logPwd.endsWith("#THT#")) {
            System.out.println("跳转登录");
            username = usernameTemp;
            password = logPwd.substring(0, logPwd.length() - 5);
        } else {
            System.out.println("普通登录");
            String pwd = logPwd;
            if (pwd == null) {
                throw new BadCredentialsException("登录失败，请重新登录！");
            }
            if (usernameTemp.contains("&")) {
                username = usernameTemp.substring(0, usernameTemp.lastIndexOf("&"));
            } else {
                username = usernameTemp;
            }
            password = Md5Utils.encryptString(pwd);
        }
        //获取登录用户的用户信息
        User userInfo = (User) userService.loadUserByUsername(username);
        if (userInfo == null || "".equals(username)) {
            throw new BadCredentialsException("用户不存在！");
        } else if ("".equals(username) || userInfo.getStatus() == 0) {
            throw new BadCredentialsException("用户不存在！");
        } else if (userInfo.getStatus() == 2) {
            throw new BadCredentialsException("用户已被冻结！缘由：" + (userInfo.getRemark() == null ? "" : userInfo.getRemark()));
        } else {
            // 验证密码是否正确
            if (!userInfo.getPassword().equals(password)) {
                throw new BadCredentialsException("用户名或密码错误！");
            }
        }
        userInfo.setPassword(null);
        Collection<? extends GrantedAuthority> authorities = userInfo.getAuthorities();
        return new UsernamePasswordAuthenticationToken(userInfo, logPwd, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

}
