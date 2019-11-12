package com.siti.wisdomhydrologic.statistics.entity;

/**
 * Created by zyw on 2019/9/23.
 */
public class DeviceChange {

    private Integer number;
    private String newDeviceName;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getNewDeviceName() {
        return newDeviceName;
    }

    public void setNewDeviceName(String newDeviceName) {
        this.newDeviceName = newDeviceName;
    }

    @Override
    public String toString() {
        return "DeviceChange{" + "number=" + number + ", newDeviceName='" + newDeviceName + '\'' + '}';
    }
}
