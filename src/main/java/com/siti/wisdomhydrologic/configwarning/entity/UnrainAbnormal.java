package com.siti.wisdomhydrologic.configwarning.entity;

/**
 * Created by zyw on 2019/10/12.
 */
public class UnrainAbnormal {

    private Integer id;
    private String sensorCode;
    private String sensorName;
    private Integer interruptLimit;
    private double levelMax;
    private double levelMin;
    private double upMax;
    private double downMax;
    private Integer duration;
    private String exceptionValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getSensorCode() {
        return sensorCode;
    }

    public void setSensorCode(String sensorCode) {
        this.sensorCode = sensorCode;
    }


    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }


    public Integer getInterruptLimit() {
        return interruptLimit;
    }

    public void setInterruptLimit(Integer interruptLimit) {
        this.interruptLimit = interruptLimit;
    }


    public double getLevelMax() {
        return levelMax;
    }

    public void setLevelMax(double levelMax) {
        this.levelMax = levelMax;
    }


    public double getLevelMin() {
        return levelMin;
    }

    public void setLevelMin(double levelMin) {
        this.levelMin = levelMin;
    }


    public double getUpMax() {
        return upMax;
    }

    public void setUpMax(double upMax) {
        this.upMax = upMax;
    }

    public double getDownMax() {
        return downMax;
    }

    public void setDownMax(double downMax) {
        this.downMax = downMax;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }


    public String getExceptionValue() {
        return exceptionValue;
    }

    public void setExceptionValue(String exceptionValue) {
        this.exceptionValue = exceptionValue;
    }

}


