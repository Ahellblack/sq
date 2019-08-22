package com.siti.wisdomhydrologic.mainpage.vo;

import javax.persistence.Id;
import java.util.Date;

/**
 * Created by dell on 2019/8/15.
 */
public class RealStationVo {

    @Id
    private int sensorCode;
    @Id
    private Date time;
    private Double realVal;
    private Integer modified;
    private Integer cycle;
    private Integer state;
    private Integer ts;

    /**
     * 测站
     * */
    private String stationCode;
    private String stationTelemetryCode;
    private String stationName;
    private int orgId;
    private String orgName;
    private int riverId;
    private String riverName;
    private int regionId;
    private String regionName;
    private String stationWiskiCode;
    private int stationLevel;
    private double stationGaodeLongitude;
    private double stationGaodeLatitude;
    private int isSluiceGate;
    private String stationAddress;
    private Date createTime;
    private Date updateTime;
    private int StationId;
    //测站状态
    private int status;

    private double realDataWaterLevel;
    private double realDataTideLevel;
    private double realDataFlowX;
    private double realDataFlowY;
    private double realDataRainFall;
    private double realDataAirPressure;
    private double realDataAirTemperature;
    private double realDataWindDirection;
    private double realDataWindSpeed;
    private double realDataElectric;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getRealDataWaterLevel() {
        return realDataWaterLevel;
    }

    public void setRealDataWaterLevel(double realDataWaterLevel) {
        this.realDataWaterLevel = realDataWaterLevel;
    }

    public double getRealDataTideLevel() {
        return realDataTideLevel;
    }

    public void setRealDataTideLevel(double realDataTideLevel) {
        this.realDataTideLevel = realDataTideLevel;
    }

    public double getRealDataFlowX() {
        return realDataFlowX;
    }

    public void setRealDataFlowX(double realDataFlowX) {
        this.realDataFlowX = realDataFlowX;
    }

    public double getRealDataFlowY() {
        return realDataFlowY;
    }

    public void setRealDataFlowY(double realDataFlowY) {
        this.realDataFlowY = realDataFlowY;
    }

    public double getRealDataRainFall() {
        return realDataRainFall;
    }

    public void setRealDataRainFall(double realDataRainFall) {
        this.realDataRainFall = realDataRainFall;
    }

    public double getRealDataAirPressure() {
        return realDataAirPressure;
    }

    public int getStationId() {
        return StationId;
    }

    public void setStationId(int stationId) {
        StationId = stationId;
    }

    public void setRealDataAirPressure(double realDataAirPressure) {
        this.realDataAirPressure = realDataAirPressure;
    }

    public double getRealDataAirTemperature() {
        return realDataAirTemperature;
    }

    public void setRealDataAirTemperature(double realDataAirTemperature) {
        this.realDataAirTemperature = realDataAirTemperature;
    }

    public double getRealDataWindDirection() {
        return realDataWindDirection;
    }

    public void setRealDataWindDirection(double realDataWindDirection) {
        this.realDataWindDirection = realDataWindDirection;
    }

    public double getRealDataWindSpeed() {
        return realDataWindSpeed;
    }

    public void setRealDataWindSpeed(double realDataWindSpeed) {
        this.realDataWindSpeed = realDataWindSpeed;
    }

    public double getRealDataElectric() {
        return realDataElectric;
    }

    public void setRealDataElectric(double realDataElectric) {
        this.realDataElectric = realDataElectric;
    }

    public int getSensorCode() {
        return sensorCode;
    }

    public void setSensorCode(int sensorCode) {
        this.sensorCode = sensorCode;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Double getRealVal() {
        return realVal;
    }

    public void setRealVal(Double realVal) {
        this.realVal = realVal;
    }

    public Integer getModified() {
        return modified;
    }

    public void setModified(Integer modified) {
        this.modified = modified;
    }

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getTs() {
        return ts;
    }

    public void setTs(Integer ts) {
        this.ts = ts;
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

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
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


    @Override
    public String toString() {
        return "RealStationVo{" + "time=" + time + ", stationName='" + stationName + '\'' + ", realDataWaterLevel=" + realDataWaterLevel + ", realDataTideLevel=" + realDataTideLevel + ", realDataFlowX=" + realDataFlowX + ", realDataFlowY=" + realDataFlowY + ", realDataRainFall=" + realDataRainFall + ", realDataAirPressure=" + realDataAirPressure + ", realDataAirTemperature=" + realDataAirTemperature + ", realDataWindDirection=" + realDataWindDirection + ", realDataWindSpeed=" + realDataWindSpeed + ", realDataElectric=" + realDataElectric + '}';
    }
}
