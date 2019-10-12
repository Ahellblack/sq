package com.siti.wisdomhydrologic.configwarning.entity;


public class AbnormalElectric {

  private long id;
  private String sensorCode;
  private String sensorName;
  private long interruptLimit;
  private double levelMax;
  private double levelMin;
  private double upMax;
  private double belowMax;
  private long duration;
  private String exceptionValue;


  public long getId() {
    return id;
  }

  public void setId(long id) {
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


  public long getInterruptLimit() {
    return interruptLimit;
  }

  public void setInterruptLimit(long interruptLimit) {
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


  public double getBelowMax() {
    return belowMax;
  }

  public void setBelowMax(double belowMax) {
    this.belowMax = belowMax;
  }


  public long getDuration() {
    return duration;
  }

  public void setDuration(long duration) {
    this.duration = duration;
  }


  public String getExceptionValue() {
    return exceptionValue;
  }

  public void setExceptionValue(String exceptionValue) {
    this.exceptionValue = exceptionValue;
  }

}
