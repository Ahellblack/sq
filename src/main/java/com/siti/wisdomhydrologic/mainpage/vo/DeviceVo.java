package com.siti.wisdomhydrologic.mainpage.vo;

/**
 * Created by dell on 2019/8/23.
 */
public class DeviceVo {
    private String device_real_status;
    private String flag;
    private String devid;
    private String temperature;
    private String is_online;
    private String humidity;

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getDevice_real_status() {
        return device_real_status;
    }

    public void setDevice_real_status(String device_real_status) {
        this.device_real_status = device_real_status;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getDevid() {
        return devid;
    }

    public void setDevid(String devid) {
        this.devid = devid;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getIs_online() {
        return is_online;
    }

    public void setIs_online(String is_online) {
        this.is_online = is_online;
    }
}
