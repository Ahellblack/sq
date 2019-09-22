package com.siti.wisdomhydrologic.statistics.entity;

/**
 * Created by dell on 2019/9/20.
 */
public class Patency {

    Integer stationId;
    Double Number;
    Double patencyRate;

    public Double getPatencyRate() {
        return patencyRate;
    }

    public void setPatencyRate(Double patencyRate) {
        this.patencyRate = patencyRate;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public Double getNumber() {
        return Number;
    }

    public void setNumber(Double number) {
        Number = number;
    }

    @Override
    public String toString() {
        return "Patency{" + "stationId=" + stationId + ", Number=" + Number + ", patencyRate=" + patencyRate + '}';
    }
}
