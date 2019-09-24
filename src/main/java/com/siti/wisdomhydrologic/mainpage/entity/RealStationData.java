package com.siti.wisdomhydrologic.mainpage.entity;

public class RealStationData {

  private String time;
  private int stationId;
  private String stationName;
  private String waterLevel;
  private String rainfall;
  private String tideLevel;
  private String electric;
  private String windSpeed;
  private String windDirection;
  private String flowVelocityX;
  private String flowVelocityY;
  private String airPressure;
  private String airTemperature;
  private Integer status;
  private double patencyRate;
  //测站的畅通率限制
  private double flowRate;
  private int unPatencyNumber;


  public int getUnPatencyNumber() {
    return unPatencyNumber;
  }

  public void setUnPatencyNumber(int unPatencyNumber) {
    this.unPatencyNumber = unPatencyNumber;
  }

  public double getFlowRate() {
    return flowRate;
  }

  public void setFlowRate(double flowRate) {
    this.flowRate = flowRate;
  }

  public double getPatencyRate() {
    return patencyRate;
  }

  public void setPatencyRate(double patencyRate) {
    this.patencyRate = patencyRate;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }


  public int getStationId() {
    return stationId;
  }

  public void setStationId(int stationId) {
    this.stationId = stationId;
  }


  public String getStationName() {
    return stationName;
  }

  public void setStationName(String stationName) {
    this.stationName = stationName;
  }


  public String getWaterLevel() {
    return waterLevel;
  }

  public void setWaterLevel(String waterLevel) {
    this.waterLevel = waterLevel;
  }

  public String getRainfall() {
    return rainfall;
  }

  public void setRainfall(String rainfall) {
    this.rainfall = rainfall;
  }

  public String getTideLevel() {
    return tideLevel;
  }

  public void setTideLevel(String tideLevel) {
    this.tideLevel = tideLevel;
  }

  public String getElectric() {
    return electric;
  }

  public void setElectric(String electric) {
    this.electric = electric;
  }

  public String getWindSpeed() {
    return windSpeed;
  }

  public void setWindSpeed(String windSpeed) {
    this.windSpeed = windSpeed;
  }

  public String getWindDirection() {
    return windDirection;
  }

  public void setWindDirection(String windDirection) {
    this.windDirection = windDirection;
  }

  public String getFlowVelocityX() {
    return flowVelocityX;
  }

  public void setFlowVelocityX(String flowVelocityX) {
    this.flowVelocityX = flowVelocityX;
  }

  public String getFlowVelocityY() {
    return flowVelocityY;
  }

  public void setFlowVelocityY(String flowVelocityY) {
    this.flowVelocityY = flowVelocityY;
  }

  public String getAirPressure() {
    return airPressure;
  }

  public void setAirPressure(String airPressure) {
    this.airPressure = airPressure;
  }

  public String getAirTemperature() {
    return airTemperature;
  }

  public void setAirTemperature(String airTemperature) {
    this.airTemperature = airTemperature;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }
}
