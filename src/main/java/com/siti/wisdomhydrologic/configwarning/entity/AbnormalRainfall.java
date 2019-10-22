package com.siti.wisdomhydrologic.configwarning.entity;


public class AbnormalRainfall {

  private long id;
  private String sensorCode;
  private String sensorName;
  private long interruptLimit;
  private double maxDayLevel;
  private double minDayLevel;
  private double maxHourLevel;
  private double minHourLevel;
  private double maxFiveLevel;
  private double minFiveLevel;
  private String nearbySensorCode;
  private double nearbyRate;
  private String exceptionValue;
  private String nearbySensorName;

  public String getNearbySensorName() {
    return nearbySensorName;
  }

  public void setNearbySensorName(String nearbySensorName) {
    this.nearbySensorName = nearbySensorName;
  }

  @Override
  public String toString() {
    return "AbnormalRainfall{" + "id=" + id + ", sensorCode='" + sensorCode + '\'' + ", sensorName='" + sensorName + '\'' + ", interruptLimit=" + interruptLimit + ", maxDayLevel=" + maxDayLevel + ", minDayLevel=" + minDayLevel + ", maxHourLevel=" + maxHourLevel + ", minHourLevel=" + minHourLevel + ", maxFiveLevel=" + maxFiveLevel + ", minFiveLevel=" + minFiveLevel + ", nearbySensorCode='" + nearbySensorCode + '\'' + ", nearbyRate=" + nearbyRate + ", exceptionValue='" + exceptionValue + '\'' + ", nearbySensorName='" + nearbySensorName + '\'' + '}';
  }

  public String getExceptionValue() {
    return exceptionValue;
  }

  public void setExceptionValue(String exceptionValue) {
    this.exceptionValue = exceptionValue;
  }

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


  public double getMaxDayLevel() {
    return maxDayLevel;
  }

  public void setMaxDayLevel(double maxDayLevel) {
    this.maxDayLevel = maxDayLevel;
  }


  public double getMinDayLevel() {
    return minDayLevel;
  }

  public void setMinDayLevel(double minDayLevel) {
    this.minDayLevel = minDayLevel;
  }


  public double getMaxHourLevel() {
    return maxHourLevel;
  }

  public void setMaxHourLevel(double maxHourLevel) {
    this.maxHourLevel = maxHourLevel;
  }


  public double getMinHourLevel() {
    return minHourLevel;
  }

  public void setMinHourLevel(double minHourLevel) {
    this.minHourLevel = minHourLevel;
  }


  public double getMaxFiveLevel() {
    return maxFiveLevel;
  }

  public void setMaxFiveLevel(double maxFiveLevel) {
    this.maxFiveLevel = maxFiveLevel;
  }


  public double getMinFiveLevel() {
    return minFiveLevel;
  }

  public void setMinFiveLevel(double minFiveLevel) {
    this.minFiveLevel = minFiveLevel;
  }


  public String getNearbySensorCode() {
    return nearbySensorCode;
  }

  public void setNearbySensorCode(String nearbySensorCode) {
    this.nearbySensorCode = nearbySensorCode;
  }


  public double getNearbyRate() {
    return nearbyRate;
  }

  public void setNearbyRate(double nearbyRate) {
    this.nearbyRate = nearbyRate;
  }

}
