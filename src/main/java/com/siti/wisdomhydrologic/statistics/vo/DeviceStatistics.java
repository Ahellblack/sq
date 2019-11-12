package com.siti.wisdomhydrologic.statistics.vo;

/**
 * Created by zyw on 2019/10/23.
 */
public class DeviceStatistics {

    private String originDeviceName;
    private String newDeviceName;
    private Integer displaytime;

    public String getOriginDeviceName() {
        return originDeviceName;
    }

    public void setOriginDeviceName(String originDeviceName) {
        this.originDeviceName = originDeviceName;
    }

    public String getNewDeviceName() {
        return newDeviceName;
    }

    public void setNewDeviceName(String newDeviceName) {
        this.newDeviceName = newDeviceName;
    }

    public Integer getDisplaytime() {
        return displaytime;
    }

    public void setDisplaytime(Integer displaytime) {
        this.displaytime = displaytime;
    }
}
