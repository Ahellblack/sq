package com.siti.wisdomhydrologic.mainpage.vo;

import java.util.Map;

/**
 * Created by zyw on 2019/9/5.
 */
public class WeatherVo {

    private String data;

    private String cityName;
    private String updateTime;
    private String currentTemp;
    private String currentShidu;
    private String currentWindOrient;
    private String currentRiseTime;
    private String currentDownTime;

    private WeatherVo[] forcast;

    private String date;
    private String highTemp;
    private String lowTemp;
    private Map<String,Object> day;
    private Map<String,Object> night;
    private String type;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(String currentTemp) {
        this.currentTemp = currentTemp;
    }

    public String getCurrentShidu() {
        return currentShidu;
    }

    public void setCurrentShidu(String currentShidu) {
        this.currentShidu = currentShidu;
    }

    public String getCurrentWindOrient() {
        return currentWindOrient;
    }

    public void setCurrentWindOrient(String currentWindOrient) {
        this.currentWindOrient = currentWindOrient;
    }

    public String getCurrentRiseTime() {
        return currentRiseTime;
    }

    public void setCurrentRiseTime(String currentRiseTime) {
        this.currentRiseTime = currentRiseTime;
    }

    public String getCurrentDownTime() {
        return currentDownTime;
    }

    public void setCurrentDownTime(String currentDownTime) {
        this.currentDownTime = currentDownTime;
    }

    public WeatherVo[] getForcast() {
        return forcast;
    }

    public void setForcast(WeatherVo[] forcast) {
        this.forcast = forcast;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHighTemp() {
        return highTemp;
    }

    public void setHighTemp(String highTemp) {
        this.highTemp = highTemp;
    }

    public String getLowTemp() {
        return lowTemp;
    }

    public void setLowTemp(String lowTemp) {
        this.lowTemp = lowTemp;
    }

    public Map<String, Object> getDay() {
        return day;
    }

    public void setDay(Map<String, Object> day) {
        this.day = day;
    }

    public Map<String, Object> getNight() {
        return night;
    }

    public void setNight(Map<String, Object> night) {
        this.night = night;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
