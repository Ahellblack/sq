package com.siti.wisdomhydrologic.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by dell on 2019/8/7.
 */


@Configuration
public class HaiKangVideoConfig {

    @Value("${wh.appKey}")
    private String appKey;

    @Value("${wh.appSecret}")
    private String appSecret;

    @Value("${wh.fluencyEzopen}")
    private String fluencyEzopen;

    @Value("${wh.highDefinitionEzopen}")
    private String highDefinitionEzopen;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getFluencyEzopen() {
        return fluencyEzopen;
    }

    public void setFluencyEzopen(String fluencyEzopen) {
        this.fluencyEzopen = fluencyEzopen;
    }

    public String getHighDefinitionEzopen() {
        return highDefinitionEzopen;
    }

    public void setHighDefinitionEzopen(String highDefinitionEzopen) {
        this.highDefinitionEzopen = highDefinitionEzopen;
    }

    @Override
    public String toString() {
        return "HaiKangVideoConfig{" + "appKey='" + appKey + '\'' + ", appSecret='" + appSecret + '\'' + ", fluencyEzopen='" + fluencyEzopen + '\'' + ", highDefinitionEzopen='" + highDefinitionEzopen + '\'' + '}';
    }
}
