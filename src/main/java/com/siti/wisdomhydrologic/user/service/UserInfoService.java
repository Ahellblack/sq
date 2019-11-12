package com.siti.wisdomhydrologic.user.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserInfoService {

    /**
     * 获取用户信息数据
     * @return Object
     */
    public Object get() {

        if (SecurityContextHolder.getContext().getAuthentication() == null) {return null;}
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}