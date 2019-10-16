package com.siti.wisdomhydrologic.document.vo;

/**
 * Created by dell on 2019/10/16.
 */
public class RainMaxVo {

    private String stationName;
    private String stationCode;
    private Double maxV;
    private String sensorDataUploadTime;

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public Double getMax() {
        return maxV;
    }

    public void setMax(Double maxV) {
        this.maxV = maxV;
    }

    public String getSensorDataUploadTime() {
        return sensorDataUploadTime;
    }

    public void setSensorDataUploadTime(String sensorDataUploadTime) {
        this.sensorDataUploadTime = sensorDataUploadTime;
    }
}
