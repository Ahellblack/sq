package com.siti.wisdomhydrologic.configmaintain.entity;


public class ConfigRiverStation {

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
    //测站级别
    private int stationLevel;
    private double stationGaodeLongitude;
    private double stationGaodeLatitude;
    private int isSluiceGate;
    private String stationAddress;
    private String createTime;
    private String updateTime;
    private int stationId;
    //测站状态
    private int status;

    //分中心id
    private int sysOrg;

    private int flowRate;

    private int patencyRate;

    private String time;

    private String buildingTime;
    private String abbreviation;
    private String numberCdma;
    private String numberGprs;


    private String haveTide;
    private String haveWaterLevel;
    private String havaRainfall;
    private String haveFlowVelocity;
    private String havaWind;
    private String havaAirPressure;
    private String havaAirTemperature;
    private String havaEvaporation;
    private String havaDT350;
    private String havaWaterTemperature;
    private String havaWatertableLever;
    private String velocityDirect;

    public String getHaveTide() {
        return haveTide;
    }

    public void setHaveTide(String haveTide) {
        this.haveTide = haveTide;
    }

    public String getHaveWaterLevel() {
        return haveWaterLevel;
    }

    public void setHaveWaterLevel(String haveWaterLevel) {
        this.haveWaterLevel = haveWaterLevel;
    }

    public String getHavaRainfall() {
        return havaRainfall;
    }

    public void setHavaRainfall(String havaRainfall) {
        this.havaRainfall = havaRainfall;
    }

    public String getHaveFlowVelocity() {
        return haveFlowVelocity;
    }

    public void setHaveFlowVelocity(String haveFlowVelocity) {
        this.haveFlowVelocity = haveFlowVelocity;
    }

    public String getHavaWind() {
        return havaWind;
    }

    public void setHavaWind(String havaWind) {
        this.havaWind = havaWind;
    }

    public String getHavaAirPressure() {
        return havaAirPressure;
    }

    public void setHavaAirPressure(String havaAirPressure) {
        this.havaAirPressure = havaAirPressure;
    }

    public String getHavaAirTemperature() {
        return havaAirTemperature;
    }

    public void setHavaAirTemperature(String havaAirTemperature) {
        this.havaAirTemperature = havaAirTemperature;
    }

    public String getHavaEvaporation() {
        return havaEvaporation;
    }

    public void setHavaEvaporation(String havaEvaporation) {
        this.havaEvaporation = havaEvaporation;
    }

    public String getHavaDT350() {
        return havaDT350;
    }

    public void setHavaDT350(String havaDT350) {
        this.havaDT350 = havaDT350;
    }

    public String getHavaWaterTemperature() {
        return havaWaterTemperature;
    }

    public void setHavaWaterTemperature(String havaWaterTemperature) {
        this.havaWaterTemperature = havaWaterTemperature;
    }

    public String getHavaWatertableLever() {
        return havaWatertableLever;
    }

    public void setHavaWatertableLever(String havaWatertableLever) {
        this.havaWatertableLever = havaWatertableLever;
    }

    public String getVelocityDirect() {
        return velocityDirect;
    }

    public void setVelocityDirect(String velocityDirect) {
        this.velocityDirect = velocityDirect;
    }

    public String getBuildingTime() {
        return buildingTime;
    }

    public void setBuildingTime(String buildingTime) {
        this.buildingTime = buildingTime;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getNumberCdma() {
        return numberCdma;
    }

    public void setNumberCdma(String numberCdma) {
        this.numberCdma = numberCdma;
    }

    public String getNumberGprs() {
        return numberGprs;
    }

    public void setNumberGprs(String numberGprs) {
        this.numberGprs = numberGprs;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPatencyRate() {
        return patencyRate;
    }

    public void setPatencyRate(int patencyRate) {
        this.patencyRate = patencyRate;
    }

    public int getFlowRate() {
        return flowRate;
    }

    public void setFlowRate(int flowRate) {
        this.flowRate = flowRate;
    }

    @Override
    public String toString() {
        return "ConfigRiverStation{" + "stationCode='" + stationCode + '\'' + ", stationTelemetryCode='" + stationTelemetryCode + '\'' + ", stationName='" + stationName + '\'' + ", orgId=" + orgId + ", orgName='" + orgName + '\'' + ", riverId=" + riverId + ", riverName='" + riverName + '\'' + ", regionId=" + regionId + ", regionName='" + regionName + '\'' + ", stationWiskiCode='" + stationWiskiCode + '\'' + ", stationLevel=" + stationLevel + ", stationGaodeLongitude=" + stationGaodeLongitude + ", stationGaodeLatitude=" + stationGaodeLatitude + ", isSluiceGate=" + isSluiceGate + ", stationAddress='" + stationAddress + '\'' + ", createTime='" + createTime + '\'' + ", updateTime='" + updateTime + '\'' + ", stationId=" + stationId + ", status=" + status + ", sysOrg=" + sysOrg + '}';
    }

    public int getSysOrg() {
        return sysOrg;
    }

    public void setSysOrg(int sysOrg) {
        this.sysOrg = sysOrg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
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


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

}
