package com.siti.wisdomhydrologic.statistics.entity;

/**
 * Created by dell on 2019/9/20.
 */
public class Patency {

    String stationName;
    Integer stationId;
    Double Number;
    Double patencyRate;
    String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

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

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }


    public Patency(String stationName, Integer stationId, Double number, Double patencyRate, String time) {
        this.stationName = stationName;
        this.stationId = stationId;
        Number = number;
        this.patencyRate = patencyRate;
        this.time = time;
    }

    public Patency(String stationName, Integer stationId, Double number, Double patencyRate) {
        this.stationName = stationName;
        this.stationId = stationId;
        Number = number;
        this.patencyRate = patencyRate;
    }

    public Patency() {
    }

    @Override
    public String toString() {
        return "Patency{" + "stationName='" + stationName + '\'' + ", stationId=" + stationId + ", Number=" + Number + ", patencyRate=" + patencyRate + '}';
    }
}
