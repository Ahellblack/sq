package com.siti.wisdomhydrologic.user.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by DC on 2019/8/19.
 *
 * @data ${DATA}-15:32
 */
public interface WHRBACService {
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
