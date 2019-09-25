package com.siti.wisdomhydrologic.mainpage.vo;

import java.util.Date;

/**
 * Created by dell on 2019/8/20.
 */
public class ConfigRiverStationVo {

    private String stationCode;
    private String stationTelemetryCode;
    private int orgId;
    private String orgName;
    private int riverId;
    private String riverName;
    private int regionId;
    private String regionName;
    private String stationWiskiCode;
    //测站级别
    private int stationLevel;
    private double stationGaodeLongitude;
    private double stationGaodeLatitude;
    private int isSluiceGate;
    private String stationAddress;
    private Date createTime;
    private Date updateTime;
    private int stationId;

    private String time;
    private String stationName;
    private String waterLevel;
    private String rainfall;
    private String tideLevel;
    private String electric;
    private String windSpeed;
    private String windDirection;

    private String windDirectionName;
    private String flowVelocityX;
    private String flowVelocityY;
    private String airPressure;
    private String airTemperature;
    //测站状态
    private int status;


    public String getWindDirectionName() {
        return windDirectionName;
    }

    public void setWindDirectionName(String windDirectionName) {
        this.windDirectionName = windDirectionName;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getStationTelemetryCode() {
        return stationTelemetryCode;
    }

    public void setStationTelemetryCode(String stationTelemetryCode) {
        this.stationTelemetryCode = stationTelemetryCode;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public int getRiverId() {
        return riverId;
    }

    public void setRiverId(int riverId) {
        this.riverId = riverId;
    }

    public String getRiverName() {
        return riverName;
    }

    public void setRiverName(String riverName) {
        this.riverName = riverName;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getStationWiskiCode() {
        return stationWiskiCode;
    }

    public void setStationWiskiCode(String stationWiskiCode) {
        this.stationWiskiCode = stationWiskiCode;
    }

    public int getStationLevel() {
        return stationLevel;
    }

    public void setStationLevel(int stationLevel) {
        this.stationLevel = stationLevel;
    }

    public double getStationGaodeLongitude() {
        return stationGaodeLongitude;
    }

    public void setStationGaodeLongitude(double stationGaodeLongitude) {
        this.stationGaodeLongitude = stationGaodeLongitude;
    }

    public double getStationGaodeLatitude() {
        return stationGaodeLatitude;
    }

    public void setStationGaodeLatitude(double stationGaodeLatitude) {
        this.stationGaodeLatitude = stationGaodeLatitude;
    }

    public int getIsSluiceGate() {
        return isSluiceGate;
    }

    public void setIsSluiceGate(int isSluiceGate) {
        this.isSluiceGate = isSluiceGate;
    }

    public String getStationAddress() {
        return stationAddress;
    }

    public void setStationAddress(String stationAddress) {
        this.stationAddress = stationAddress;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
