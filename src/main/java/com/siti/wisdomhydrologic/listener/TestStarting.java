/*
package com.siti.wisdomhydrologic.listener;

import com.siti.wisdomhydrologic.datepull.service.impl.TSDBServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;
import java.util.List;

*/
/**
 * Created by dell on 2019/8/9.
 *//*

@Component
public class TestStarting implements ServletContextListener {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private TSDBServiceImpl tsdbService;


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        List<String> hourlist = new ArrayList<>();
        logger.info("启动时获取HISTORY信息列表： ");
        try {
            hourlist = tsdbService;
        } catch (Exception e) {
            System.out.println(" 启动时获取街镇信息列表出错...");
        }


    }


    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
*/
