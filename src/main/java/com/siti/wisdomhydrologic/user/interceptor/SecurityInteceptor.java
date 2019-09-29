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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@Component
public class SecurityInteceptor implements HandlerInterceptor,ApplicationContextAware {
    @Autowired
    private RedisBiz redisBiz;
    private static ApplicationContext context = null;
    public static <T> T getBean(Class<T> requiredType) {
        return context.getBean(requiredType);
    }
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        redisBiz = getBean(RedisBiz.class);
        httpServletResponse.setContentType("text/html;charset=UTF-8");
        HttpSession session = httpServletRequest.getSession();
        //System.out.println(session.getId());
        if(!redisBiz.exists(session.getId())){

            httpServletResponse.getWriter().write("请先完成登陆！");
            //httpServletResponse.sendRedirect(ConstantConfig.LOGINLOCATIONTEST);
            //throw new System.ServiceModel.Web.WebFaultException<String>("\"mes\":\"认证信息失效\"", System.Net.HttpStatusCode.Unauthorized);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
