package com.siti.wisdomhydrologic.operation.vo;

/**
 * Created by zyw on 2019/10/22.
 */
public class RealDeviceStatus {

    private String devid;
    private String mc;
    private String lastUploadTime;
    private String stationId;

    public String getDevid() {
        return devid;
    }

    public void setDevid(String devid) {
        this.devid = devid;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public String getLastUploadTime() {
        return lastUploadTime;
    }

    public void setLastUploadTime(String lastUploadTime) {
        this.lastUploadTime = lastUploadTime;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    @Override
    public String toString() {
        return "RealDeviceStatus{" + "devid='" + devid + '\'' + ", mc='" + mc + '\'' + ", lastUploadTime='" + lastUploadTime + '\'' + ", stationId='" + stationId + '\'' + '}';
    }
}
