package com.siti.wisdomhydrologic.mainpage.entity;

/**
 * Created by dell on 2019/8/22.
 */
public class HaiKangResult {

    private String data;
    private String accessToken;
    private String expireTime;
    private String code;
    private String msg;
    private String fluencyEzopen;
    private String highDefinitionEzopen;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "HaiKangResult{" + "data='" + data + '\'' + ", accessToken='" + accessToken + '\'' + ", expireTime='" + expireTime + '\'' + ", code='" + code + '\'' + ", msg='" + msg + '\'' + '}';
    }
}
