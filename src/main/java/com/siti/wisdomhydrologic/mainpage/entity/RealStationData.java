package com.siti.wisdomhydrologic.mainpage.entity;

public class RealStationData {

  private String time;
  private int stationId;
  private String stationName;
  private double waterLevel;
  private double rainfall;
  private double tideLevel;
  private double electric;
  private double windSpeed;
  private String windDirection;
  private double flowVelocityX;
  private double flowVelocityY;
  private double airPressure;
  private double airTemperature;
  private String status;
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


  public double getWaterLevel() {
    return waterLevel;
  }

  public void setWaterLevel(double waterLevel) {
    this.waterLevel = waterLevel;
  }


  public double getRainfall() {
    return rainfall;
  }

  public void setRainfall(double rainfall) {
    this.rainfall = rainfall;
  }


  public double getTideLevel() {
    return tideLevel;
  }

  public void setTideLevel(double tideLevel) {
    this.tideLevel = tideLevel;
  }


  public double getElectric() {
    return electric;
  }

  public void setElectric(double electric) {
    this.electric = electric;
  }


  public double getWindSpeed() {
    return windSpeed;
  }

  public void setWindSpeed(double windSpeed) {
    this.windSpeed = windSpeed;
  }


  public String getWindDirection() {
    return windDirection;
  }

  public void setWindDirection(String windDirection) {
    this.windDirection = windDirection;
  }


  public double getFlowVelocityX() {
    return flowVelocityX;
  }

  public void setFlowVelocityX(double flowVelocityX) {
    this.flowVelocityX = flowVelocityX;
  }


  public double getFlowVelocityY() {
    return flowVelocityY;
  }

  public void setFlowVelocityY(double flowVelocityY) {
    this.flowVelocityY = flowVelocityY;
  }


  public double getAirPressure() {
    return airPressure;
  }

  public void setAirPressure(double airPressure) {
    this.airPressure = airPressure;
  }


  public double getAirTemperature() {
    return airTemperature;
  }

  public void setAirTemperature(double airTemperature) {
    this.airTemperature = airTemperature;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

}
