package com.siti.wisdomhydrologic.user.interceptor;

import com.siti.wisdomhydrologic.config.ConstantConfig;
import com.siti.wisdomhydrologic.user.service.RedisBiz;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import ytx.org.apache.http.client.HttpResponseException;
import java.util.List;
import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@Component
public class SecurityInteceptor implements HandlerInterceptor {
    @Autowired
    private RedisBiz redisBiz;
    private static ApplicationContext context = null;
    public static <T> T getBean(Class<T> requiredType) {
        return context.getBean(requiredType);
    }
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        /*redisBiz = getBean(RedisBiz.class);
        httpServletResponse.setContentType("text/html;charset=UTF-8");
        HttpSession session = httpServletRequest.getSession();
        //System.out.println(session.getId());
        if(!redisBiz.exists(session.getId())){

            httpServletResponse.getWriter().write("请先完成登陆！");
            //返回401 给前台, 跳转默认地址
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            return false;
        }*/
        //System.out.println(httpServletRequest.getRequestURL());
        httpServletRequest.setCharacterEncoding("UTF-8");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/html;charset=UTF-8");
        //if(!httpServletRequest.getRequestURL().toString().contains("login")){
        Object obj = httpServletRequest.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        if (obj == null) {
            String url = "http://" + httpServletRequest.getLocalAddr() + ":" + httpServletRequest.getLocalPort() + httpServletRequest.getContextPath() + "/login";
            //ajax请求超时判断
            String requestType = httpServletRequest.getHeader("X-Requested-With");
            if ((requestType != null) && requestType.equalsIgnoreCase("XMLHttpRequest")) {
                httpServletResponse.setHeader("sessionstatus", "timeout");
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle...");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("afterCompletion...");
    }

    /*@Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }*/
}
