package com.siti.wisdomhydrologic.configwarning.entity;


public class AbnormalWindSpeed {

  private long id;
  private String sensorCode;
  private String sensorName;
  private long interruptLimit;
  private String levelMax;
  private String levelMin;
  private String upMax;
  private String belowMax;
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


  public String getLevelMax() {
    return levelMax;
  }

  public void setLevelMax(String levelMax) {
    this.levelMax = levelMax;
  }


  public String getLevelMin() {
    return levelMin;
  }

  public void setLevelMin(String levelMin) {
    this.levelMin = levelMin;
  }


  public String getUpMax() {
    return upMax;
  }

  public void setUpMax(String upMax) {
    this.upMax = upMax;
  }


  public String getBelowMax() {
    return belowMax;
  }

  public void setBelowMax(String belowMax) {
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
