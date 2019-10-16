package com.siti.wisdomhydrologic.document.vo;

/**
 * Created by dell on 2019/10/16.
 */
public class WaterGroupVo {

    Double maxV;
    Double minV;
    Double avgV;
    String StationName;
    String stationCode;

    public Double getMinV() {
        return minV;
    }

    public void setMinV(Double minV) {
        this.minV = minV;
    }

    public Double getAvgV() {
        return avgV;
    }

    public void setAvgV(Double avgV) {
        this.avgV = avgV;
    }

    public String getStationName() {
        return StationName;
    }

    public void setStationName(String stationName) {
        StationName = stationName;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public Double getMaxV() {
        return maxV;
    }

    public void setMaxV(Double maxV) {
        this.maxV = maxV;
    }
}
